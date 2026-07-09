package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import memopemo.pooltoys.Tanuki;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.Identifier;

public class TanukiRenderer extends BasePooltoyRenderer<Tanuki>{

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Pooltoys.MOD_ID, "tanuki_boba.png");
    private static final Identifier RED = Identifier.fromNamespaceAndPath(Pooltoys.MOD_ID, "tanuki_red_panda.png");
    private static final Identifier GREEN = Identifier.fromNamespaceAndPath(Pooltoys.MOD_ID, "tanuki_matcha.png");
    private static final Identifier PURPLE = Identifier.fromNamespaceAndPath(Pooltoys.MOD_ID, "tanuki_taro.png");

    protected TanukiRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new TanukiModel(context.bakeLayer(PooltoysModelLayers.TANUKI));
        this.shadowRadius = 1.5f;
    }

    public void submit(PooltoyRenderState ptRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        Identifier customTexture = TEXTURE;
        switch (ptRenderState.hasSpecialName) {
            case 0:
                customTexture = RED;
                break;
            case 1:
                customTexture = PURPLE;
                break;
            case 2:
                customTexture = GREEN;
                break;
            default:
                break;
        }
        super.submit(ptRenderState, poseStack, submitNodeCollector, cameraRenderState, customTexture);
   }

   @Override
   public void extractRenderState(Tanuki tanuki, PooltoyRenderState renderState, float f) {
        super.extractRenderState(tanuki, renderState, f);
        renderState.hasSpecialName = -1;
        if(tanuki.hasCustomName())
        {
            if(tanuki.getCustomName().tryCollapseToString().equalsIgnoreCase("chili"))
                renderState.hasSpecialName = 0;
            if(tanuki.getCustomName().tryCollapseToString().equalsIgnoreCase("taro"))
                renderState.hasSpecialName = 1;
            if(tanuki.getCustomName().tryCollapseToString().equalsIgnoreCase("matcha"))
                renderState.hasSpecialName = 2;
        }

   }
}
