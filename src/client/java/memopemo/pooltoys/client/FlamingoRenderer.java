package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import memopemo.pooltoys.Flamingo;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class FlamingoRenderer extends BasePooltoyRenderer<Flamingo>{

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Pooltoys.MOD_ID, "flamingo.png");

    protected FlamingoRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new FlamingoModel(context.bakeLayer(PooltoysModelLayers.FLAMINGO));
        this.shadowRadius = 1.25f;
    }

//    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
//        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, TEXTURE);
//    }
    public void render(Flamingo flamingo, float t, float k, PoseStack poseStack, MultiBufferSource mbs, int i)
    {
        super.render(flamingo, t, k, poseStack, mbs, i, TEXTURE);
    }
   public ResourceLocation getTextureLocation(Flamingo entity)
   {
       return TEXTURE;
   }
//    @Override
//    public void extractRenderState(Flamingo flamingo, PooltoyRenderState boatRenderState, float f) {
//        super.extractRenderState(flamingo, boatRenderState, f);
//    }
}
