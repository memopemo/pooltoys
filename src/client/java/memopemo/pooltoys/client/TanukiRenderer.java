package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import memopemo.pooltoys.Gator;
import memopemo.pooltoys.Tanuki;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class TanukiRenderer extends BasePooltoyRenderer<Tanuki>{

    private static final ResourceLocation TEXTURE = new ResourceLocation(Pooltoys.MOD_ID, "tanuki_boba.png");
    private static final ResourceLocation RED = new ResourceLocation(Pooltoys.MOD_ID, "tanuki_red_panda.png");
    private static final ResourceLocation GREEN = new ResourceLocation(Pooltoys.MOD_ID, "tanuki_matcha.png");
    private static final ResourceLocation PURPLE = new ResourceLocation(Pooltoys.MOD_ID, "tanuki_taro.png");

    protected TanukiRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new TanukiModel(context.bakeLayer(PooltoysModelLayers.TANUKI));
        this.shadowRadius = 1.5f;
    }

//    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
//        ResourceLocation customTexture = TEXTURE;
//        switch (ptRenderState.hasSpecialName) {
//            case 0:
//                customTexture = RED;
//                break;
//            case 1:
//                customTexture = PURPLE;
//                break;
//            case 2:
//                customTexture = GREEN;
//                break;
//            default:
//                break;
//        }
//        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, customTexture);
//   }
//
//   @Override
//   public void extractRenderState(Tanuki tanuki, PooltoyRenderState renderState, float f) {
//        super.extractRenderState(tanuki, renderState, f);
//        renderState.hasSpecialName = -1;
//        if(tanuki.hasCustomName())
//        {
//            if(tanuki.getCustomName().tryCollapseToString().equalsIgnoreCase("chili"))
//                renderState.hasSpecialName = 0;
//            if(tanuki.getCustomName().tryCollapseToString().equalsIgnoreCase("taro"))
//                renderState.hasSpecialName = 1;
//            if(tanuki.getCustomName().tryCollapseToString().equalsIgnoreCase("matcha"))
//                renderState.hasSpecialName = 2;
//        }
//
//   }
    @Override
    public void render(Tanuki pt, float t, float k, PoseStack poseStack, MultiBufferSource mbs, int i)
    {
        ResourceLocation result = TEXTURE;
        if(pt.hasCustomName())
        {
            if(pt.getCustomName().getString().equalsIgnoreCase("chili"))
                result = RED;
            if(pt.getCustomName().getString().equalsIgnoreCase("taro"))
                result = PURPLE;
            if(pt.getCustomName().getString().equalsIgnoreCase("matcha"))
                result = GREEN;
        }
        super.render(pt, t, k, poseStack, mbs, i, result);
    }
    @Override
    public ResourceLocation getTextureLocation(Tanuki pt) {
        ResourceLocation result = TEXTURE;
        if(pt.hasCustomName())
        {
            if(pt.getCustomName().getString().equalsIgnoreCase("chili"))
                result = RED;
            if(pt.getCustomName().getString().equalsIgnoreCase("taro"))
                result = PURPLE;
            if(pt.getCustomName().getString().equalsIgnoreCase("matcha"))
                result = GREEN;
        }
        return result;
    }
}
