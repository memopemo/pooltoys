package memopemo.pooltoys.client;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class FlamingoModel extends BasePooltoyModel {
	
    //paste the model part definitions here like this one:

	public FlamingoModel(ModelPart root) {
		super(root);
		root.getChild("body");
	}

	public static LayerDefinition getTexturedModelData() {
		meshDefinition = new MeshDefinition();
		PartDefinition partdefinition = meshDefinition.getRoot();
		
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -12.0F, -21.0F, 28.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(14.0F, -12.0F, -19.0F, 2.0F, 12.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(72, 88).addBox(4.0F, -12.0F, -9.0F, 10.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 24).addBox(-14.0F, -12.0F, -1.0F, 28.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(60, 48).addBox(-16.0F, -12.0F, -19.0F, 2.0F, 12.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(72, 108).addBox(-14.0F, -12.0F, -9.0F, 10.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(80, 0).addBox(16.0F, -13.0F, -10.0F, 5.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(80, 24).addBox(-21.0F, -13.0F, -10.0F, 5.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(120, 48).addBox(-3.0F, -8.0F, -1.0F, 6.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -34.0F, -31.0F, 2.618F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(38, 88).addBox(-4.0F, -14.0F, -1.0F, 8.0F, 15.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -38.0F, -19.0F, 2.0071F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 113).addBox(-4.0F, -10.0F, 0.0F, 8.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -27.0F, -13.0F, 0.3054F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(108, 88).addBox(-4.0F, -14.0F, 0.0F, 8.0F, 15.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.0F, -21.0F, -0.6109F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 88).addBox(-4.0F, -14.0F, -1.0F, 8.0F, 14.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 6.0F, -0.6545F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(38, 112).addBox(-4.0F, -11.0F, -1.0F, 8.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -34.0F, -13.0F, 1.0472F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(108, 110).addBox(-4.0F, -1.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.5F, -18.0F, 0.1745F, 0.0F, 0.0F));

        //replace '512' with size of your texture.
		return LayerDefinition.create(meshDefinition, 256, 256);
	}

}
