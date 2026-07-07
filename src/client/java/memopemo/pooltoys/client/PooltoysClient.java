package memopemo.pooltoys.client;

import memopemo.pooltoys.Pooltoys;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class PooltoysClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		//register model and renderer.
		PooltoysModelLayers.registerModelLayers();
		EntityRenderers.register(Pooltoys.ORCA, OrcaRenderer::new);
		EntityRenderers.register(Pooltoys.DRAGON, DragonRenderer::new);
		EntityRenderers.register(Pooltoys.RACCOON, RaccoonRenderer::new);
		EntityRenderers.register(Pooltoys.FOX, FoxRenderer::new);
		EntityRenderers.register(Pooltoys.GATOR, GatorRenderer::new);
		EntityRenderers.register(Pooltoys.OTTER, OtterRenderer::new);
		EntityRenderers.register(Pooltoys.SHARK, SharkRenderer::new);
		EntityRenderers.register(Pooltoys.FLAMINGO, FlamingoRenderer::new);
		EntityRenderers.register(Pooltoys.WOLF, WolfRenderer::new);
		EntityRenderers.register(Pooltoys.TANUKI, TanukiRenderer::new);
		EntityRenderers.register(Pooltoys.TEST, TestRenderer::new);
		
		//Add by copying and renaming like so
	}
}