package memopemo.pooltoys.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import memopemo.pooltoys.Pooltoy;
import memopemo.pooltoys.Pooltoys;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;

/**
 * Requires all model's root to be named "body"
 */
public abstract class BasePooltoyModel<T extends Pooltoy> extends EntityModel<T> {
	protected static MeshDefinition meshDefinition;

	public BasePooltoyModel(ModelPart root) {
		root.getChild("body");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = new MeshDefinition();
		return LayerDefinition.create(meshdefinition, 512, 512);
	}



	@Override
	public void setupAnim(Pooltoy entity, float f, float g, float h, float i, float j) {

	}
}
