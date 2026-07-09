package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import memopemo.pooltoys.Shark;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.Identifier;

public class SharkRenderer extends BasePooltoyRenderer<Shark>{

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Pooltoys.MOD_ID, "shark.png");

    protected SharkRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new SharkModel(context.bakeLayer(PooltoysModelLayers.SHARK));
    }
    @Override
    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, TEXTURE);
    }

    @Override
    public void extractRenderState(Shark shark, PooltoyRenderState boatRenderState, float f) {
        super.extractRenderState(shark, boatRenderState, f);
    }
}
