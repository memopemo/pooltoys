package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import memopemo.pooltoys.Wolf;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class WolfModel extends BasePooltoyModel<Wolf> {

	public WolfModel(ModelPart root) {
		super(root);
		root.getChild("body");
	}

	public static LayerDefinition getTexturedModelData() {
		meshDefinition = new MeshDefinition();
		PartDefinition partdefinition = meshDefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(84, 60).addBox(-6.0F, -24.0F, -22.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(128, 86).addBox(-4.0F, -18.0F, -26.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 86).addBox(5.0F, -7.0F, -27.0F, 8.0F, 7.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(40, 86).addBox(-13.0F, -7.0F, -27.0F, 8.0F, 7.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(80, 104).addBox(7.0F, -14.0F, 12.0F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 105).addBox(-15.0F, -14.0F, 12.0F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(68, 105).addBox(-1.0F, 0.0F, 6.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-7.0F, -16.0F, -14.0F, 14.0F, 16.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition tail3_r1 = body.addOrReplaceChild("tail3_r1", CubeListBuilder.create().texOffs(32, 105).addBox(-5.0F, -1.0F, 24.0F, 10.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.0F, 15.0F, 1.5272F, 0.0F, 0.0F));

		PartDefinition tail2_r1 = body.addOrReplaceChild("tail2_r1", CubeListBuilder.create().texOffs(9, 47).addBox(-6.0F, -2.0F, -1.0F, 12.0F, 12.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -23.0F, 12.0F, 1.3526F, 0.0F, 0.0F));

		PartDefinition tail1_r1 = body.addOrReplaceChild("tail1_r1", CubeListBuilder.create().texOffs(84, 84).addBox(-5.0F, -10.0F, -1.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 14.0F, 0.6981F, 0.0F, 0.0F));

		PartDefinition thigh_r_r1 = body.addOrReplaceChild("thigh_r_r1", CubeListBuilder.create().texOffs(84, 30).addBox(-5.0F, -8.5F, -6.5F, 10.0F, 17.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(84, 0).addBox(13.0F, -8.5F, -6.5F, 10.0F, 17.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -15.5F, 8.5F, 0.2182F, 0.0F, 0.0F));

		PartDefinition hair_r1 = body.addOrReplaceChild("hair_r1", CubeListBuilder.create().texOffs(82, 126).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -23.0F, -19.0F, -1.1345F, 0.0F, 0.0F));

		PartDefinition hair_r2 = body.addOrReplaceChild("hair_r2", CubeListBuilder.create().texOffs(60, 126).addBox(-2.0F, -4.0F, -5.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, -20.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition ear_r1 = body.addOrReplaceChild("ear_r1", CubeListBuilder.create().texOffs(14, 127).addBox(-3.0F, -7.0F, -1.0F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -22.0F, -20.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition ear_r2 = body.addOrReplaceChild("ear_r2", CubeListBuilder.create().texOffs(0, 127).addBox(-1.0F, -7.0F, -1.0F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -22.0F, -20.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 123).addBox(-5.0F, -2.0F, -5.0F, 6.0F, 14.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(112, 104).addBox(13.0F, -2.0F, -5.0F, 6.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -10.0F, -8.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cheek_l_r1 = body.addOrReplaceChild("cheek_l_r1", CubeListBuilder.create().texOffs(120, 126).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(104, 126).addBox(-15.0F, -5.0F, -1.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -17.0F, -20.0F, -0.7854F, 0.0F, 0.0F));
        //replace '512' with size of your texture.
		return LayerDefinition.create(meshDefinition, 160, 160);
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, int f) {
		getTexturedModelData().bakeRoot().render(poseStack, vertexConsumer, i, j);
	}

}
