package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;

import memopemo.pooltoys.Orca;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class OrcaRenderer extends BasePooltoyRenderer<Orca>{
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Pooltoys.MOD_ID, "orca.png");

    protected OrcaRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new OrcaModel(context.bakeLayer(PooltoysModelLayers.ORCA));
    }

//    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
//        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, TEXTURE);
//    }
//    @Override
//    public void extractRenderState(Orca orca, PooltoyRenderState boatRenderState, float f) {
//        super.extractRenderState(orca, boatRenderState, f);
//    }
    @Override
    public void render(Orca pt, float t, float k, PoseStack poseStack, MultiBufferSource mbs, int i)
    {
        super.render(pt, t, k, poseStack, mbs, i, TEXTURE);
    }
    @Override
    public ResourceLocation getTextureLocation(Orca entity) {
        return TEXTURE;
    }

}
