package memopemo.pooltoys.client;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class DragonModel extends BasePooltoyModel {
	public DragonModel(ModelPart root) {
		super(root);
		root.getChild("body");
	}

	public static LayerDefinition getTexturedModelData() {
		meshDefinition = new MeshDefinition();
		PartDefinition partdefinition = meshDefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, 1.8473F, -23.513F, 30.0F, 28.0F, 48.0F, new CubeDeformation(0.0F))
		.texOffs(18, 0).addBox(-1.0F, 0.8473F, 10.487F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.8473F, -0.487F));

		PartDefinition HornL_r1 = body.addOrReplaceChild("HornL_r1", CubeListBuilder.create().texOffs(0, 21).mirror().addBox(-1.5F, -6.5F, -2.4F, 4.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).addBox(12.5F, -6.5F, -2.4F, 4.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.5F, -32.3416F, -37.9644F, -1.8326F, 0.0F, 0.0F));

		PartDefinition WingL_r1 = body.addOrReplaceChild("WingL_r1", CubeListBuilder.create().texOffs(0, 112).addBox(-25.0F, -5.0F, -17.0F, 34.0F, 6.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, 23.8473F, -11.513F, 0.0F, 0.3927F, 0.0F));

		PartDefinition WingR_r1 = body.addOrReplaceChild("WingR_r1", CubeListBuilder.create().texOffs(0, 76).addBox(-9.0F, -5.0F, -17.0F, 34.0F, 6.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0F, 23.8473F, -11.513F, 0.0F, -0.3927F, 0.0F));

		PartDefinition LegL_r1 = body.addOrReplaceChild("LegL_r1", CubeListBuilder.create().texOffs(140, 156).addBox(-12.0F, -13.0F, -1.0F, 13.0F, 13.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(156, 42).addBox(33.0F, -13.0F, -1.0F, 13.0F, 13.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, 12.8473F, 20.487F, 0.6981F, 0.0F, 0.0F));

		PartDefinition ThighL_r1 = body.addOrReplaceChild("ThighL_r1", CubeListBuilder.create().texOffs(156, 0).addBox(-6.0F, -10.0F, -11.0F, 12.0F, 20.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(0, 148).addBox(36.0F, -10.0F, -11.0F, 12.0F, 20.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, 13.8473F, 17.487F, 0.2618F, 0.0F, 0.0F));

		PartDefinition Tail4_r1 = body.addOrReplaceChild("Tail4_r1", CubeListBuilder.create().texOffs(216, 76).addBox(-1.0F, -6.0F, -1.0F, 10.0F, 10.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -16.1527F, 40.487F, 2.4435F, 0.0F, 0.0F));

		PartDefinition Tail3_r1 = body.addOrReplaceChild("Tail3_r1", CubeListBuilder.create().texOffs(202, 190).addBox(-3.0F, -8.0F, -1.0F, 12.0F, 12.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.1527F, 43.487F, 1.6581F, 0.0F, 0.0F));

		PartDefinition Tail2_r1 = body.addOrReplaceChild("Tail2_r1", CubeListBuilder.create().texOffs(140, 190).addBox(-5.0F, -10.0F, -4.0F, 14.0F, 14.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 10.8473F, 38.487F, 0.9599F, 0.0F, 0.0F));

		PartDefinition Tail1_r1 = body.addOrReplaceChild("Tail1_r1", CubeListBuilder.create().texOffs(68, 156).addBox(-10.0F, -14.0F, -4.0F, 20.0F, 20.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.8473F, 25.487F, 0.4363F, 0.0F, 0.0F));

		PartDefinition ArmL_r1 = body.addOrReplaceChild("ArmL_r1", CubeListBuilder.create().texOffs(200, 114).addBox(-6.0F, -10.0F, -8.0F, 12.0F, 20.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(64, 192).addBox(32.0F, -10.0F, -8.0F, 12.0F, 20.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.0F, 2.8473F, -10.513F, -0.5236F, 0.0F, 0.0F));

		PartDefinition Head_r1 = body.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(128, 114).addBox(-9.0F, -12.0F, -9.0F, 18.0F, 24.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.1917F, -21.7748F, -1.8326F, 0.0F, 0.0F));

		PartDefinition Neck3_r1 = body.addOrReplaceChild("Neck3_r1", CubeListBuilder.create().texOffs(208, 150).addBox(-7.0F, -6.0F, -9.0F, 14.0F, 12.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.1527F, -34.013F, -2.138F, 0.0F, 0.0F));

		PartDefinition Neck2_r1 = body.addOrReplaceChild("Neck2_r1", CubeListBuilder.create().texOffs(0, 190).addBox(-8.0F, -8.0F, -9.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.1527F, -35.013F, -1.3963F, 0.0F, 0.0F));

		PartDefinition Neck1_r1 = body.addOrReplaceChild("Neck1_r1", CubeListBuilder.create().texOffs(128, 76).addBox(-11.0F, -8.0F, -22.5F, 22.0F, 16.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 21.8473F, -21.013F, -0.9163F, 0.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 512, 512);
	}

}
