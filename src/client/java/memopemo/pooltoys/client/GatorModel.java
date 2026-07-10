package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import memopemo.pooltoys.Gator;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class GatorModel extends BasePooltoyModel<Gator> {
	
    //paste the model part definitions here like this one:

	public GatorModel(ModelPart root) {
		super(root);
		root.getChild("body");
	}

	public static LayerDefinition getTexturedModelData() {
		meshDefinition = new MeshDefinition();
		PartDefinition partdefinition = meshDefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -7.0F, -11.0F, 14.0F, 7.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(46, 29).addBox(-5.0F, -5.0F, -28.0F, 10.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 45).addBox(-5.0F, -6.0F, 11.0F, 10.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(31, 52).addBox(-3.0F, -5.0F, 22.0F, 6.0F, 5.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(50, 0).addBox(-6.0F, -11.0F, -19.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(50, 0).mirror().addBox(1.0F, -11.0F, -19.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 29).addBox(-7.0F, -7.0F, -20.0F, 14.0F, 7.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(37, 29).mirror().addBox(-5.0F, -7.0F, -27.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(37, 29).addBox(2.0F, -7.0F, -27.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(31, 45).addBox(-2.0F, -10.0F, -15.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(31, 45).addBox(-2.0F, -9.0F, -10.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(31, 45).addBox(-2.0F, -9.0F, 11.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(31, 45).addBox(-2.0F, -8.0F, 17.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(31, 45).addBox(-2.0F, -7.0F, 24.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(50, 8).mirror().addBox(0.0F, -4.0F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, -2.0F, 7.0F, 0.0F, -0.2618F, 0.0F));

		body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(1, 9).addBox(-6.0F, -4.0F, -3.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(1, 9).mirror().addBox(13.0F, -4.0F, -3.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.0F, -3.0F, 14.0F, 0.829F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -2.0F, -2.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -3.0F, -8.0F, 0.0372F, -0.4293F, -0.1922F));

		body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -2.0F, -2.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, -3.0F, -8.0F, 0.0372F, 0.4293F, 0.1922F));

		body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 62).addBox(-6.0F, -2.0F, -2.0F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -2.0F, -8.0F, 0.0F, -1.4399F, 0.0F));

		body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(0.0F, -2.0F, -2.0F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.0F, -2.0F, -8.0F, 0.0F, 1.4399F, 0.0F));

		body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(50, 8).addBox(-5.0F, -4.0F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -2.0F, 7.0F, 0.0F, 0.2618F, 0.0F));

        //replace '512' with size of your texture.
		return LayerDefinition.create(meshDefinition, 128, 128);
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
		getTexturedModelData().bakeRoot().render(poseStack, vertexConsumer, i, j , f,g,h,k);
	}

}
