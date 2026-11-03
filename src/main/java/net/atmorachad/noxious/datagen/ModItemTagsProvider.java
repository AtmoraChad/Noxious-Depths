package net.atmorachad.noxious.datagen;

import net.atmorachad.noxious.block.ModBlocks;
import net.atmorachad.noxious.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {

        tag(ModTags.Items.SULFUR_FIRE_BASE_BLOCKS)
                .add(ModBlocks.getItemRK(Blocks.SULFUR))
                .add(ModBlocks.getItemRK(Blocks.POTENT_SULFUR));
    }
}
