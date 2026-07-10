package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import memopemo.pooltoys.Gator;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class GatorRenderer extends BasePooltoyRenderer<Gator>{

    private static final ResourceLocation TEXTURE = new ResourceLocation(Pooltoys.MOD_ID, "gator.png");

    protected GatorRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new GatorModel(context.bakeLayer(PooltoysModelLayers.GATOR));
        this.shadowRadius = 0.75f;
    }
//    @Override
//    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
//    {
//        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, TEXTURE);
//    }
//
//    @Override
//    public void extractRenderState(Gator gator, PooltoyRenderState boatRenderState, float f) {
//        super.extractRenderState(gator, boatRenderState, f);
//    }
    @Override
    public void render(Gator pt, float t, float k, PoseStack poseStack, MultiBufferSource mbs, int i)
    {
        super.render(pt, t, k, poseStack, mbs, i, TEXTURE);
    }
    @Override
    public ResourceLocation getTextureLocation(Gator entity) {
        return TEXTURE;
    }

}
