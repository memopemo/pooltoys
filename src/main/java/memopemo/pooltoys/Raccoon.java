package memopemo.pooltoys;

import java.util.function.Supplier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class Raccoon extends Pooltoy{
    public Raccoon(EntityType<? extends Pooltoy> entityType, Level level, Supplier<Item> supplier) {
        super(entityType, level, supplier);
    }
}
