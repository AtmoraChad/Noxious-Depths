
package net.atmorachad.noxious.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class EnrichedFarmlandBlock extends Block {
    private final Block baseBlock;
    private static final VoxelShape SHAPE = Block.column(16.0, 0.0, 15.0);

    public EnrichedFarmlandBlock(final Block baseBlock, final BlockBehaviour.Properties properties) {
        super(properties);
        this.baseBlock = baseBlock;
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
            final net.minecraft.util.RandomSource random
    ) {
        if (directionToNeighbour == Direction.UP && !state.canSurvive(level, pos)) {
            ticks.scheduleTick(pos, this, 1);
        }

        return super.updateShape(state, level, ticks, pos, directionToNeighbour,
                neighbourPos, neighbourState, random);
    }

    @Override
    protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
        BlockState aboveState = level.getBlockState(pos.above());
        return !aboveState.isSolid() || shouldMaintainFarmland(level, pos);
    }

    @Override
    public BlockState getStateForPlacement(final net.minecraft.world.item.context.BlockPlaceContext context) {
        return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos())
                ? this.baseBlock.defaultBlockState()
                : super.getStateForPlacement(context);
    }

    @Override
    protected boolean useShapeForLightOcclusion(final BlockState state) {
        return true;
    }

    @Override
    protected VoxelShape getShape(final BlockState state, final net.minecraft.world.level.BlockGetter level,
                                  final BlockPos pos, final CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void tick(final BlockState state, final ServerLevel level,
                        final BlockPos pos, final net.minecraft.util.RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            this.turnToBaseBlock(null, state, level, pos);
        }
    }

    @Override
    public void fallOn(final Level level, final BlockState state, final BlockPos pos,
                       final Entity entity, final double fallDistance) {
        if (level instanceof ServerLevel serverLevel
                && level.getRandom().nextFloat() < fallDistance - 0.5
                && entity instanceof LivingEntity) {

            boolean canTrample = entity instanceof Player player
                    ? !level.getServer().isUnderSpawnProtection(serverLevel, pos, player)
                    : serverLevel.getGameRules().get(GameRules.MOB_GRIEFING);

            if (canTrample && entity.getBbWidth() * entity.getBbWidth()
                    * entity.getBbHeight() > 0.512F) {
                this.turnToBaseBlock(entity, state, level, pos);
            }
        }

        super.fallOn(level, state, pos, entity, fallDistance);
    }

    public void turnToBaseBlock(final @Nullable Entity sourceEntity,
                                final BlockState state, final Level level, final BlockPos pos) {
        BlockState newState = pushEntitiesUp(state, this.baseBlock.defaultBlockState(), level, pos);
        level.setBlockAndUpdate(pos, newState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(sourceEntity, newState));
    }

    private static boolean shouldMaintainFarmland(final net.minecraft.world.level.BlockGetter level,
                                                  final BlockPos pos) {
        return level.getBlockState(pos.above()).is(BlockTags.MAINTAINS_FARMLAND);
    }

    @Override
    protected boolean isPathfindable(final BlockState state, final PathComputationType type) {
        return false;
    }
}
