package net.atmorachad.noxious.datagen;

import net.atmorachad.noxious.block.ModBlocks;
import net.atmorachad.noxious.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipeContext,
            BootstrapContext<Advancement> advancementContext) {
        return new RecipeProvider(recipeContext, advancementContext) {

            @Override
            public void buildRecipes() {


                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_BARS, 16)
                        .pattern("RRR")
                        .pattern("RRR")
                        .define('R', Items.GOLD_INGOT)
                        .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_CHAIN, 1)
                        .pattern("R")
                        .pattern("S")
                        .pattern("R")
                        .define('S', Items.GOLD_INGOT)
                        .define('R', Items.GOLD_NUGGET)
                        .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                        .unlockedBy(getHasName(Items.GOLD_NUGGET), has(Items.GOLD_NUGGET))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SULFUR_CAMPFIRE, 1)
                        .pattern(" S ")
                        .pattern("SPS")
                        .pattern("LLL")
                        .define('S', Items.STICK)
                        .define('P', ModTags.Items.SULFUR_FIRE_BASE_BLOCKS)
                        .define('L', ItemTags.LOGS)
                        .unlockedBy(getHasName(Blocks.SULFUR), has(Items.SULFUR))
                        .unlockedBy(getHasName(Blocks.POTENT_SULFUR), has(Blocks.POTENT_SULFUR))
                        .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return "Noxious Depths" +
                "Recipes";
    }
}