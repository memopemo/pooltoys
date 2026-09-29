package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import memopemo.pooltoys.Tanuki;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class TanukiModel extends BasePooltoyModel<Tanuki> {
	
    //paste the model part definitions here like this one:
	private final ModelPart body;
	private final ModelPart bone1;

	public TanukiModel(ModelPart root) {
		super(root);
		this.body = root.getChild("body");
		this.bone1 = this.body.getChild("bone1");
	}

	public static LayerDefinition getTexturedModelData() {
		meshDefinition = new MeshDefinition();
		PartDefinition partdefinition = meshDefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 0.0F));

		body.addOrReplaceChild("leg_r_r1", CubeListBuilder.create().texOffs(102, 0).addBox(-20.0F, -7.0F, -11.0F, 10.0F, 11.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, 20.0F, -0.2657F, -0.1685F, 0.0456F));

		body.addOrReplaceChild("arm_r_r1", CubeListBuilder.create().texOffs(60, 119).addBox(-20.0F, -8.0F, -11.0F, 10.0F, 12.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, -20.0F, 0.1415F, 0.3892F, 0.054F));

		body.addOrReplaceChild("leg_l_r1", CubeListBuilder.create().texOffs(90, 87).addBox(10.0F, -7.0F, -11.0F, 10.0F, 11.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 20.0F, -0.2657F, 0.1685F, -0.0456F));

		body.addOrReplaceChild("arm_l_r1", CubeListBuilder.create().texOffs(0, 105).addBox(10.0F, -8.0F, -11.0F, 10.0F, 12.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, -20.0F, 0.1415F, -0.3892F, -0.054F));

		body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -16.0F, -16.0F, 18.0F, 19.0F, 33.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition face = body.addOrReplaceChild("face", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.0F, -1.0F, -0.0873F, 0.0F, 0.0F));

		face.addOrReplaceChild("ear_r_r1", CubeListBuilder.create().texOffs(134, 42).addBox(0.0F, -3.0F, -2.5F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -27.6423F, -12.21F, -0.0457F, -0.8249F, -0.0948F));

		face.addOrReplaceChild("nose_r1", CubeListBuilder.create().texOffs(60, 105).addBox(-4.0F, -3.0F, -4.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -18.0F, -21.0F, 0.2182F, 0.0F, 0.0F));

		face.addOrReplaceChild("ear_l_r1", CubeListBuilder.create().texOffs(134, 32).addBox(-2.0F, -3.0F, -2.5F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -27.6423F, -12.21F, -0.0457F, 0.8249F, 0.0948F));

		face.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(0, 137).addBox(-7.0F, -14.0F, -14.0F, 14.0F, 14.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, -7.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition bone1 = body.addOrReplaceChild("bone1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, 14.0F, -0.3054F, 0.0F, 0.0F));

		bone1.addOrReplaceChild("tail1_r1", CubeListBuilder.create().texOffs(102, 32).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition bone2 = bone1.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 0.0F));

		bone2.addOrReplaceChild("tail2_r1", CubeListBuilder.create().texOffs(120, 140).addBox(-6.0F, -8.0F, -2.0F, 12.0F, 11.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, -11.0F));

		bone3.addOrReplaceChild("tail3_r1", CubeListBuilder.create().texOffs(90, 52).addBox(-8.0F, -8.0F, 3.0F, 16.0F, 19.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F));

		PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		bone4.addOrReplaceChild("tail4_r1", CubeListBuilder.create().texOffs(0, 52).addBox(-11.0F, -20.0F, -4.0F, 22.0F, 30.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.6581F, 0.0F, 0.0F));

		PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -5.0F));

		bone5.addOrReplaceChild("tail5_r1", CubeListBuilder.create().texOffs(120, 119).addBox(-8.0F, -19.0F, -1.0F, 16.0F, 5.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.7453F, 0.0F, 0.0F));

        //replace '512' with size of your texture.
		return LayerDefinition.create(meshDefinition, 256, 256);
	}

//	@Override
//	public void setupAnim(PooltoyRenderState ptRenderState) {
//		super.setupAnim(ptRenderState);
//		if(ptRenderState.hasSomeoneSitting)
//			bone1.xRot = -0.5F;
//		else
//			bone1.xRot = -0.3054F;
//	}
	public void setupAnim(Tanuki t, float f, float g, float h, float i, float j){
		if(t.hasControllingPassenger())
			bone1.xRot = -0.5F;
		else
			bone1.xRot = -0.3054F;
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, int f) {
		getTexturedModelData().bakeRoot().render(poseStack, vertexConsumer, i, j);
	}

}
