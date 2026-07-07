package memopemo.pooltoys.client.mixin;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.AbstractBoat;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.authlib.GameProfile;

import memopemo.pooltoys.Pooltoy;

@Mixin(LocalPlayer.class)
public class ExampleClientMixin extends AbstractClientPlayer {
	public ExampleClientMixin(ClientLevel clientLevel, GameProfile gameProfile) {
		super(clientLevel, gameProfile);
	}

	@Inject(at = @At("TAIL"), method = "rideTick")
	public void rideTick(CallbackInfo ci) {
		LocalPlayer localPlayer = (LocalPlayer) (Object) this;
		Entity vehicle = localPlayer.getControlledVehicle();
		if (vehicle instanceof Pooltoy pooltoy) 
		{
			pooltoy.setInput(localPlayer.input.keyPresses.left(), localPlayer.input.keyPresses.right(),
					localPlayer.input.keyPresses.forward(), localPlayer.input.keyPresses.backward());
		}
	}
}