package memopemo.pooltoys;

import java.util.function.Supplier;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class Gator extends Pooltoy {

    public Gator(EntityType<? extends Pooltoy> entityType, Level level, Supplier<Item> supplier) {
        super(entityType, level, supplier);
    }

    @Override
    protected double rideHeight(EntityDimensions entityDimensions) {
        return 0.25f;
    }
}
