package space.bbkr.nixieland.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity extends LivingEntity {

	protected MixinPlayerEntity(EntityType<? extends LivingEntity> type, World world) {
		super(type, world);
	}

	@Inject(method = "attack", at = @At("HEAD"), cancellable = true)
	public void cancelAttack(Entity entity, CallbackInfo info) {
		if (entity instanceof TameableEntity) {
			TameableEntity tameable = (TameableEntity)entity;
			if (tameable.isOwner(this)) info.cancel();
		} else if (entity instanceof HorseBaseEntity) {
			HorseBaseEntity horse = (HorseBaseEntity)entity;
			if (this.getUuid().equals(horse.getOwnerUuid())) info.cancel();
		}
	}
}
