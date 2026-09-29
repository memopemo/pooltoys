package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import memopemo.pooltoys.Otter;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class OtterModel extends BasePooltoyModel<Otter> {
	
	public OtterModel(ModelPart root) {
		super(root);
		root.getChild("body");
	}

	public static LayerDefinition getTexturedModelData() {
		meshDefinition = new MeshDefinition();
		PartDefinition partdefinition = meshDefinition.getRoot();
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -12.0F, -18.0F, 14.0F, 12.0F, 23.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.0F, -13.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		body.addOrReplaceChild("crown_r1", CubeListBuilder.create().texOffs(92, 91).addBox(-3.0F, -2.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -25.0F, -23.0F, -1.2654F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(44, 100).addBox(-1.0F, -3.5F, -7.5F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -17.0F, -22.0F, -1.2216F, -0.4971F, -0.1719F));

		body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(24, 100).addBox(-3.0F, -3.5F, -7.5F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -17.0F, -22.0F, -1.2216F, 0.4971F, 0.1719F));

		body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(36, 74).addBox(-5.0F, -5.0F, -4.0F, 6.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(74, 0).addBox(13.0F, -5.0F, -4.0F, 6.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -7.0F, 0.0F, 0.48F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(84, 55).addBox(-1.0F, -5.0F, -4.0F, 5.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 75).addBox(-18.0F, -5.0F, -4.0F, 5.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -5.0F, 6.0F, 0.48F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 91).addBox(-2.0F, -4.0F, -4.0F, 3.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(68, 90).addBox(17.0F, -4.0F, -4.0F, 3.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -5.0F, -15.0F, -0.4363F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(68, 74).addBox(-4.0F, -4.0F, -4.0F, 6.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(74, 18).addBox(-18.0F, -4.0F, -4.0F, 6.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -8.0F, -10.0F, 1.2654F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(86, 35).addBox(-3.0F, -3.0F, 0.5F, 6.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 18.5F, -0.1745F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 57).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 10.0F, 0.0436F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(46, 35).addBox(-5.0F, -5.0F, 0.5F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 2.5F, 0.5236F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(27, 93).addBox(-4.0F, -3.0F, -2.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.4573F, -15.5321F, -1.2654F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 35).addBox(-6.0F, -5.0F, -9.0F, 12.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, -20.0F, -1.2654F, 0.0F, 0.0F));
		body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(46, 55).addBox(-5.0F, -4.5F, -3.5F, 10.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.5F, -19.5F, -0.6109F, 0.0F, 0.0F));

        //replace '512' with size of your texture.
		return LayerDefinition.create(meshDefinition, 128, 128);
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, int f) {
		getTexturedModelData().bakeRoot().render(poseStack, vertexConsumer, i, j);
	}

}
