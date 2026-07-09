// Unused
// No longer extending AbstractBoat
package memopemo.pooltoys.client;

import net.minecraft.client.model.object.boat.AbstractBoatModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.BoatRenderer;

public class OldTemplateEntityModel extends AbstractBoatModel {
    //paste the model part definitions here like this one:
	//private final ModelPart bone;

	public OldTemplateEntityModel(ModelPart root) {
		super(root);
		//this.bone = root.getChild("body"); //needs defined "body"
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
        /*
        copy and paste the block definitions in the block bench's exported .java file here, like this one
        
        PartDefinition HornL_r1 = bone.addOrReplaceChild("HornL_r1", CubeListBuilder.create().texOffs(0, 21).mirror().addBox(-1.5F, -6.5F, -2.4F, 4.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).addBox(12.5F, -6.5F, -2.4F, 4.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.5F, -32.3416F, -37.9644F, -1.8326F, 0.0F, 0.0F));
         
        */

        // keep this. requires paddles.
		partdefinition.addOrReplaceChild("left_paddle", CubeListBuilder.create().addBox(0,0,0,0,0,0), PartPose.offset(0, 0, 0));
        partdefinition.addOrReplaceChild("right_paddle", CubeListBuilder.create().addBox(0,0,0,0,0,0), PartPose.offset(0, 0, 0));

        //replace '512' with size of your texture.
		return LayerDefinition.create(meshdefinition, 512, 512);
	}

}
