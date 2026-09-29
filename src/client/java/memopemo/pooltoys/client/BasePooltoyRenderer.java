package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.BoatRenderer;
import org.joml.Matrix4fc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import memopemo.pooltoys.Pooltoy;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

//if creating a new one just rename everything "dragon" in the new file to your new entity.
public abstract class BasePooltoyRenderer<T extends Pooltoy> extends EntityRenderer<T>
{
    protected EntityModel<T> model;

    protected BasePooltoyRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = null;
        this.shadowRadius = 1f;
        this.shadowStrength = 0.7f;
    }

    /*
    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState, ResourceLocation TEXTURE) {
        if(model == null) return;
        poseStack.pushPose();
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
   */

   public void render(T pooltoy, float tilt, float tick, PoseStack poseStack, MultiBufferSource mbSource, int i, ResourceLocation TEXTURE)
   {
       if(model == null) return;
       poseStack.pushPose();
       poseStack.translate(0.0F, 1.5f, 0.0F);
       poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - tilt));
       float hurtTime = (float) pooltoy.getHurtTime() - tick;
       float damage = pooltoy.getDamage() - tick;
       if(damage < 0.0f)
       {
           damage = 0.0f;
       }
       if (hurtTime > 0.0F) {
           poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.sin(hurtTime) * hurtTime * damage / 10.0F * (float)pooltoy.getHurtDir()));
       }
       poseStack.scale(-1.0F, -1.0F, 1.0F);
       model.setupAnim(pooltoy, tick, 0f,-0.1f, 0f, 0f);
       VertexConsumer vertexConsumer = mbSource.getBuffer(model.renderType(TEXTURE));
       model.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1);
       poseStack.popPose();
       super.render(pooltoy, tilt, tick, poseStack, mbSource, i);
   }

    @Override
    public abstract ResourceLocation getTextureLocation(T entity);



//   public PooltoyRenderState createRenderState() {
//      return new PooltoyRenderState();
//   }

//   public void extractRenderState(T pooltoy, PooltoyRenderState renderState, float f) {
//        super.extractRenderState(pooltoy, renderState, f);
//        renderState.yRot = pooltoy.getYRot(f);
//        renderState.hurtTime = (float)pooltoy.getHurtTime() - f;
//        renderState.hurtDir = pooltoy.getHurtDir();
//        renderState.damageTime = Math.max(pooltoy.getDamage() - f, 0.0F);
//        renderState.hasSomeoneSitting = pooltoy.hasControllingPassenger();
//   }

}
