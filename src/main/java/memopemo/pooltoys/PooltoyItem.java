// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package memopemo.pooltoys;

import java.util.List;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntitySpawnReason;
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

   public PooltoyItem(EntityType<? extends Pooltoy> entityType, Item.Properties properties) {
      super(properties);
      this.entityType = entityType;
   }

   public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
      ItemStack itemStack = player.getItemInHand(interactionHand);
      HitResult hitResult = getPlayerPOVHitResult(level, player, Fluid.ANY);
      if (hitResult.getType() == Type.MISS) {
         return InteractionResult.PASS;
      } else {
         Vec3 vec3 = player.getViewVector(1.0F);
         List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(vec3.scale((double)5.0F)).inflate((double)1.0F), EntitySelector.CAN_BE_PICKED);
         if (!list.isEmpty()) {
            Vec3 vec32 = player.getEyePosition();

            for(Entity entity : list) {
               AABB aABB = entity.getBoundingBox().inflate((double)entity.getPickRadius());
               if (aABB.contains(vec32)) {
                  return InteractionResult.PASS;
               }
            }
         }

         if (hitResult.getType() == Type.BLOCK) {
            Pooltoy newPooltoy = this.getBoat(level, hitResult, itemStack, player);
            if (newPooltoy == null) {
               return InteractionResult.FAIL;
            } else {
               newPooltoy.setYRot(player.getYRot());
               if (!level.noCollision(newPooltoy, newPooltoy.getBoundingBox())) {
                  return InteractionResult.FAIL;
               } else {
                  if (!level.isClientSide()) {
                     level.addFreshEntity(newPooltoy);
                     level.gameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getLocation());
                     itemStack.consume(1, player);
                  }

                  player.awardStat(Stats.ITEM_USED.get(this));
                  return InteractionResult.SUCCESS;
               }
            }
         } else {
            return InteractionResult.PASS;
         }
      }
   }

   @Nullable
   private Pooltoy getBoat(Level level, HitResult hitResult, ItemStack itemStack, Player player) {
      Pooltoy newPooltoy = (Pooltoy)this.entityType.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
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