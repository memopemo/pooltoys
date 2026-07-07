package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;

import memopemo.pooltoys.Dragon;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;

public class DragonRenderer extends BasePooltoyRenderer<Dragon>{

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Pooltoys.MOD_ID, "dragon.png");

    protected DragonRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new DragonModel(context.bakeLayer(PooltoysModelLayers.DRAGON));
        this.shadowRadius = 3f;

    }

    @Override
    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, TEXTURE);
    }

    @Override
    public void extractRenderState(Dragon pooltoy, PooltoyRenderState boatRenderState, float f) {
      super.extractRenderState(pooltoy, boatRenderState, f);
    }

}
