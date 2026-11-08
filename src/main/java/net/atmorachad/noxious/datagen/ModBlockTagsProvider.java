package net.atmorachad.noxious.datagen;


import net.atmorachad.noxious.block.ModBlocks;
import net.atmorachad.noxious.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

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

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getRK(ModBlocks.GOLD_BARS))
                .add(ModBlocks.getRK(ModBlocks.GOLD_CHAIN))
                .add(ModBlocks.getRK(ModBlocks.SULFUR_LANTERN));

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.getRK(ModBlocks.SULFUR_CAMPFIRE));

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.getRK(ModBlocks.ENRICHED_FARMLAND));

        tag(ModTags.Blocks.SULFUR_FIRE_BASE_BLOCKS)
                .add(ModBlocks.getRK(Blocks.SULFUR))
                .add(ModBlocks.getRK(Blocks.POTENT_SULFUR));
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
                ModBlocks.getRK(ModBlocks.GOLD_CHAIN),
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

        addToTags(
                ModBlocks.getRK(ModBlocks.SULFUR_FIRE),
                BlockTags.REPLACEABLE,
                BlockTags.ENCHANTMENT_POWER_TRANSMITTER,
                BlockTags.CAT_DOES_NOT_TELEPORT_TO,
                BlockTags.WASHED_AWAY_BY_FLUIDS,
                BlockTags.FIRE,
                BlockTags.HAPPY_GHAST_AVOIDS,
                BlockTags.ENDERMAN_DOES_NOT_TELEPORT_TO,
                BlockTags.DANGEROUS_FOR_TELEPORTATION,
                BlockTags.DRAGON_TRANSPARENT
        );

        addToTags(
                ModBlocks.getRK(ModBlocks.SULFUR_CAMPFIRE),
                BlockTags.BLOCKS_DOLPHIN_JUMP,
                BlockTags.BLOCKS_FLUID_FLOW,
                BlockTags.BLOCKS_LAVA_FIRE_SPREAD,
                BlockTags.BLOCKS_MOTION_NO_LEAVES,
                BlockTags.ICE_MELTS_WHEN_DESTROYED_ABOVE,
                BlockTags.CAUSES_SUFFOCATION,
                BlockTags.BLOCKS_MOTION,
                BlockTags.CAT_DOES_NOT_TELEPORT_TO,
                BlockTags.CAMPFIRES,
                BlockTags.ENTITIES_CAN_TELEPORT_TO,
                BlockTags.BLOCKS_MOTION_IN_HEIGHTMAP_NO_LEAVES,
                BlockTags.DANGEROUS_FOR_TELEPORTATION,
                BlockTags.ENDERMAN_DOES_NOT_TELEPORT_TO,
                BlockTags.BLOCKS_MOTION_IN_HEIGHTMAP
        );

        addToTags(
                ModBlocks.getRK(ModBlocks.SULFUR_CAMPFIRE),
                BlockTags.LANTERNS,
                BlockTags.ICE_MELTS_WHEN_DESTROYED_ABOVE,
                BlockTags.BLOCKS_MOTION_NO_LEAVES,
                BlockTags.BLOCKS_LAVA_FIRE_SPREAD,
                BlockTags.BLOCKS_DOLPHIN_JUMP,
                BlockTags.BLOCKS_MOTION,
                BlockTags.BLOCKS_MOTION_IN_HEIGHTMAP_NO_LEAVES,
                BlockTags.ENTITIES_CAN_TELEPORT_TO,
                BlockTags.BLOCKS_FLUID_FLOW,
                BlockTags.BLOCKS_MOTION_IN_HEIGHTMAP,
                BlockTags.CAUSES_SUFFOCATION
        );

        addToTags(
                ModBlocks.getRK(ModBlocks.ENRICHED_FARMLAND),
                BlockTags.SUPPORTS_AZALEA,
                BlockTags.BLOCKS_MOTION_IN_HEIGHTMAP_NO_LEAVES,
                BlockTags.SUPPORTS_WARPED_FUNGUS,
                BlockTags.SUPPORT_OVERRIDE_CACTUS_FLOWER,
                BlockTags.BLOCKS_MOTION,
                BlockTags.SUPPORTS_MANGROVE_PROPAGULE,
                BlockTags.SUPPORTS_BIG_DRIPLEAF,
                BlockTags.BLOCKS_FLUID_FLOW,
                BlockTags.SUPPORTS_NETHER_SPROUTS,
                BlockTags.SUPPORTS_CROPS,
                BlockTags.SUPPORTS_PUMPKIN_STEM,
                BlockTags.CAUSES_SUFFOCATION,
                BlockTags.SUPPORTS_DRY_VEGETATION,
                BlockTags.SUPPORTS_CRIMSON_ROOTS,
                BlockTags.BLOCKS_MOTION_NO_LEAVES,
                BlockTags.SUPPORTS_MELON_STEM,
                BlockTags.SUPPORTS_CRIMSON_FUNGUS,
                BlockTags.ENTITIES_CAN_TELEPORT_TO,
                BlockTags.SUPPORTS_STEM_FRUIT,
                BlockTags.ICE_MELTS_WHEN_DESTROYED_ABOVE,
                BlockTags.BLOCKS_MOTION_IN_HEIGHTMAP,
                BlockTags.SUPPORTS_STEM_CROPS,
                BlockTags.SUPPORTS_PUMPKIN_STEM_FRUIT,
                BlockTags.SUPPORTS_VEGETATION,
                BlockTags.GROWS_CROPS,
                BlockTags.SUPPORTS_MELON_STEM_FRUIT,
                BlockTags.SUPPORTS_WITHER_ROSE,
                BlockTags.BLOCKS_LAVA_FIRE_SPREAD,
                BlockTags.BLOCKS_DOLPHIN_JUMP,
                BlockTags.SUPPORTS_WARPED_ROOTS
        );
    }
}
