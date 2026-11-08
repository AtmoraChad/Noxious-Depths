package net.atmorachad.noxious.creativetab;

import net.atmorachad.noxious.NoxiousDepths;
import net.atmorachad.noxious.block.ModBlocks;
import net.atmorachad.noxious.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class ModCreativeTabs {

    public static final CreativeModeTab ND_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, "sulfur_cave_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(Blocks.SULFUR))
                    .title(Component.translatable("creativemodetab.noxious.depth_blocks"))
                    .displayItems((parameters, output) -> {

                        output.accept(ModItems.SULFUR);
                        output.accept(ModItems.CINNABAR);
                        output.accept(ModItems.SULFUR_TORCH);
                        output.accept(ModItems.SULFUR_CAMPFIRE);
                        output.accept(ModItems.SULFUR_LANTERN);
                        output.accept(ModBlocks.GOLD_BARS);
                        output.accept(ModBlocks.GOLD_CHAIN);
                        output.accept(ModBlocks.ENRICHED_FARMLAND);

                    }).build());

    public static void registerModCreativeModeTabs() {
        NoxiousDepths.LOGGER.info("Registering Creative Mode Tabs for " + NoxiousDepths.MOD_ID);
    }
}
