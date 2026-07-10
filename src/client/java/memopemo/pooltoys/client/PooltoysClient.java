package memopemo.pooltoys.client;

import memopemo.pooltoys.Pooltoys;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class PooltoysClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		//register model and renderer.
		PooltoysModelLayers.registerModelLayers();
		EntityRendererRegistry.register(Pooltoys.ORCA, OrcaRenderer::new);
		EntityRendererRegistry.register(Pooltoys.DRAGON, DragonRenderer::new);
		EntityRendererRegistry.register(Pooltoys.RACCOON, RaccoonRenderer::new);
		EntityRendererRegistry.register(Pooltoys.FOX, FoxRenderer::new);
		EntityRendererRegistry.register(Pooltoys.GATOR, GatorRenderer::new);
		EntityRendererRegistry.register(Pooltoys.OTTER, OtterRenderer::new);
		EntityRendererRegistry.register(Pooltoys.SHARK, SharkRenderer::new);
		EntityRendererRegistry.register(Pooltoys.FLAMINGO, FlamingoRenderer::new);
		EntityRendererRegistry.register(Pooltoys.WOLF, WolfRenderer::new);
		EntityRendererRegistry.register(Pooltoys.TANUKI, TanukiRenderer::new);
		EntityRendererRegistry.register(Pooltoys.TEST, TestRenderer::new);
		
		//Add by copying and renaming like so
	}
}