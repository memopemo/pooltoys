package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;

import memopemo.pooltoys.Pooltoys;
import memopemo.pooltoys.Raccoon;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class RaccoonRenderer extends BasePooltoyRenderer<Raccoon> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Pooltoys.MOD_ID, "raccoon.png");

    protected RaccoonRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new RaccoonModel(context.bakeLayer(PooltoysModelLayers.RACCOON));
        this.shadowRadius = 1.5f;
    }

//    @Override
//    //this is just copy and pasted from the decompilation
//    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
//        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, TEXTURE);
//    }
//    @Override
//    public void extractRenderState(Raccoon pooltoy, PooltoyRenderState boatRenderState, float f) {
//        super.extractRenderState(pooltoy, boatRenderState, f);
//    }
    @Override
    public void render(Raccoon pt, float t, float k, PoseStack poseStack, MultiBufferSource mbs, int i)
    {
        super.render(pt, t, k, poseStack, mbs, i, TEXTURE);
    }
    @Override
    public ResourceLocation getTextureLocation(Raccoon entity) {
        return TEXTURE;
    }

}
