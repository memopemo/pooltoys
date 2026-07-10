package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import memopemo.pooltoys.Shark;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class SharkModel extends BasePooltoyModel<Shark> {

	public SharkModel(ModelPart root) {
		super(root);
		root.getChild("body");
	}

	public static LayerDefinition getTexturedModelData() {
		meshDefinition = new MeshDefinition();
		PartDefinition partdefinition = meshDefinition.getRoot();
		
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -14.0F, -22.0F, 12.0F, 14.0F, 30.0F, new CubeDeformation(0.0F))
		.texOffs(54, 0).addBox(-5.0F, -12.0F, 8.0F, 10.0F, 11.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(54, 44).addBox(-3.0F, -11.0F, 18.0F, 6.0F, 8.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.0F, -10.0F, -28.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(41, 71).mirror().addBox(-7.0F, -3.0F, -4.0F, 12.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.0F, -3.0F, -2.0F, 0.0F, -0.3491F, 0.3491F));

		body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(54, 22).mirror().addBox(-1.0F, -3.0F, -2.0F, 7.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-8.0F, -3.0F, 17.0F, 0.0F, 0.4363F, -0.3491F));

		body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, 19.0F, -0.5672F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 58).addBox(-2.0F, -3.5F, -4.0F, 4.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.5F, 0.0F, -0.5672F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(54, 22).addBox(-6.0F, -3.0F, -2.0F, 7.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -3.0F, 17.0F, 0.0F, -0.4363F, 0.3491F));

		body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(41, 71).addBox(-5.0F, -3.0F, -4.0F, 12.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -3.0F, -2.0F, 0.0F, 0.3491F, -0.3491F));

		body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(36, 44).addBox(-2.0F, -7.5F, -2.5F, 4.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 30.5F, 1.0036F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(24, 59).addBox(-2.0F, -5.5F, -3.5F, 4.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.5F, 29.5F, -0.5672F, 0.0F, 0.0F));

        //replace '512' with size of your texture.
		return LayerDefinition.create(meshDefinition, 128, 128);
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
		getTexturedModelData().bakeRoot().render(poseStack, vertexConsumer, i, j , f,g,h,k);
	}

}
