package net.atmorachad.noxious.mixin;

import net.atmorachad.noxious.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin {

    @Inject(
            method = "getGrowthSpeed",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void noxious$increaseGrowthSpeed(
            Block block,
            BlockGetter level,
            BlockPos pos,
            CallbackInfoReturnable<Float> cir
    ) {
        if (level.getBlockState(pos.below()).is(ModBlocks.ENRICHED_FARMLAND)) {
            cir.setReturnValue(cir.getReturnValue() * 2.0F);
        }
    }
}
