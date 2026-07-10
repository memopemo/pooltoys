package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;

import memopemo.pooltoys.Shark;
import memopemo.pooltoys.Wolf;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class WolfRenderer extends BasePooltoyRenderer<Wolf>{

    private static final ResourceLocation TEXTURE = new ResourceLocation(Pooltoys.MOD_ID, "wolf.png");
    private static final ResourceLocation HUSKY = new ResourceLocation(Pooltoys.MOD_ID, "wolf_husky.png");

    protected WolfRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new WolfModel(context.bakeLayer(PooltoysModelLayers.WOLF));
        this.shadowRadius = 1.5f;
    }

//    @Override
//    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
//
//        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState,ptRenderState.hasSpecialName==0?HUSKY:TEXTURE);
//    }
//
//   @Override
//   public void extractRenderState(Wolf wolf, PooltoyRenderState boatRenderState, float f) {
//        super.extractRenderState(wolf, boatRenderState, f);
//        boatRenderState.hasSpecialName = -1;
//        if(wolf.hasCustomName() && wolf.getCustomName().tryCollapseToString().equalsIgnoreCase("husky"))
//        {
//            boatRenderState.hasSpecialName = 0;
//        }
//   }
    @Override
    public void render(Wolf pt, float t, float k, PoseStack poseStack, MultiBufferSource mbs, int i)
    {
        ResourceLocation result = pt.hasCustomName() && pt.getCustomName().getString().equalsIgnoreCase("husky") ? HUSKY : TEXTURE;
        super.render(pt, t, k, poseStack, mbs, i, result);
    }
    @Override
    public ResourceLocation getTextureLocation(Wolf entity) {
        return TEXTURE;
    }
}
