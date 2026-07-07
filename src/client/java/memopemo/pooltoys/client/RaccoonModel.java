package memopemo.pooltoys.client;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class RaccoonModel extends BasePooltoyModel{
    //paste the model part definitions here like this one:
	//private final ModelPart bone;

	public RaccoonModel(ModelPart root) {
		super(root);
		root.getChild("body");
    }

	public static LayerDefinition getTexturedModelData() {
		meshDefinition = new MeshDefinition();
		PartDefinition partdefinition = meshDefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition pooltoy = body.addOrReplaceChild("pooltoy", CubeListBuilder.create().texOffs(0, 48).addBox(-8.0F, -16.0F, -14.0F, 16.0F, 16.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(96, 0).addBox(8.0F, -8.0F, -24.0F, 8.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(96, 24).addBox(-16.0F, -8.0F, -24.0F, 8.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		pooltoy.addOrReplaceChild("L leg", CubeListBuilder.create().texOffs(0, 116).addBox(-6.0F, -7.0F, -10.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(48, 108).addBox(-4.0F, -3.0F, 2.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, -5.0F, 16.0F));

		pooltoy.addOrReplaceChild("L_leg2", CubeListBuilder.create().texOffs(48, 120).addBox(-6.0F, -7.0F, -10.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 140).addBox(-4.0F, -3.0F, 2.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.0F, -5.0F, 16.0F));

		PartDefinition Tail = pooltoy.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(88, 88).addBox(-8.0F, -33.0F, 17.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		Tail.addOrReplaceChild("Tail_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -23.0F, -12.0F, 24.0F, 24.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -40.0F, 17.0F, 1.0472F, 0.0F, 0.0F));

		Tail.addOrReplaceChild("Tail_r2", CubeListBuilder.create().texOffs(88, 48).addBox(-10.0F, -10.0F, -10.0F, 20.0F, 20.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -36.0F, 20.0F, 0.5236F, 0.0F, 0.0F));

		Tail.addOrReplaceChild("Tail_r3", CubeListBuilder.create().texOffs(96, 120).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -17.0F, 23.0F, -0.5236F, 0.0F, 0.0F));

		Tail.addOrReplaceChild("Tail_r4", CubeListBuilder.create().texOffs(48, 92).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, 18.0F, -1.0036F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 92).addBox(-6.0F, -7.0F, -5.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(144, 0).addBox(-6.0F, -7.0F, -7.0F, 12.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -17.0F, -15.0F));

		head.addOrReplaceChild("L ear_r1", CubeListBuilder.create().texOffs(144, 20).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -7.0F, 4.0F, -0.3491F, -0.3491F, 0.0F));

		head.addOrReplaceChild("L_ear_r2", CubeListBuilder.create().texOffs(144, 8).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -7.0F, 4.0F, -0.3491F, 0.3491F, 0.0F));

		head.addOrReplaceChild("muzzle_r1", CubeListBuilder.create().texOffs(24, 140).addBox(-4.0F, -6.0F, -4.0F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -5.0F, -0.1745F, 0.0F, 0.0F));

        //replace '512' with size of your texture.
		return LayerDefinition.create(meshDefinition, 256, 256);
	}
}
