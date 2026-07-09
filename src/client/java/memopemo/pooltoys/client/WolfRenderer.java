package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;

import memopemo.pooltoys.Wolf;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.Identifier;

public class WolfRenderer extends BasePooltoyRenderer<Wolf>{

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Pooltoys.MOD_ID, "wolf.png");
    private static final Identifier HUSKY = Identifier.fromNamespaceAndPath(Pooltoys.MOD_ID, "wolf_husky.png");

    protected WolfRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new WolfModel(context.bakeLayer(PooltoysModelLayers.WOLF));
        this.shadowRadius = 1.5f;
    }

    @Override
    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        
        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState,ptRenderState.hasSpecialName==0?HUSKY:TEXTURE);
    }

   @Override
   public void extractRenderState(Wolf wolf, PooltoyRenderState boatRenderState, float f) {
        super.extractRenderState(wolf, boatRenderState, f);
        boatRenderState.hasSpecialName = -1;
        if(wolf.hasCustomName() && wolf.getCustomName().tryCollapseToString().equalsIgnoreCase("husky"))
        {
            boatRenderState.hasSpecialName = 0;
        }
   }
}
