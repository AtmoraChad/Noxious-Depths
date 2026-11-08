package net.atmorachad.noxious.block;

import net.atmorachad.noxious.NoxiousDepths;
import net.atmorachad.noxious.block.blocks.EnrichedFarmlandBlock;
import net.atmorachad.noxious.block.blocks.SulfurFireBlock;
import net.atmorachad.noxious.particle.ModParticles;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

import static net.minecraft.world.level.block.Blocks.litBlockEmission;

public class ModBlocks {


    public static final Block GOLD_BARS = registerBlockWithoutItem("gold_bars",
            properties -> new IronBarsBlock(properties.requiresCorrectToolForDrops().strength(5.0F, 6.0F)
                    .sound(SoundType.IRON).noOcclusion()));

    public static final Block GOLD_CHAIN = registerBlockWithoutItem("gold_chain",
            properties -> new ChainBlock(properties.requiresCorrectToolForDrops().strength(5.0F, 6.0F)
                    .sound(SoundType.CHAIN).noOcclusion().forceSolidOn()));

    public static final Block SULFUR_FIRE = registerBlockWithoutItem("sulfur_fire",
            properties -> new SulfurFireBlock(properties.mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollision().instabreak()
                    .lightLevel(state -> 15).sound(SoundType.WOOL).pushReaction(PushReaction.POPPED)));

    public static final Block SULFUR_TORCH = registerBlockWithoutItem("sulfur_torch",
            properties -> new TorchBlock(ModParticles.SULFUR_FLAME, properties.noCollision().instabreak().lightLevel(state -> 14).sound(SoundType.WOOD).pushReaction(PushReaction.POPPED)));

    public static final Block WALL_SULFUR_TORCH = registerBlockWithoutItem("wall_sulfur_torch",
            properties -> new WallTorchBlock(ModParticles.SULFUR_FLAME, wallVariant(properties.noCollision().instabreak().lightLevel(state -> 14).sound(SoundType.WOOD).pushReaction(PushReaction.POPPED), SULFUR_TORCH, true)));

    public static final Block SULFUR_CAMPFIRE = registerBlockWithoutItem("sulfur_campfire",
            properties -> new CampfireBlock(true, 1, properties.mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion().ignitedByLava()));

    public static final Block SULFUR_LANTERN = registerBlockWithoutItem("sulfur_lantern",
            properties -> new LanternBlock(properties.mapColor(MapColor.METAL).forceSolidOn().strength(3.5F).sound(SoundType.LANTERN).lightLevel(state -> 15).noOcclusion().pushReaction(PushReaction.POPPED)));

    public static final Block ENRICHED_FARMLAND = registerBlock("enriched_farmland",
            properties -> new EnrichedFarmlandBlock(Blocks.DIRT, properties.mapColor(MapColor.DIRT).mapColor(MapColor.DIRT).randomTicks().strength(0.6F).sound(SoundType.GRAVEL).isSuffocating(Blocks::always)));


    public static ResourceKey<Block> getRK(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }

    public static ResourceKey<Item> getItemRK(Block block) {
        Identifier id = BuiltInRegistries.BLOCK.getKey(block);
        return ResourceKey.create(Registries.ITEM, id);
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, name)))));
    }

    private static Block registerBlockWithoutItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, name))));
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, name), toRegister);
    }

    private static BlockBehaviour.Properties wallVariant(
            BlockBehaviour.Properties wallProperties,
            final Block standingBlock,
            final boolean copyName
    ) {
        wallProperties = wallProperties.overrideLootTable(standingBlock.getLootTable());

        if (copyName) {
            wallProperties = wallProperties.overrideDescription(standingBlock.getDescriptionId());
        }
        return wallProperties;
    }

    public static void registerModBlocks() {
        NoxiousDepths.LOGGER.info("Registering Mod Blocks for " + NoxiousDepths.MOD_ID);
    }
}
