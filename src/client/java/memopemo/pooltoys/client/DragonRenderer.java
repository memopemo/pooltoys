package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;

import memopemo.pooltoys.Dragon;
import memopemo.pooltoys.Pooltoy;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;

public class DragonRenderer extends BasePooltoyRenderer<Dragon>{

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Pooltoys.MOD_ID, "dragon.png");

    protected DragonRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new DragonModel(context.bakeLayer(PooltoysModelLayers.DRAGON));
        this.shadowRadius = 3f;
    }

    @Override
    public ResourceLocation getTextureLocation(Dragon entity) {
        return TEXTURE;
    }

//    @Override
//    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
//        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, TEXTURE);
//    }
    @Override
    public void render(Dragon pt, float t, float k, PoseStack poseStack, MultiBufferSource mbs, int i)
    {
        super.render(pt, t, k, poseStack, mbs, i, TEXTURE);
    }
//    @Override
//    public void extractRenderState(Dragon pooltoy, PooltoyRenderState boatRenderState, float f) {
//      super.extractRenderState(pooltoy, boatRenderState, f);
//    }

}
