package memopemo.pooltoys;

import java.util.function.Supplier;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.vehicle.AbstractBoat;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.util.List;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ServerboundPaddleBoatPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.InterpolationHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class Pooltoy extends VehicleEntity implements Leashable {

   private static final EntityDataAccessor<Boolean> DATA_ID_PADDLE;
   private float paddling = 0;
   private float deltaRotation;
   private final InterpolationHandler interpolation = new InterpolationHandler(this, 3);
   private boolean inputLeft;
   private boolean inputRight;
   private boolean inputUp;
   private boolean inputDown;
   private double waterLevel;
   private float landFriction;
   private Status status;
   private Status oldStatus;
   private double lastYd;
   private float bubbleAngle;
   private float bubbleAngleO;
   private int hasBumped;
   private int moveTimer;
   @Nullable
   private Leashable.LeashData leashData;
   private final Supplier<Item> dropItem;

   public Pooltoy(EntityType<? extends Pooltoy> entityType, Level level, Supplier<Item> supplier) {
      super(entityType, level);
      this.dropItem = supplier;
      this.blocksBuilding = true;
   }

   public void setInitialPos(double d, double e, double f) {
      this.setPos(d, e, f);
      this.xo = d;
      this.yo = e;
      this.zo = f;
      bounce();
   }
   

   protected Entity.MovementEmission getMovementEmission() {
      return MovementEmission.EVENTS;
   }

   protected void defineSynchedData(SynchedEntityData.Builder builder) {
      builder.define(DATA_ID_PADDLE, false);
      super.defineSynchedData(builder);
   }

   public boolean canCollideWith(Entity entity) {
      return canVehicleCollide(this, entity);
   }

   public static boolean canVehicleCollide(Entity entity, Entity entity2) {
      return (entity2.canBeCollidedWith(entity) || entity2.isPushable()) && !entity.isPassengerOfSameVehicle(entity2);
   }

   public boolean canBeCollidedWith(@Nullable Entity entity) {
      return true;
   }

   public boolean isPushable() {
      return true;
   }

   public Vec3 getRelativePortalPosition(Direction.Axis axis, BlockUtil.FoundRectangle foundRectangle) {
      return LivingEntity
            .resetForwardDirectionOfRelativePortalPosition(super.getRelativePortalPosition(axis, foundRectangle));
   }

   protected double rideHeight(EntityDimensions entityDimensions) {
      return 1f;
   };

   protected Vec3 getPassengerAttachmentPoint(Entity entity, EntityDimensions entityDimensions, float f) {
      float g = this.getSinglePassengerXOffset();
      if (this.getPassengers().size() > 1) {
         int i = this.getPassengers().indexOf(entity);
         if (i == 0) {
            g = 0.2F;
         } else {
            g = -0.6F;
         }

         if (entity instanceof Animal) {
            g += 0.2F;
         }
      }

      return (new Vec3((double) 0.0F, this.rideHeight(entityDimensions), (double) g))
            .yRot(-this.getYRot() * ((float) Math.PI / 180F));
   }

   public void push(Entity entity) {
      //Play bouncing animation
      if(hasBumped <= 0)
      {
         bounce();
         hasBumped = 10;
      }
      if (entity instanceof Pooltoy || entity instanceof AbstractBoat) {
         if (entity.getBoundingBox().minY < this.getBoundingBox().maxY) {
            super.push(entity);
         }
      } else if (entity.getBoundingBox().minY <= this.getBoundingBox().minY) {
         //Play bouncing animation, but dont if its our passenger colliding with us.
         if(!entity.isPassenger()) {
            super.push(entity);
         }
      }

   }

   public void animateHurt(float f) {
      this.setHurtDir(-this.getHurtDir());
      this.setHurtTime(10);
      this.setDamage(this.getDamage() * 11.0F);
   }

   public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float f)
   {
      playBumpSound();
      super.hurtServer(serverLevel, damageSource, f);
      return true;
   }

   private void bounce()
   {
      playBumpSound();
      this.setDamage(1f);
      this.setHurtDir(-1);
      animateHurt(0);
   }
   private void playBumpSound()
   {
      this.level().playSound((Entity)null, this.getX(), this.getY(), this.getZ(), Pooltoys.BOUNCE, this.getSoundSource(), 1F, this.getRandom().triangle(0.6f, 1.5f));
   }

   public boolean isPickable() {
      return !this.isRemoved();
   }

   public InterpolationHandler getInterpolation() {
      return this.interpolation;
   }

   public Direction getMotionDirection() {
      return this.getDirection().getClockWise();
   }

   public void tick() {
      this.oldStatus = this.status;
      this.status = this.getStatus();

      if (this.getHurtTime() > 0) {
         this.setHurtTime(this.getHurtTime() - 1);
      }

      if (this.getDamage() > 0.0F) {
         this.setDamage(this.getDamage() - 1.0F);
      }

      super.tick();
      this.interpolation.interpolate();
      if (this.isLocalInstanceAuthoritative()) {
         if (!(this.getFirstPassenger() instanceof Player)) {
            this.setPaddleState(false);
         }
         this.floatBoat();
         if (this.level().isClientSide()) {
            this.controlBoat();
            this.level().sendPacketToServer(new ServerboundPaddleBoatPacket(this.getPaddleState(), this.getPaddleState()));
         }

         this.move(MoverType.SELF, this.getDeltaMovement());
      } else {
         this.setDeltaMovement(Vec3.ZERO);
      }

      this.applyEffectsFromBlocks();
      this.applyEffectsFromBlocks();

      //TODO: Figure out why this wont play litterally anything.
      if (this.getPaddleState()) {
         if (!this.isSilent() && (double)(this.paddling % ((float)Math.PI * 2F)) <= (double)((float)Math.PI / 4F) && (double)((this.paddling + ((float)Math.PI / 8F)) % ((float)Math.PI * 2F)) >= (double)((float)Math.PI / 4F)) {
            Pooltoys.LOGGER.info("Attempting To Play Sound");
            SoundEvent soundEvent = this.getPaddleSound();
            if (soundEvent != null) {
              
               this.level().playSound((Entity)null, this.getX(), this.getY(), this.getZ(), Pooltoys.BOUNCE, this.getSoundSource(), 1.0F, 0.8F + 0.4F * this.random.nextFloat());
               Pooltoys.LOGGER.info("Played? ...No? Figures. Figures getting this thing to peep sucks ass and i hate it and frick you");
            }
         }

         this.paddling += ((float)Math.PI / 8F);
         Pooltoys.LOGGER.info("paddling: {}", this.paddling);
      } 
      else {
         this.paddling = 0.0F;
      }

      List<Entity> list = this.level().getEntities(this,
            this.getBoundingBox().inflate((double) 0.2F, (double) 0.1F, (double) 0.2F),
            EntitySelector.pushableBy(this));
      if (!list.isEmpty()) {
         boolean bl = !this.level().isClientSide() && !(this.getControllingPassenger() instanceof Player);

         for (Entity entity : list) {
            if (!entity.hasPassenger(this)) {
               if (bl && this.getPassengers().size() < this.getMaxPassengers() && !entity.isPassenger()
                     && this.hasEnoughSpaceFor(entity) && entity instanceof LivingEntity
                     && !entity.getType().is(EntityTypeTags.CANNOT_BE_PUSHED_ONTO_BOATS)) {
                  entity.startRiding(this);
               } else {
                  this.push(entity);
               }
            }
         }
      }
      else
      {
         hasBumped -= 1;
      }

   }

   @Nullable
   protected SoundEvent getPaddleSound() {
      SoundEvent var10000;
      switch (this.getStatus().ordinal()) {
         case 0:
         case 1:
         case 2:
            var10000 = SoundEvents.BOAT_PADDLE_WATER;
            break;
         case 3:
            var10000 = SoundEvents.BOAT_PADDLE_LAND;
            break;
         default:
            var10000 = null;
      }

      return var10000;
   }

   @Nullable
   public Leashable.LeashData getLeashData() {
      return this.leashData;
   }

   public void setLeashData(@Nullable Leashable.LeashData leashData) {
      this.leashData = leashData;
   }

   public Vec3 getLeashOffset() {
      return new Vec3((double) 0.0F, (double) (0.88F * this.getBbHeight()), (double) (0.64F * this.getBbWidth()));
   }

   public boolean supportQuadLeash() {
      return true;
   }

   public Vec3[] getQuadLeashOffsets() {
      return Leashable.createQuadLeashOffsets(this, (double) 0.0F, 0.64, 0.382, 0.88);
   }

   private Status getStatus() {
      Status status = this.isUnderwater();
      if (status != null) {
         this.waterLevel = this.getBoundingBox().maxY;
         return status;
      } else if (this.checkInWater()) {
         return Pooltoy.Status.IN_WATER;
      } else {
         float f = this.getGroundFriction();
         if (f > 0.0F) {
            this.landFriction = f;
            return Pooltoy.Status.ON_LAND;
         } else {
            return Pooltoy.Status.IN_AIR;
         }
      }
   }

   public float getWaterLevelAbove() {
      AABB aABB = this.getBoundingBox();
      int i = Mth.floor(aABB.minX);
      int j = Mth.ceil(aABB.maxX);
      int k = Mth.floor(aABB.maxY);
      int l = Mth.ceil(aABB.maxY - this.lastYd);
      int m = Mth.floor(aABB.minZ);
      int n = Mth.ceil(aABB.maxZ);
      BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

      label39: for (int o = k; o < l; ++o) {
         float f = 0.0F;

         for (int p = i; p < j; ++p) {
            for (int q = m; q < n; ++q) {
               mutableBlockPos.set(p, o, q);
               FluidState fluidState = this.level().getFluidState(mutableBlockPos);
               if (fluidState.is(FluidTags.WATER)) {
                  f = Math.max(f, fluidState.getHeight(this.level(), mutableBlockPos));
               }

               if (f >= 1.0F) {
                  continue label39;
               }
            }
         }

         if (f < 1.0F) {
            return (float) mutableBlockPos.getY() + f;
         }
      }

      return (float) (l + 1);
   }

   public float getGroundFriction() {
      AABB aABB = this.getBoundingBox();
      AABB aABB2 = new AABB(aABB.minX, aABB.minY - 0.001, aABB.minZ, aABB.maxX, aABB.minY, aABB.maxZ);
      int i = Mth.floor(aABB2.minX) - 1;
      int j = Mth.ceil(aABB2.maxX) + 1;
      int k = Mth.floor(aABB2.minY) - 1;
      int l = Mth.ceil(aABB2.maxY) + 1;
      int m = Mth.floor(aABB2.minZ) - 1;
      int n = Mth.ceil(aABB2.maxZ) + 1;
      VoxelShape voxelShape = Shapes.create(aABB2);
      float f = 0.0F;
      int o = 0;
      BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

      for (int p = i; p < j; ++p) {
         for (int q = m; q < n; ++q) {
            int r = (p != i && p != j - 1 ? 0 : 1) + (q != m && q != n - 1 ? 0 : 1);
            if (r != 2) {
               for (int s = k; s < l; ++s) {
                  if (r <= 0 || s != k && s != l - 1) {
                     mutableBlockPos.set(p, s, q);
                     BlockState blockState = this.level().getBlockState(mutableBlockPos);
                     if (!(blockState.getBlock() instanceof WaterlilyBlock) && Shapes.joinIsNotEmpty(
                           blockState.getCollisionShape(this.level(), mutableBlockPos).move(mutableBlockPos),
                           voxelShape, BooleanOp.AND)) {
                        f += blockState.getBlock().getFriction();
                        ++o;
                     }
                  }
               }
            }
         }
      }

      return f / (float) o;
   }

   private boolean checkInWater() {
      AABB aABB = this.getBoundingBox();
      int bbMinXBlocks = Mth.floor(aABB.minX);
      int bbMaxXBlocks = Mth.ceil(aABB.maxX);
      int bbMinYBlocks = Mth.floor(aABB.minY);
      int bbMaxYBlocks = Mth.ceil(aABB.minY + 0.001);
      int bbMinZBlocks = Mth.floor(aABB.minZ);
      int bbMaxZBlocks = Mth.ceil(aABB.maxZ);
      boolean waterResult = false;
      this.waterLevel = -Double.MAX_VALUE;
      BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

      for (int x = bbMinXBlocks; x < bbMaxXBlocks; ++x) {
         for (int y = bbMinYBlocks; y < bbMaxYBlocks; ++y) {
            for (int z = bbMinZBlocks; z < bbMaxZBlocks; ++z) {
               mutableBlockPos.set(x, y, z);
               FluidState fluidState = this.level().getFluidState(mutableBlockPos);
               if (fluidState.is(FluidTags.WATER)) {
                  float fluidBlockHeight = (float) y + fluidState.getHeight(this.level(), mutableBlockPos);
                  this.waterLevel = Math.max((double) fluidBlockHeight, this.waterLevel);
                  waterResult |= aABB.minY < (double) fluidBlockHeight;
               }
            }
         }
      }

      return waterResult;
   }

   @Nullable
   private Status isUnderwater() {
      AABB aABB = this.getBoundingBox();
      double d = aABB.maxY + 0.001;
      int i = Mth.floor(aABB.minX);
      int j = Mth.ceil(aABB.maxX);
      int k = Mth.floor(aABB.maxY);
      int l = Mth.ceil(d);
      int m = Mth.floor(aABB.minZ);
      int n = Mth.ceil(aABB.maxZ);
      boolean bl = false;
      BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

      for (int o = i; o < j; ++o) {
         for (int p = k; p < l; ++p) {
            for (int q = m; q < n; ++q) {
               mutableBlockPos.set(o, p, q);
               FluidState fluidState = this.level().getFluidState(mutableBlockPos);
               if (fluidState.is(FluidTags.WATER) && d < (double) ((float) mutableBlockPos.getY()
                     + fluidState.getHeight(this.level(), mutableBlockPos))) {
                  if (!fluidState.isSource()) {
                     return Pooltoy.Status.UNDER_FLOWING_WATER;
                  }

                  bl = true;
               }
            }
         }
      }

      return bl ? Pooltoy.Status.UNDER_WATER : null;
   }

   protected double getDefaultGravity() {
      return 0.04;
   }

   private void floatBoat() {
      double gravityApplication = -this.getGravity();
      double floatAmount = (double) 0.0F;
      float resultingFriction = 0.05F;
      if (this.oldStatus == Pooltoy.Status.IN_AIR && this.status != Pooltoy.Status.IN_AIR
            && this.status != Pooltoy.Status.ON_LAND) {
         this.waterLevel = this.getY((double) 1.0F);
         double g = (double) (this.getWaterLevelAbove() - this.getBbHeight()) + 0.101;
         if (this.level().noCollision(this,
               this.getBoundingBox().move((double) 0.0F, g - this.getY(), (double) 0.0F))) {
            this.setPos(this.getX(), g, this.getZ());
            this.setDeltaMovement(this.getDeltaMovement().multiply((double) 1.0F, (double) 0.0F, (double) 1.0F));
            this.lastYd = (double) 0.0F;
         }

         this.status = Pooltoy.Status.IN_WATER;
      } else {
         if (this.status == Pooltoy.Status.IN_WATER) {
            floatAmount = (this.waterLevel+0.25F) - this.getY();
            resultingFriction = 0.9F;
         } else if (this.status == Pooltoy.Status.UNDER_FLOWING_WATER) {
            floatAmount = (this.waterLevel+0.25F) - this.getY();
            resultingFriction = 0.9F;
         } else if (this.status == Pooltoy.Status.UNDER_WATER) {
            floatAmount = (this.waterLevel+0.25F) - this.getY();
            resultingFriction = 0.9F;
         } else if (this.status == Pooltoy.Status.IN_AIR) {
            resultingFriction = 0.9F;
         } else if (this.status == Pooltoy.Status.ON_LAND) {
            resultingFriction = this.landFriction;
         }

         Vec3 vec3 = this.getDeltaMovement();
         this.setDeltaMovement(vec3.x * (double) resultingFriction, vec3.y + gravityApplication, vec3.z * (double) resultingFriction);
         this.deltaRotation *= resultingFriction;
         Vec3 vec32 = this.getDeltaMovement();
         this.setDeltaMovement(vec32.x, (vec32.y + floatAmount * (this.getDefaultGravity() / 0.65)) * (double) 0.85F, vec32.z);
      }

   }

   private void controlBoat() {
      if (this.isVehicle()) {
         float f = 0.0F;
         if (this.inputLeft) {
            --this.deltaRotation;
         }

         if (this.inputRight) {
            ++this.deltaRotation;
         }

         if (this.inputRight != this.inputLeft && !this.inputUp && !this.inputDown) {
            f += 0.005F;
         }

         this.setYRot(this.getYRot() + this.deltaRotation);
         if (this.inputUp) {
            f += 0.04F;
         }

         if (this.inputDown) {
            f -= 0.005F;
         }

         this.setDeltaMovement(
               this.getDeltaMovement().add((double) (Mth.sin(-this.getYRot() * ((float) Math.PI / 180F)) * f),
                     (double) 0.0F, (double) (Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * f)));
         this.setPaddleState(this.inputRight || this.inputLeft || this.inputUp);
      }
   }

   protected float getSinglePassengerXOffset() {
      return 0.0F;
   }

   public boolean hasEnoughSpaceFor(Entity entity) {
      return entity.getBbWidth() < this.getBbWidth();
   }

   protected void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
      super.positionRider(entity, moveFunction);
      if (!entity.getType().is(EntityTypeTags.CAN_TURN_IN_BOATS)) {
         entity.setYRot(entity.getYRot() + this.deltaRotation);
         entity.setYHeadRot(entity.getYHeadRot() + this.deltaRotation);
         this.clampRotation(entity);
         if (entity instanceof Animal && this.getPassengers().size() == this.getMaxPassengers()) {
            int i = entity.getId() % 2 == 0 ? 90 : 270;
            entity.setYBodyRot(((Animal) entity).yBodyRot + (float) i);
            entity.setYHeadRot(entity.getYHeadRot() + (float) i);
         }

      }
   }

   public Vec3 getDismountLocationForPassenger(LivingEntity livingEntity) {
      Vec3 vec3 = getCollisionHorizontalEscapeVector((double) (this.getBbWidth() * Mth.SQRT_OF_TWO),
            (double) livingEntity.getBbWidth(), livingEntity.getYRot());
      double d = this.getX() + vec3.x;
      double e = this.getZ() + vec3.z;
      BlockPos blockPos = BlockPos.containing(d, this.getBoundingBox().maxY, e);
      BlockPos blockPos2 = blockPos.below();
      if (!this.level().isWaterAt(blockPos2)) {
         List<Vec3> list = Lists.newArrayList();
         double f = this.level().getBlockFloorHeight(blockPos);
         if (DismountHelper.isBlockFloorValid(f)) {
            list.add(new Vec3(d, (double) blockPos.getY() + f, e));
         }

         double g = this.level().getBlockFloorHeight(blockPos2);
         if (DismountHelper.isBlockFloorValid(g)) {
            list.add(new Vec3(d, (double) blockPos2.getY() + g, e));
         }

         UnmodifiableIterator var14 = livingEntity.getDismountPoses().iterator();

         while (var14.hasNext()) {
            Pose pose = (Pose) var14.next();

            for (Vec3 vec32 : list) {
               if (DismountHelper.canDismountTo(this.level(), vec32, livingEntity, pose)) {
                  livingEntity.setPose(pose);
                  return vec32;
               }
            }
         }
      }

      return super.getDismountLocationForPassenger(livingEntity);
   }

   protected void clampRotation(Entity entity) {
      entity.setYBodyRot(this.getYRot());
      float f = Mth.wrapDegrees(entity.getYRot() - this.getYRot());
      float g = Mth.clamp(f, -105.0F, 105.0F);
      entity.yRotO += g - f;
      entity.setYRot(entity.getYRot() + g - f);
      entity.setYHeadRot(entity.getYRot());
   }

   public void onPassengerTurned(Entity entity) {
      this.clampRotation(entity);
   }

   protected void addAdditionalSaveData(ValueOutput valueOutput) {
      this.writeLeashData(valueOutput, this.leashData);
   }

   protected void readAdditionalSaveData(ValueInput valueInput) {
      this.readLeashData(valueInput);
   }

   public InteractionResult interact(Player player, InteractionHand interactionHand) {
      InteractionResult interactionResult = super.interact(player, interactionHand);
      if (interactionResult != InteractionResult.PASS) {
         return interactionResult;
      } else {
         return (InteractionResult) (player.isSecondaryUseActive()
               || !this.level().isClientSide() && !player.startRiding(this) ? InteractionResult.PASS
                     : InteractionResult.SUCCESS);
      }
   }

   public void remove(Entity.RemovalReason removalReason) {
      if (!this.level().isClientSide() && removalReason.shouldDestroy() && this.isLeashed()) {
         this.dropLeash();
      }

      super.remove(removalReason);
   }

   protected void checkFallDamage(double d, boolean bl, BlockState blockState, BlockPos blockPos) {
      this.lastYd = this.getDeltaMovement().y;
      if (!this.isPassenger()) {
         if (bl) {
            this.resetFallDistance();
         } else if (!this.level().getFluidState(this.blockPosition().below()).is(FluidTags.WATER)
               && d < (double) 0.0F) {
            this.fallDistance -= (double) ((float) d);
         }

      }
   }
   public void setPaddleState(boolean value)
   {
      this.entityData.set(DATA_ID_PADDLE, value);
   }

   public boolean getPaddleState() {
      return (Boolean)this.entityData.get(DATA_ID_PADDLE) && this.getControllingPassenger() != null;
   }


   public float getBubbleAngle(float f) {
      return Mth.lerp(f, this.bubbleAngleO, this.bubbleAngle);
   }

   protected boolean canAddPassenger(Entity entity) {
      return this.getPassengers().size() < this.getMaxPassengers() && !this.isEyeInFluid(FluidTags.WATER);
   }

   protected int getMaxPassengers() {
      return 2;
   }

   @Nullable
   public LivingEntity getControllingPassenger() {
      Entity var2 = this.getFirstPassenger();
      LivingEntity var10000;
      if (var2 instanceof LivingEntity livingEntity) {
         var10000 = livingEntity;
      } else {
         var10000 = super.getControllingPassenger();
      }

      return var10000;
   }

   public void setInput(boolean bl, boolean bl2, boolean bl3, boolean bl4) {

      this.inputLeft = bl;
      this.inputRight = bl2;
      this.inputUp = bl3;
      this.inputDown = bl4;
   }

   public boolean isUnderWater() {
      return this.status == Pooltoy.Status.UNDER_WATER || this.status == Pooltoy.Status.UNDER_FLOWING_WATER;
   }

   protected final Item getDropItem() {
      return (Item) this.dropItem.get();
   }

   public final ItemStack getPickResult() {
      return new ItemStack((ItemLike) this.dropItem.get());
   }
   static{
      DATA_ID_PADDLE = SynchedEntityData.defineId(Pooltoy.class, EntityDataSerializers.BOOLEAN);
   }

   public static enum Status {
      IN_WATER,
      UNDER_WATER,
      UNDER_FLOWING_WATER,
      ON_LAND,
      IN_AIR;

      private Status() {
      }
   }
}