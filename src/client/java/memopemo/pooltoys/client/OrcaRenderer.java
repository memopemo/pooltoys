package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;

import memopemo.pooltoys.Orca;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.Identifier;

public class OrcaRenderer extends BasePooltoyRenderer<Orca>{

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Pooltoys.MOD_ID, "orca.png");

    protected OrcaRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new OrcaModel(context.bakeLayer(PooltoysModelLayers.ORCA));
    }

    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, TEXTURE);
    }
    @Override
    public void extractRenderState(Orca orca, PooltoyRenderState boatRenderState, float f) {
        super.extractRenderState(orca, boatRenderState, f);
    }
}
