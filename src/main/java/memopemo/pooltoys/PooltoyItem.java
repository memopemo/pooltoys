// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package memopemo.pooltoys;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;
import org.jetbrains.annotations.Nullable;

public class PooltoyItem extends Item {
   private final EntityType<? extends Pooltoy> entityType;

   public PooltoyItem(EntityType<? extends Pooltoy> entityType, Properties properties) {
      super(properties);
      this.entityType = entityType;
   }
   public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
      ItemStack itemStack = player.getItemInHand(interactionHand);
      HitResult hitResult = getPlayerPOVHitResult(level, player, Fluid.ANY);
      if (hitResult.getType() == Type.MISS) {
         return InteractionResultHolder.pass(itemStack);
      } else {
         Vec3 vec3 = player.getViewVector(1.0F);
         List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(vec3.scale((double)5.0F)).inflate((double)1.0F), EntitySelector.NO_SPECTATORS.and(Entity::isPickable));
         if (!list.isEmpty()) {
            Vec3 vec32 = player.getEyePosition();

            for(Entity entity : list) {
               AABB aABB = entity.getBoundingBox().inflate((double)entity.getPickRadius());
               if (aABB.contains(vec32)) {
                  return InteractionResultHolder.pass(itemStack);
               }
            }
         }

         if (hitResult.getType() == Type.BLOCK) {
            Pooltoy newPooltoy = this.getBoat(level, hitResult, itemStack, player);
            if (newPooltoy == null) {
               return InteractionResultHolder.fail(itemStack);
            } else {
               newPooltoy.setYRot(player.getYRot());
               if (!level.noCollision(newPooltoy, newPooltoy.getBoundingBox())) {
                  return InteractionResultHolder.fail(itemStack);
               } else {
                  if (!level.isClientSide()) {
                     level.addFreshEntity(newPooltoy);
                     level.gameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getLocation());
                     if (!player.getAbilities().instabuild) {
                        itemStack.shrink(1);
                     }
                  }

                  player.awardStat(Stats.ITEM_USED.get(this));
                  return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
               }
            }
         } else {
            return InteractionResultHolder.pass(itemStack);
         }
      }
   }

   @Nullable
   private Pooltoy getBoat(Level level, HitResult hitResult, ItemStack itemStack, Player player) {
      //Pooltoy newPooltoy = (Pooltoy)this.entityType.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
      Pooltoy newPooltoy = new Pooltoy(this.entityType, level, itemStack::getItem);
      if (newPooltoy != null) {
         Vec3 vec3 = hitResult.getLocation();
         newPooltoy.setInitialPos(vec3.x, vec3.y, vec3.z);
         if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            EntityType.createDefaultStackConfig(serverLevel, itemStack, player).accept(newPooltoy);
         }
      }

      return newPooltoy;
   }
}