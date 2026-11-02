package net.atmorachad.noxious.datagen;


import net.atmorachad.noxious.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @SafeVarargs
    private final void addToTags(ResourceKey<Block> block, TagKey<Block>... tags) {
        for (TagKey<Block> tag : tags) {
            builder(tag).add(block);
        }
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.getRK(ModBlocks.GOLD_BARS));

        addToTags(
                ModBlocks.getRK(ModBlocks.GOLD_BARS),
                BlockTags.BLOCKS_LAVA_FIRE_SPREAD,
                BlockTags.SPEEDS_UP_ZOMBIE_VILLAGER_CURING,
                BlockTags.ICE_MELTS_WHEN_DESTROYED_ABOVE,
                BlockTags.DRAGON_IMMUNE,
                BlockTags.BLOCKS_MOTION,
                BlockTags.BLOCKS_MOTION_IN_HEIGHTMAP,
                BlockTags.CAUSES_SUFFOCATION,
                BlockTags.BLOCKS_MOTION_NO_LEAVES,
                BlockTags.BLOCKS_DOLPHIN_JUMP,
                BlockTags.BLOCKS_MOTION_IN_HEIGHTMAP_NO_LEAVES,
                BlockTags.ENTITIES_CAN_TELEPORT_TO,
                BlockTags.BARS,
                BlockTags.BLOCKS_FLUID_FLOW
        );

        addToTags(
                ModBlocks.getRK(ModBlocks.GOLD_BARS),
                BlockTags.BLOCKS_MOTION_IN_HEIGHTMAP_NO_LEAVES,
                BlockTags.BLOCKS_MOTION_NO_LEAVES,
                BlockTags.ENTITIES_CAN_TELEPORT_TO,
                BlockTags.ICE_MELTS_WHEN_DESTROYED_ABOVE,
                BlockTags.BLOCKS_MOTION_IN_HEIGHTMAP,
                BlockTags.BLOCKS_MOTION,
                BlockTags.BLOCKS_DOLPHIN_JUMP,
                BlockTags.BLOCKS_FLUID_FLOW,
                BlockTags.CHAINS,
                BlockTags.BLOCKS_LAVA_FIRE_SPREAD,
                BlockTags.CAUSES_SUFFOCATION

       );
    }
}
