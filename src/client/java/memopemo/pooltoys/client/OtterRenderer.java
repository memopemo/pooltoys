package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import memopemo.pooltoys.Otter;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.Identifier;

public class OtterRenderer extends BasePooltoyRenderer<Otter>{

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Pooltoys.MOD_ID, "otter.png");
    private static final Identifier JAZZ = Identifier.fromNamespaceAndPath(Pooltoys.MOD_ID, "otter_jazz.png");

    protected OtterRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new OtterModel(context.bakeLayer(PooltoysModelLayers.OTTER));
        this.shadowRadius = 1.25f;
    }

    @Override
    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, ptRenderState.hasSpecialName==0?JAZZ:TEXTURE);
    }

    @Override
    public void extractRenderState(Otter otter, PooltoyRenderState pooltoyRenderState, float f) {
        super.extractRenderState(otter, pooltoyRenderState, f);
        pooltoyRenderState.hasSpecialName = -1;
        if(otter.hasCustomName() && otter.getCustomName().tryCollapseToString().equalsIgnoreCase("jazz"))
        {
            pooltoyRenderState.hasSpecialName = 0;
        }

   }
}
