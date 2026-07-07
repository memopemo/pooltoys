package memopemo.pooltoys.client;

import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.resources.ResourceLocation;

public class PooltoysModelLayers {
	
	public static final ModelLayerLocation ORCA = createMain("orca");
	public static final ModelLayerLocation DRAGON = createMain("dragon");
	public static final ModelLayerLocation RACCOON = createMain("raccoon");
	public static final ModelLayerLocation FOX = createMain("fox");
	public static final ModelLayerLocation GATOR = createMain("gator");
	public static final ModelLayerLocation OTTER = createMain("otter");
	public static final ModelLayerLocation SHARK = createMain("shark");
	public static final ModelLayerLocation FLAMINGO = createMain("flamingo");
	public static final ModelLayerLocation WOLF = createMain("wolf");
	public static final ModelLayerLocation TANUKI = createMain("tanuki");
	public static final ModelLayerLocation TEST = createMain("test");
	//Add by copying and renaming like so

	private static ModelLayerLocation createMain(String name) {
		return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Pooltoys.MOD_ID, name), "main");
	}

	public static void registerModelLayers() {
		EntityModelLayerRegistry.registerModelLayer(PooltoysModelLayers.ORCA, OrcaModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(PooltoysModelLayers.DRAGON, DragonModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(PooltoysModelLayers.RACCOON, RaccoonModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(PooltoysModelLayers.FOX, FoxModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(PooltoysModelLayers.GATOR, GatorModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(PooltoysModelLayers.OTTER, OtterModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(PooltoysModelLayers.SHARK, SharkModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(PooltoysModelLayers.FLAMINGO, FlamingoModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(PooltoysModelLayers.WOLF, WolfModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(PooltoysModelLayers.TANUKI, TanukiModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(PooltoysModelLayers.TEST,TestModel::getTexturedModelData);
		//Add by copying and renaming like so
	}
}
