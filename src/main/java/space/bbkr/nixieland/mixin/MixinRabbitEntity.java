package space.bbkr.nixieland.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.RabbitEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RabbitEntity.class)
public class MixinRabbitEntity {
	@Inject(method = "damage", at = @At("HEAD"), cancellable = true)
	private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
		if (source.equals(DamageSource.FALL)) info.setReturnValue(false);
	}
}
