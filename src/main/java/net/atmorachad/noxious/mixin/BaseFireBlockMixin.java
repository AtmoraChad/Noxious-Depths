package net.atmorachad.noxious.mixin;

import net.atmorachad.noxious.block.ModBlocks;
import net.atmorachad.noxious.tag.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BaseFireBlock.class)
public class BaseFireBlockMixin {

    @Inject(method = "getState", at = @At("HEAD"), cancellable = true)
    private static void noxious$placeSulfurFire(
            final BlockGetter level,
            final BlockPos pos,
            final CallbackInfoReturnable<BlockState> cir
    ) {
        BlockState belowState = level.getBlockState(pos.below());

        if (belowState.is(ModTags.Blocks.SULFUR_FIRE_BASE_BLOCKS)) {
            cir.setReturnValue(ModBlocks.SULFUR_FIRE.defaultBlockState());
        }
    }
}