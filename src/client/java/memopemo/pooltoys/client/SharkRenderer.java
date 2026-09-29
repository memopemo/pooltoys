package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import memopemo.pooltoys.Gator;
import memopemo.pooltoys.Shark;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SharkRenderer extends BasePooltoyRenderer<Shark>{

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Pooltoys.MOD_ID, "shark.png");

    protected SharkRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new SharkModel(context.bakeLayer(PooltoysModelLayers.SHARK));
    }
//    @Override
//    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
//        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, TEXTURE);
//    }
//
//    @Override
//    public void extractRenderState(Shark shark, PooltoyRenderState boatRenderState, float f) {
//        super.extractRenderState(shark, boatRenderState, f);
//    }
    @Override
    public void render(Shark pt, float t, float k, PoseStack poseStack, MultiBufferSource mbs, int i)
    {
        super.render(pt, t, k, poseStack, mbs, i, TEXTURE);
    }
    @Override
    public ResourceLocation getTextureLocation(Shark entity) {
        return TEXTURE;
    }
}
