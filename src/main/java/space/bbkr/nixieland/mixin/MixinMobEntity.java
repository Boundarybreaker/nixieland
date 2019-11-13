package space.bbkr.nixieland.mixin;

import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class MixinMobEntity extends LivingEntity {
	protected MixinMobEntity(EntityType<? extends LivingEntity> type, World world) {
		super(type, world);
	}

	@Inject(method = "getLimitPerChunk", at = @At("HEAD"), cancellable = true)
	private void getSpawnLimit(CallbackInfoReturnable<Integer> info) {
		if (this.getType().getCategory().equals(EntityCategory.MONSTER)) info.setReturnValue(1);
	}
}
