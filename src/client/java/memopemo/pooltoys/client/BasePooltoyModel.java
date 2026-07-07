package memopemo.pooltoys.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
/**
 * Requires all model's root to be named "body"
 */
public class BasePooltoyModel extends EntityModel<PooltoyRenderState> {

	protected static MeshDefinition meshDefinition;

	public BasePooltoyModel(ModelPart root) {
		super(root);
		root.getChild("body");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = new MeshDefinition();
		return LayerDefinition.create(meshdefinition, 512, 512);
	}

}
