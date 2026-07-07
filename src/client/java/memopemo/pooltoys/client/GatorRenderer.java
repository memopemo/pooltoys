package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import memopemo.pooltoys.Gator;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;

public class GatorRenderer extends BasePooltoyRenderer<Gator>{

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Pooltoys.MOD_ID, "gator.png");

    protected GatorRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new GatorModel(context.bakeLayer(PooltoysModelLayers.GATOR));
        this.shadowRadius = 0.75f;
    }
    @Override
    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) 
    {
        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, TEXTURE);
    }

    @Override
    public void extractRenderState(Gator gator, PooltoyRenderState boatRenderState, float f) {
        super.extractRenderState(gator, boatRenderState, f);
    }
}
