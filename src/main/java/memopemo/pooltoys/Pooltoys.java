package memopemo.pooltoys;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pooltoys implements ModInitializer {
	public static final String MOD_ID = "pooltoys";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	// copy and adjust for new pooltoy
	public static final EntityType<Orca> ORCA = register(
			"orca",
			EntityType.Builder.<Orca>of((entityType, level) -> new Orca(entityType, level, () -> PooltoyItems.ORCA_POOLTOY), MobCategory.MISC)
                .noLootTable()
                .sized(1.5f, 0.75f) //hitbox size (width/depth and height)
                .eyeHeight(1) //doesnt matter i dont think
                .clientTrackingRange(10)
	);

	public static final EntityType<Dragon> DRAGON = register(
			"dragon",
			EntityType.Builder.<Dragon>of((entityType, level) -> new Dragon(entityType, level, () -> PooltoyItems.DRAGON_POOLTOY), MobCategory.MISC)
                .noLootTable()
                .sized(5, 1.75f) //hitbox size (width/depth and height)
                .eyeHeight(1) //doesnt matter i dont think
                .clientTrackingRange(10)
	);

	public static final EntityType<Raccoon> RACCOON = register(
			"raccoon",
			EntityType.Builder.<Raccoon>of((entityType, level) -> new Raccoon(entityType, level, () -> PooltoyItems.RACCOON_POOLTOY), MobCategory.MISC)
                .noLootTable()
                .sized(1.5f, 1f) //hitbox size (width/depth and height)
                .eyeHeight(1) //doesnt matter i dont think
                .clientTrackingRange(10)
	);
	public static final EntityType<Fox> FOX = register(
			"fox",
			EntityType.Builder.<Fox>of((entityType, level) -> new Fox(entityType, level, () -> PooltoyItems.FOX_POOLTOY), MobCategory.MISC)
                .noLootTable()
                .sized(1.5f, 1f) //hitbox size (width/depth and height)
                .eyeHeight(1) //doesnt matter i dont think
                .clientTrackingRange(10)
	);
	public static final EntityType<Gator> GATOR = register(
			"gator",
			EntityType.Builder.<Gator>of((entityType, level) -> new Gator(entityType, level, () -> PooltoyItems.GATOR_POOLTOY), MobCategory.MISC)
                .noLootTable()
                .sized(1.5f, 0.5f) //hitbox size (width/depth and height)
                .eyeHeight(1) //doesnt matter i dont think
                .clientTrackingRange(10)
	);
	public static final EntityType<Otter> OTTER = register(
			"otter",
			EntityType.Builder.<Otter>of((entityType, level) -> new Otter(entityType, level, () -> PooltoyItems.OTTER_POOLTOY), MobCategory.MISC)
                .noLootTable()
                .sized(1.5f, 0.75f) //hitbox size (width/depth and height)
                .eyeHeight(1) //doesnt matter i dont think
                .clientTrackingRange(10)
	);
	public static final EntityType<Shark> SHARK = register(
			"shark",
			EntityType.Builder.<Shark>of((entityType, level) -> new Shark(entityType, level, () -> PooltoyItems.SHARK_POOLTOY), MobCategory.MISC)
                .noLootTable()
                .sized(1.5f, 0.75f) //hitbox size (width/depth and height)
                .eyeHeight(1) //doesnt matter i dont think
                .clientTrackingRange(10)
	);
	public static final EntityType<Flamingo> FLAMINGO = register(
			"flamingo",
			EntityType.Builder.<Flamingo>of((entityType, level) -> new Flamingo(entityType, level, () -> PooltoyItems.FLAMINGO_POOLTOY), MobCategory.MISC)
                .noLootTable()
                .sized(1.5f, 0.75f) //hitbox size (width/depth and height)
                .eyeHeight(1) //doesnt matter i dont think
                .clientTrackingRange(10)
	);
	public static final EntityType<Wolf> WOLF = register(
			"wolf",
			EntityType.Builder.<Wolf>of((entityType, level) -> new Wolf(entityType, level, () -> PooltoyItems.WOLF_POOLTOY), MobCategory.MISC)
                .noLootTable()
                .sized(1.5f, 1f) //hitbox size (width/depth and height)
                .eyeHeight(1) //doesnt matter i dont think
                .clientTrackingRange(10)
	);
	public static final EntityType<Tanuki> TANUKI = register(
			"tanuki",
			EntityType.Builder.<Tanuki>of((entityType, level) -> new Tanuki(entityType, level, () -> PooltoyItems.TANUKI_POOLTOY), MobCategory.MISC)
                .noLootTable()
                .sized(2f, 1.25f) //hitbox size (width/depth and height)
                .eyeHeight(1) //doesnt matter i dont think
                .clientTrackingRange(10)
	);
	public static final EntityType<Test> TEST = register(
			"test",
			EntityType.Builder.<Test>of((entityType, level) -> new Test(entityType, level, () -> PooltoyItems.TANUKI_POOLTOY), MobCategory.MISC)
                .noLootTable()
                .sized(3, 2) //hitbox size (width/depth and height)
                .eyeHeight(1) //doesnt matter i dont think
                .clientTrackingRange(10)
	);

	


    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
		ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Pooltoys.MOD_ID, name));
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
	}
	public static final SoundEvent BOUNCE = Registry.register(BuiltInRegistries.SOUND_EVENT, Identifier.fromNamespaceAndPath(MOD_ID, "bounce"), SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(MOD_ID, "bounce")));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Inflating pooltoys");
		PooltoyItems.initialize();
	}
	
}
