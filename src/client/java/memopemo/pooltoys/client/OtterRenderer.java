package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import memopemo.pooltoys.Otter;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class OtterRenderer extends BasePooltoyRenderer<Otter>{

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Pooltoys.MOD_ID, "otter.png");
    private static final ResourceLocation JAZZ = ResourceLocation.fromNamespaceAndPath(Pooltoys.MOD_ID, "otter_jazz.png");

    protected OtterRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new OtterModel(context.bakeLayer(PooltoysModelLayers.OTTER));
        this.shadowRadius = 1.25f;
    }

//    @Override
//    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
//        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, ptRenderState.hasSpecialName==0?JAZZ:TEXTURE);
//    }
//
//    @Override
//    public void extractRenderState(Otter otter, PooltoyRenderState pooltoyRenderState, float f) {
//        super.extractRenderState(otter, pooltoyRenderState, f);
//        pooltoyRenderState.hasSpecialName = -1;
//        if(otter.hasCustomName() && otter.getCustomName().tryCollapseToString().equalsIgnoreCase("jazz"))
//        {
//            pooltoyRenderState.hasSpecialName = 0;
//        }
//
//   }
    @Override
    public void render(Otter pt, float t, float k, PoseStack poseStack, MultiBufferSource mbs, int i)
    {
        ResourceLocation result = TEXTURE;
        if(pt.hasCustomName() && pt.getCustomName().getString().equalsIgnoreCase("jazz"))
        {
            result = JAZZ;
        }
        super.render(pt, t, k, poseStack, mbs, i, result);
    }
    @Override
    public ResourceLocation getTextureLocation(Otter entity) {
        return TEXTURE;
    }
}
