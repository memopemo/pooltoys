package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import memopemo.pooltoys.Pooltoys;
import memopemo.pooltoys.Test;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.Identifier;

//if creating a new one just rename everything "dragon" in the new file to your new entity.
public class TestRenderer extends BasePooltoyRenderer<Test>{
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Pooltoys.MOD_ID, "dragon.png");

    protected TestRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new TestModel(context.bakeLayer(PooltoysModelLayers.DRAGON));
    }

    @Override
    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, TEXTURE);
    }
    @Override
    public void extractRenderState(Test pooltoy, PooltoyRenderState renderState, float f) {
        super.extractRenderState(pooltoy, renderState, f);
    }
}
