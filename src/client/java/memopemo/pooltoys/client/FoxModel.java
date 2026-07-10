package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import memopemo.pooltoys.Fox;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class FoxModel extends BasePooltoyModel<Fox> {
    //paste the model part definitions here like this one:
	private final ModelPart body;
	private final ModelPart arm_l;
	private final ModelPart paw_l;
	private final ModelPart arm_r;
	private final ModelPart paw_r;
	private final ModelPart head;
	private final ModelPart nose;
	private final ModelPart ear_l;
	private final ModelPart ear_r;
	private final ModelPart cheek_l;
	private final ModelPart cheek_r;
	private final ModelPart hair2;
	private final ModelPart hair1;
	private final ModelPart thigh_l;
	private final ModelPart leg_l;
	private final ModelPart thigh_r;
	private final ModelPart leg_r;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;

	public FoxModel(ModelPart root) {
		super(root);
		this.body = root.getChild("body");
		this.arm_l = this.body.getChild("arm_l");
		this.paw_l = this.arm_l.getChild("paw_l");
		this.arm_r = this.body.getChild("arm_r");
		this.paw_r = this.arm_r.getChild("paw_r");
		this.head = this.body.getChild("head");
		this.nose = this.head.getChild("nose");
		this.ear_l = this.head.getChild("ear_l");
		this.ear_r = this.head.getChild("ear_r");
		this.cheek_l = this.head.getChild("cheek_l");
		this.cheek_r = this.head.getChild("cheek_r");
		this.hair2 = this.body.getChild("hair2");
		this.hair1 = this.body.getChild("hair1");
		this.thigh_l = this.body.getChild("thigh_l");
		this.leg_l = this.thigh_l.getChild("leg_l");
		this.thigh_r = this.body.getChild("thigh_r");
		this.leg_r = this.thigh_r.getChild("leg_r");
		this.tail1 = this.body.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
		this.tail3 = this.tail2.getChild("tail3");
	}

	public static LayerDefinition getTexturedModelData() {
		meshDefinition = new MeshDefinition();
		PartDefinition partdefinition = meshDefinition.getRoot();
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -8.0F, -14.0F, 14.0F, 16.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(68, 105).addBox(-1.0F, 8.0F, 6.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition arm_l = body.addOrReplaceChild("arm_l", CubeListBuilder.create(), PartPose.offset(7.0F, -2.0F, -10.0F));

		PartDefinition arm_l_r1 = arm_l.addOrReplaceChild("arm_l_r1", CubeListBuilder.create().texOffs(112, 104).addBox(-1.0F, -3.0F, -4.0F, 6.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition paw_l = arm_l.addOrReplaceChild("paw_l", CubeListBuilder.create().texOffs(0, 86).addBox(-4.0F, -3.0F, -11.0F, 8.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 6.0F, -6.0F));

		PartDefinition arm_r = body.addOrReplaceChild("arm_r", CubeListBuilder.create(), PartPose.offset(-7.0F, -2.0F, -10.0F));

		PartDefinition arm_r_r1 = arm_r.addOrReplaceChild("arm_r_r1", CubeListBuilder.create().texOffs(32, 123).addBox(-5.0F, -3.0F, -4.0F, 6.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition paw_r = arm_r.addOrReplaceChild("paw_r", CubeListBuilder.create().texOffs(40, 86).addBox(-4.0F, -3.0F, -11.0F, 8.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 6.0F, -6.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(84, 60).addBox(-6.0F, -10.0F, -10.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -12.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(128, 86).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -10.0F));

		PartDefinition ear_l = head.addOrReplaceChild("ear_l", CubeListBuilder.create(), PartPose.offset(5.0F, -9.0F, -8.0F));

		PartDefinition ear_l_r1 = ear_l.addOrReplaceChild("ear_l_r1", CubeListBuilder.create().texOffs(0, 127).addBox(-1.0F, -6.0F, -1.0F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition ear_r = head.addOrReplaceChild("ear_r", CubeListBuilder.create(), PartPose.offset(-5.0F, -9.0F, -8.0F));

		PartDefinition ear_r_r1 = ear_r.addOrReplaceChild("ear_r_r1", CubeListBuilder.create().texOffs(14, 127).addBox(-3.0F, -6.0F, -1.0F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition cheek_l = head.addOrReplaceChild("cheek_l", CubeListBuilder.create(), PartPose.offset(7.0F, -3.0F, -5.0F));

		PartDefinition cheek_l_r1 = cheek_l.addOrReplaceChild("cheek_l_r1", CubeListBuilder.create().texOffs(120, 126).addBox(-1.0F, -3.0F, -3.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cheek_r = head.addOrReplaceChild("cheek_r", CubeListBuilder.create(), PartPose.offset(-7.0F, -3.0F, -5.0F));

		PartDefinition cheek_r_r1 = cheek_r.addOrReplaceChild("cheek_r_r1", CubeListBuilder.create().texOffs(104, 126).addBox(-1.0F, -3.0F, -3.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition hair2 = body.addOrReplaceChild("hair2", CubeListBuilder.create(), PartPose.offset(0.0F, -15.0F, -18.0F));

		PartDefinition hair2_r1 = hair2.addOrReplaceChild("hair2_r1", CubeListBuilder.create().texOffs(60, 126).addBox(-2.0F, -2.0F, -6.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition hair1 = body.addOrReplaceChild("hair1", CubeListBuilder.create(), PartPose.offset(0.0F, -15.0F, -17.0F));

		PartDefinition hair1_r1 = hair1.addOrReplaceChild("hair1_r1", CubeListBuilder.create().texOffs(82, 126).addBox(-2.0F, -2.0F, -7.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.1345F, 0.0F, 0.0F));

		PartDefinition thigh_l = body.addOrReplaceChild("thigh_l", CubeListBuilder.create(), PartPose.offset(8.0F, -0.5F, 13.5F));

		PartDefinition thigh_l_r1 = thigh_l.addOrReplaceChild("thigh_l_r1", CubeListBuilder.create().texOffs(84, 0).addBox(-1.0F, -3.5F, -7.5F, 10.0F, 17.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition leg_l = thigh_l.addOrReplaceChild("leg_l", CubeListBuilder.create(), PartPose.offset(6.0F, 2.5F, 10.5F));

		PartDefinition leg_l_r1 = leg_l.addOrReplaceChild("leg_l_r1", CubeListBuilder.create().texOffs(80, 104).addBox(-4.0F, -2.0F, -2.0F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition thigh_r = body.addOrReplaceChild("thigh_r", CubeListBuilder.create(), PartPose.offset(-8.0F, -0.5F, 13.5F));

		PartDefinition thigh_r_r1 = thigh_r.addOrReplaceChild("thigh_r_r1", CubeListBuilder.create().texOffs(84, 30).addBox(-9.0F, -3.5F, -7.5F, 10.0F, 17.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition leg_r = thigh_r.addOrReplaceChild("leg_r", CubeListBuilder.create(), PartPose.offset(-6.0F, 2.5F, 10.5F));

		PartDefinition leg_r_r1 = leg_r.addOrReplaceChild("leg_r_r1", CubeListBuilder.create().texOffs(0, 105).addBox(-4.0F, -2.0F, -2.0F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 13.0F));

		PartDefinition tail1_r1 = tail1.addOrReplaceChild("tail1_r1", CubeListBuilder.create().texOffs(84, 84).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 9.0F));

		PartDefinition tail2_r1 = tail2.addOrReplaceChild("tail2_r1", CubeListBuilder.create().texOffs(0, 44).addBox(-9.0F, -9.0F, -1.0F, 18.0F, 18.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0F, 19.0F));

		PartDefinition tail3_r1 = tail3.addOrReplaceChild("tail3_r1", CubeListBuilder.create().texOffs(32, 105).addBox(-6.0F, -6.0F, 0.0F, 12.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        //replace '512' with size of your texture.
		return LayerDefinition.create(meshDefinition, 160, 160);
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
		getTexturedModelData().bakeRoot().render(poseStack, vertexConsumer, i, j , f,g,h,k);
	}
}
