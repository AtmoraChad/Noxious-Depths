package net.atmorachad.noxious.item.items;

import net.atmorachad.noxious.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SulfurItem extends Item {
    public SulfurItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Player player = context.getPlayer();

        if (state.is(Blocks.FARMLAND)
                && state.getValue(FarmlandBlock.MOISTURE) == 7) {

            if (!level.isClientSide()) {
                level.setBlockAndUpdate(pos, ModBlocks.ENRICHED_FARMLAND.defaultBlockState());

                if (player == null || !player.getAbilities().instabuild) {
                    context.getItemInHand().shrink(1);
                }
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
