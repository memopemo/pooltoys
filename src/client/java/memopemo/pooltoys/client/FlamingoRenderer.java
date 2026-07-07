package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import memopemo.pooltoys.Flamingo;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;

public class FlamingoRenderer extends BasePooltoyRenderer<Flamingo>{

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Pooltoys.MOD_ID, "flamingo.png");

    protected FlamingoRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new FlamingoModel(context.bakeLayer(PooltoysModelLayers.FLAMINGO));
        this.shadowRadius = 1.25f;
    }

    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, TEXTURE);
   }
    @Override
    public void extractRenderState(Flamingo flamingo, PooltoyRenderState boatRenderState, float f) {
        super.extractRenderState(flamingo, boatRenderState, f);
    }
}
