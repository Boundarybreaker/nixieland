package space.bbkr.nixieland.mixin;

import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.WorldView;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(SpawnHelper.class)
public class MixinSpawnHelper {
	private static Random random = new Random();

	@Inject(method = "canSpawn", at = @At("HEAD"), cancellable = true)
	private static void cancelSpawns(SpawnRestriction.Location location, WorldView world, BlockPos pos, EntityType<?> type, CallbackInfoReturnable<Boolean> info) {
		if (type.equals(EntityType.CREEPER) || type.equals(EntityType.PILLAGER)) info.setReturnValue(false);
		if (type.getCategory() == EntityCategory.MONSTER) {
			if (random.nextInt(10) == 0) {
				if (!world.isSkyVisible(pos) && world.getDimension().getType() == DimensionType.OVERWORLD) info.setReturnValue(false);
			} else {
				info.setReturnValue(false);
			}
		}
	}
}
