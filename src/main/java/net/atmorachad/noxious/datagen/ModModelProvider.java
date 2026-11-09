package net.atmorachad.noxious.datagen;


import net.atmorachad.noxious.block.ModBlocks;
import net.atmorachad.noxious.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.Identifier;

import static net.atmorachad.noxious.NoxiousDepths.MOD_ID;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {super(output);}

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

        blockModelGenerators.createBars(ModBlocks.GOLD_BARS,
                Identifier.fromNamespaceAndPath(MOD_ID, "block/gold_bars_post_ends"),
                Identifier.fromNamespaceAndPath(MOD_ID, "block/gold_bars_post"),
                Identifier.fromNamespaceAndPath(MOD_ID, "block/gold_bars_cap"),
                Identifier.fromNamespaceAndPath(MOD_ID, "block/gold_bars_cap_alt"),
                Identifier.fromNamespaceAndPath(MOD_ID, "block/gold_bars_side"),
                Identifier.fromNamespaceAndPath(MOD_ID, "block/gold_bars_side_alt"));

        blockModelGenerators.createAxisAlignedPillarBlockCustomModel(ModBlocks.GOLD_CHAIN,
                BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(MOD_ID, "block/gold_chain")));

        blockModelGenerators.createNormalTorch(ModBlocks.SULFUR_TORCH, ModBlocks.WALL_SULFUR_TORCH);

        blockModelGenerators.createLantern(ModBlocks.SULFUR_LANTERN);
    }


    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {

        itemModelGenerators.generateFlatItem(ModItems.GOLD_BARS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GOLD_CHAIN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SULFUR_CAMPFIRE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SULFUR, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CINNABAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CINNABAR_SPIDER_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BRIMSTONE_WITCH_SPAWN_EGG, ModelTemplates.FLAT_ITEM);

    }
}
