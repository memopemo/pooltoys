package memopemo.pooltoys;

import java.util.function.Supplier;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class Dragon extends Pooltoy{

    public Dragon(EntityType<? extends Pooltoy> entityType, Level level, Supplier<Item> supplier) {
        super(entityType, level, supplier);
    }

    @Override
    protected double rideHeight(EntityDimensions entityDimensions) {
        return 1.75;
    }
}
