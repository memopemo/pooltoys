package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;

import memopemo.pooltoys.Fox;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

//if creating a new one just rename everything "fox" in the new file to your new entity.
public class FoxRenderer extends BasePooltoyRenderer<Fox>{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Pooltoys.MOD_ID, "fox.png");
    private static final ResourceLocation MEMOPEMO = new ResourceLocation(Pooltoys.MOD_ID, "fox_memo.png");

    protected FoxRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new FoxModel(context.bakeLayer(PooltoysModelLayers.FOX));
        this.shadowRadius = 1.5f;
    }
    @Override
    public ResourceLocation getTextureLocation(Fox entity) {
        return TEXTURE;
    }
    @Override
    public void render(Fox pt, float t, float k, PoseStack poseStack, MultiBufferSource mbs, int i)
    {
        ResourceLocation result = pt.hasCustomName() && pt.getCustomName().getString().equalsIgnoreCase("memopemo") ? MEMOPEMO : TEXTURE;
        super.render(pt, t, k, poseStack, mbs, i, result);
    }


//    @Override
//    //this is just copy and pasted from the decompilation
//    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
//    {
//        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, ptRenderState.hasSpecialName==0 ? MEMOPEMO : TEXTURE);
//    }

//    @Override
//    public void extractRenderState(Fox pooltoy, PooltoyRenderState boatRenderState, float f) {
//        super.extractRenderState(pooltoy, boatRenderState, f);
//        boatRenderState.hasSpecialName = pooltoy.hasCustomName() && pooltoy.getCustomName().tryCollapseToString().equalsIgnoreCase("memopemo") ? (byte)0 : (byte)-1;
//    }


}
