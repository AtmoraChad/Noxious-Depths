package net.atmorachad.noxious.block.blocks;

import net.atmorachad.noxious.block.ModBlocks;
import net.atmorachad.noxious.tag.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class SulfurFireBlock extends FireBlock {
    public SulfurFireBlock(final BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected BlockState updateShape(
            final BlockState state,
            final LevelReader level,
            final ScheduledTickAccess ticks,
            final BlockPos pos,
            final Direction directionToNeighbour,
            final BlockPos neighbourPos,
            final BlockState neighbourState,
            final RandomSource random
    ) {
        if (!this.canSurvive(state, level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }

        return this.getStateForPlacement(level, pos)
                .setValue(FireBlock.AGE, state.getValue(FireBlock.AGE));
    }

    @Override
    protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
        return canSurviveOnBlock(level.getBlockState(pos.below()));
    }

    public static boolean canSurviveOnBlock(final BlockState state) {
        return state.is(ModTags.Blocks.SULFUR_FIRE_BASE_BLOCKS);
    }

    @Override
    protected boolean canBurn(final BlockState state) {
        return true;
    }
}