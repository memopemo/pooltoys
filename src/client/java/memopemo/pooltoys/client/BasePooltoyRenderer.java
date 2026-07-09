package memopemo.pooltoys.client;

import org.joml.Matrix4fc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import memopemo.pooltoys.Pooltoy;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

//if creating a new one just rename everything "dragon" in the new file to your new entity.
public class BasePooltoyRenderer<T extends Pooltoy> extends EntityRenderer<T, PooltoyRenderState>
{
    protected EntityModel<PooltoyRenderState> model;

    protected BasePooltoyRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = null;
        this.shadowRadius = 1f;
        this.shadowStrength = 0.7f;
    }
    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState, Identifier TEXTURE) {
        if(model == null) return;
        poseStack.pushPose();

        poseStack.scale(1.0F, 1.0F-ptRenderState.squishTime/50, 1.0F);
        //change translate
        poseStack.translate(0.0F, 1.5f, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - ptRenderState.yRot));
        float hurtTime = ptRenderState.hurtTime;
        if (hurtTime > 0.0F) {
            poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.sin(hurtTime) * hurtTime * ptRenderState.damageTime / 10.0F * (float)ptRenderState.hurtDir));
        }
        

        poseStack.scale(-1.0F, -1.0F, 1.0F);
        submitNodeCollector.submitModel(this.model, ptRenderState, poseStack, this.model.renderType(TEXTURE), ptRenderState.lightCoords, OverlayTexture.NO_OVERLAY, ptRenderState.outlineColor, (ModelFeatureRenderer.CrumblingOverlay)null);
        this.submitTypeAdditions(ptRenderState, poseStack, submitNodeCollector, ptRenderState.lightCoords);
        poseStack.popPose();
        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState);
   }

   protected void submitTypeAdditions(PooltoyRenderState pooltoyRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i) {
   }

   public PooltoyRenderState createRenderState() {
      return new PooltoyRenderState();
   }

   public void extractRenderState(T pooltoy, PooltoyRenderState renderState, float f) {
        super.extractRenderState(pooltoy, renderState, f);
        renderState.yRot = pooltoy.getYRot(f);
        renderState.hurtTime = (float)pooltoy.getHurtTime() - f;
        renderState.hurtDir = pooltoy.getHurtDir();
        renderState.damageTime = Math.max(pooltoy.getDamage() - f, 0.0F);
        renderState.hasSomeoneSitting = pooltoy.hasControllingPassenger();
   }

}
