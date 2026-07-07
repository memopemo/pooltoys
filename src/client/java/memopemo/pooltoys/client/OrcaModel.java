package memopemo.pooltoys.client;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;


public class OrcaModel extends BasePooltoyModel {
	private final ModelPart body;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart fluke;
	private final ModelPart Lfin;
	private final ModelPart Rfin;
	public OrcaModel(ModelPart root) {
        super(root);
		root.getChild("body");
		this.body = root.getChild("body");
		this.tail1 = this.body.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
		this.fluke = this.tail2.getChild("fluke");
		this.Lfin = this.body.getChild("Lfin");
		this.Rfin = this.body.getChild("Rfin");
	}

	public static LayerDefinition getTexturedModelData() {
		meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		PartDefinition body = partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(66, 40).addBox(-8.0F, -13.0F, -35.0F, 16.0F, 13.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-8.0F, -16.0F, -27.0F, 16.0F, 16.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(77, 75).addBox(-2.0F, -22.0F, -20.0F, 4.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 40).addBox(-7.0F, -8.0F, 0.0F, 14.0F, 13.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -3.0F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(50, 69).addBox(-5.0F, -3.0F, -0.5F, 10.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 11.5F));

		PartDefinition fluke = tail2.addOrReplaceChild("fluke", CubeListBuilder.create().texOffs(0, 11).addBox(4.0F, -4.0F, 7.0F, 7.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 69).addBox(-8.0F, -6.0F, 0.0F, 16.0F, 10.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-11.0F, -4.0F, 7.0F, 7.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 7.5F));

		PartDefinition Lfin = body.addOrReplaceChild("Lfin", CubeListBuilder.create().texOffs(56, 0).addBox(0.0F, -2.0F, -8.0F, 8.0F, 5.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -3.0F, -14.0F));

		PartDefinition Rfin = body.addOrReplaceChild("Rfin", CubeListBuilder.create().texOffs(34, 48).addBox(-8.0F, -2.0F, -9.0F, 8.0F, 5.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -3.0F, -13.0F));
       
		return LayerDefinition.create(meshDefinition, 128, 128);
	}
}

