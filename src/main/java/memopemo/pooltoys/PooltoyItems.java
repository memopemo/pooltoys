package memopemo.pooltoys;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.function.Function;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;

public class PooltoyItems {
    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
		// Create the item key.
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, new ResourceLocation(Pooltoys.MOD_ID, name));

		// Create the item instance.
		//T item = itemFactory.apply(settings.setId(itemKey));
        //may not work
        T item = itemFactory.apply(settings);

		// Register the item.
		Registry.register(BuiltInRegistries.ITEM, itemKey, item);

		return item;
	}
    public static final Item DRAGON_POOLTOY = register("dragon_pooltoy", (p)-> new PooltoyItem(Pooltoys.DRAGON, p), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    public static final Item FLAMINGO_POOLTOY = register("flamingo_pooltoy", (p)-> new PooltoyItem(Pooltoys.FLAMINGO, p), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    public static final Item FOX_POOLTOY = register("fox_pooltoy", (p)-> new PooltoyItem(Pooltoys.FOX, p), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    public static final Item GATOR_POOLTOY = register("gator_pooltoy", (p)-> new PooltoyItem(Pooltoys.GATOR, p), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    public static final Item ORCA_POOLTOY = register("orca_pooltoy", (p)-> new PooltoyItem(Pooltoys.ORCA, p), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    public static final Item OTTER_POOLTOY = register("otter_pooltoy", (p)-> new PooltoyItem(Pooltoys.OTTER, p), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    public static final Item RACCOON_POOLTOY = register("raccoon_pooltoy", (p)-> new PooltoyItem(Pooltoys.RACCOON, p), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    public static final Item SHARK_POOLTOY = register("shark_pooltoy", (p)-> new PooltoyItem(Pooltoys.SHARK, p), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    public static final Item TANUKI_POOLTOY = register("tanuki_pooltoy", (p)-> new PooltoyItem(Pooltoys.TANUKI, p), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    public static final Item WOLF_POOLTOY = register("wolf_pooltoy", (p)-> new PooltoyItem(Pooltoys.WOLF, p), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    
    public static void initialize(){
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((creativeTab) -> creativeTab.accept(PooltoyItems.DRAGON_POOLTOY));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((creativeTab) -> creativeTab.accept(PooltoyItems.FLAMINGO_POOLTOY));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((creativeTab) -> creativeTab.accept(PooltoyItems.FOX_POOLTOY));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((creativeTab) -> creativeTab.accept(PooltoyItems.GATOR_POOLTOY));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((creativeTab) -> creativeTab.accept(PooltoyItems.ORCA_POOLTOY));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((creativeTab) -> creativeTab.accept(PooltoyItems.OTTER_POOLTOY));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((creativeTab) -> creativeTab.accept(PooltoyItems.RACCOON_POOLTOY));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((creativeTab) -> creativeTab.accept(PooltoyItems.SHARK_POOLTOY));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((creativeTab) -> creativeTab.accept(PooltoyItems.TANUKI_POOLTOY));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((creativeTab) -> creativeTab.accept(PooltoyItems.WOLF_POOLTOY));
    }

}
