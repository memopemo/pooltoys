package memopemo.pooltoys;

import java.util.function.IntFunction;
import java.util.function.Supplier;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
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
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class Pooltoy extends Entity {
   private static final EntityDataAccessor<Integer> DATA_ID_HURT;
   private static final EntityDataAccessor<Integer> DATA_ID_HURTDIR;
   private static final EntityDataAccessor<Float> DATA_ID_DAMAGE;
   private static final EntityDataAccessor<Boolean> DATA_ID_PADDLE;
   private float paddling = 0;
   private float deltaRotation;
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
   private int lerpSteps;
   private double lerpX;
   private double lerpY;
   private double lerpZ;
   private double lerpYRot;
   private double lerpXRot;
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

   protected void defineSynchedData() {
      this.entityData.define(DATA_ID_HURT, 0);
      this.entityData.define(DATA_ID_HURTDIR, 1);
      this.entityData.define(DATA_ID_DAMAGE, 0.0F);
      this.entityData.define(DATA_ID_PADDLE, false);
   }

   public boolean canCollideWith(Entity entity) {
      return canVehicleCollide(this, entity);
   }

   public static boolean canVehicleCollide(Entity entity, Entity entity2) {
      return (entity2.canBeCollidedWith() || entity2.isPushable()) && !entity.isPassengerOfSameVehicle(entity2);
   }

   public boolean canBeCollidedWith() {
      return true;
   }

   public boolean isPushable() {
      return true;
   }

   public Vec3 getRelativePortalPosition(Direction.Axis axis, BlockUtil.FoundRectangle foundRectangle) {
      return LivingEntity
            .resetForwardDirectionOfRelativePortalPosition(super.getRelativePortalPosition(axis, foundRectangle));
   }

   public double getPassengersRidingOffset()
   {
      return rideHeight(null)-0.25f;
   }

   protected double rideHeight(EntityDimensions entityDimensions) {
      return 1f;
   };


   public void push(Entity entity) {
      //Play bouncing animation
      if(hasBumped <= 0)
      {
         bounce();
         hasBumped = 10;
      }
      if (entity instanceof Pooltoy || entity instanceof Boat) {
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

   public boolean hurt(DamageSource damageSource, float f)
   {
      playBumpSound();
      if (this.isInvulnerableTo(damageSource)) {
         return false;
      } else if (!this.level().isClientSide && !this.isRemoved()) {
         this.setHurtDir(-this.getHurtDir());
         this.setHurtTime(10);
         this.setDamage(this.getDamage() + f * 10.0F);
         this.markHurt();
         this.gameEvent(GameEvent.ENTITY_DAMAGE, damageSource.getEntity());
         boolean bl = damageSource.getEntity() instanceof Player && ((Player)damageSource.getEntity()).getAbilities().instabuild;
         if (bl || this.getDamage() > 40.0F) {
            if (!bl && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
               this.destroy(damageSource);
            }

            this.discard();
         }

         return true;
      } else {
         return true;
      }
   }

   protected void destroy(DamageSource damageSource) {
      this.spawnAtLocation(this.getDropItem());
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
      this.level().playSound((Player) null, this.getX(), this.getY(), this.getZ(), Pooltoys.BOUNCE, this.getSoundSource(), 1F, (float) this.random.triangle(0.6D, (double) 1.5f));
   }

   public boolean isPickable() {
      return !this.isRemoved();
   }

   public void lerpTo(double d, double e, double f, float g, float h, int i, boolean bl) {
      this.lerpX = d;
      this.lerpY = e;
      this.lerpZ = f;
      this.lerpYRot = (double)g;
      this.lerpXRot = (double)h;
      this.lerpSteps = 10;
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
      this.tickLerp();
      if (this.isControlledByLocalInstance()) {
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

      //TODO: Figure out why this wont play litterally anything.
      if (this.getPaddleState()) {
         if (!this.isSilent() && (double)(this.paddling % ((float)Math.PI * 2F)) <= (double)((float)Math.PI / 4F) && (double)((this.paddling + ((float)Math.PI / 8F)) % ((float)Math.PI * 2F)) >= (double)((float)Math.PI / 4F)) {
            Pooltoys.LOGGER.info("Attempting To Play Sound");
            SoundEvent soundEvent = this.getPaddleSound();
            if (soundEvent != null) {
              
               this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), Pooltoys.BOUNCE, this.getSoundSource(), 1.0F, 0.8F + 0.4F * this.random.nextFloat());
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
                     && !(entity instanceof WaterAnimal) && !(entity instanceof Player)) {
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
   private void tickLerp() {
      if (this.isControlledByLocalInstance()) {
         this.lerpSteps = 0;
         this.syncPacketPositionCodec(this.getX(), this.getY(), this.getZ());
      }

      if (this.lerpSteps > 0) {
         double d = this.getX() + (this.lerpX - this.getX()) / (double)this.lerpSteps;
         double e = this.getY() + (this.lerpY - this.getY()) / (double)this.lerpSteps;
         double f = this.getZ() + (this.lerpZ - this.getZ()) / (double)this.lerpSteps;
         double g = Mth.wrapDegrees(this.lerpYRot - (double)this.getYRot());
         this.setYRot(this.getYRot() + (float)g / (float)this.lerpSteps);
         this.setXRot(this.getXRot() + (float)(this.lerpXRot - (double)this.getXRot()) / (float)this.lerpSteps);
         --this.lerpSteps;
         this.setPos(d, e, f);
         this.setRot(this.getYRot(), this.getXRot());
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

   public Vec3 getLeashOffset() {
      return new Vec3((double) 0.0F, (double) (0.88F * this.getBbHeight()), (double) (0.64F * this.getBbWidth()));
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
                           blockState.getCollisionShape(this.level(), mutableBlockPos).move((double)p, (double)s, (double)q),
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
      double gravityApplication = -0.04F;
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
      if (this.hasPassenger(entity)) {
         float f = this.getSinglePassengerXOffset();
         float g = (float)((this.isRemoved() ? (double)0.01F : this.getPassengersRidingOffset()) + entity.getMyRidingOffset());
         if (this.getPassengers().size() > 1) {
            int i = this.getPassengers().indexOf(entity);
            if (i == 0) {
               f = 0.2F;
            } else {
               f = -0.6F;
            }

            if (entity instanceof Animal) {
               f += 0.2F;
            }
         }

         Vec3 vec3 = (new Vec3((double)f, (double)0.0F, (double)0.0F)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
         moveFunction.accept(entity, this.getX() + vec3.x, this.getY() + (double)g, this.getZ() + vec3.z);
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

   protected void addAdditionalSaveData(CompoundTag compoundTag) {
   }

   protected void readAdditionalSaveData(CompoundTag compoundTag) {
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
   public void setDamage(float f) {
      this.entityData.set(DATA_ID_DAMAGE, f);
   }

   public float getDamage() {
      return (Float)this.entityData.get(DATA_ID_DAMAGE);
   }

   public void setHurtTime(int i) {
      this.entityData.set(DATA_ID_HURT, i);
   }

   public int getHurtTime() {
      return (Integer)this.entityData.get(DATA_ID_HURT);
   }

   public void setHurtDir(int i) {
      this.entityData.set(DATA_ID_HURTDIR, i);
   }

   public int getHurtDir() {
      return (Integer)this.entityData.get(DATA_ID_HURTDIR);
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

   protected final Item getDropItem()
   {
      return this.dropItem.get();
   }

   public final ItemStack getPickResult() {
      return new ItemStack(this.getDropItem());
   }

   static{
      DATA_ID_HURT = SynchedEntityData.defineId(Pooltoy.class, EntityDataSerializers.INT);
      DATA_ID_HURTDIR = SynchedEntityData.defineId(Pooltoy.class, EntityDataSerializers.INT);
      DATA_ID_DAMAGE = SynchedEntityData.defineId(Pooltoy.class, EntityDataSerializers.FLOAT);
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
   public static enum Type implements StringRepresentable {
      OAK(Blocks.OAK_PLANKS, "oak"),
      SPRUCE(Blocks.SPRUCE_PLANKS, "spruce"),
      BIRCH(Blocks.BIRCH_PLANKS, "birch"),
      JUNGLE(Blocks.JUNGLE_PLANKS, "jungle"),
      ACACIA(Blocks.ACACIA_PLANKS, "acacia"),
      CHERRY(Blocks.CHERRY_PLANKS, "cherry"),
      DARK_OAK(Blocks.DARK_OAK_PLANKS, "dark_oak"),
      MANGROVE(Blocks.MANGROVE_PLANKS, "mangrove"),
      BAMBOO(Blocks.BAMBOO_PLANKS, "bamboo");

      private final String name;
      private final Block planks;
      public static final StringRepresentable.EnumCodec<Type> CODEC = StringRepresentable.fromEnum(Type::values);
      private static final IntFunction<Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

      private Type(Block block, String string2) {
         this.name = string2;
         this.planks = block;
      }

      public String getSerializedName() {
         return this.name;
      }

      public String getName() {
         return this.name;
      }

      public Block getPlanks() {
         return this.planks;
      }

      public String toString() {
         return this.name;
      }

      public static Pooltoy.Type byId(int i) {
         return (Pooltoy.Type)BY_ID.apply(i);
      }

      public static Pooltoy.Type byName(String string) {
         return (Pooltoy.Type)CODEC.byName(string, OAK);
      }
   }
}