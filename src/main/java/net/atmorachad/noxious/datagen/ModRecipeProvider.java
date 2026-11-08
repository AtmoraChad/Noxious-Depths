package net.atmorachad.noxious.datagen;

import net.atmorachad.noxious.NoxiousDepths;
import net.atmorachad.noxious.block.ModBlocks;
import net.atmorachad.noxious.item.ModItems;
import net.atmorachad.noxious.potion.ModPotions;
import net.atmorachad.noxious.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.predicates.PotionsPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.BrewingRecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.BrewingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PotionIngredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Optional;
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

                shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.SULFUR, 1)
                        .pattern("SSS")
                        .pattern("SSS")
                        .pattern("SSS")
                        .define('S', ModItems.SULFUR)
                        .unlockedBy(getHasName(Blocks.SULFUR), has(Items.SULFUR))
                        .save(output);

                shapeless(RecipeCategory.MISC, ModItems.SULFUR, 9)
                        .unlockedBy(getHasName(Blocks.SULFUR), has(Blocks.SULFUR)).requires(Blocks.SULFUR).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.CINNABAR, 1)
                        .pattern("CCC")
                        .pattern("CCC")
                        .pattern("CCC")
                        .define('C', ModItems.CINNABAR)
                        .unlockedBy(getHasName(Blocks.CINNABAR), has(ModItems.CINNABAR))
                        .save(output);

                shapeless(RecipeCategory.MISC, ModItems.CINNABAR, 9)
                        .unlockedBy(getHasName(Blocks.CINNABAR), has(Blocks.CINNABAR)).requires(Blocks.CINNABAR).save(output);

                for(Item potion : List.of(Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION)) {
                   BrewingRecipeBuilder.brewingMix(potion, Potions.POISON, ModItems.SULFUR, ModPotions.NAUSEA_POTION)
                         .save(output, NoxiousDepths.MOD_ID + ":brewing/" + getItemName(potion) + "_nausea_potion");
                }

                for(Item potion : List.of(Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION)) {
                    BrewingRecipeBuilder.brewingMix(potion, Potions.NIGHT_VISION, ModItems.CINNABAR, ModPotions.BLINDNESS_POTION)
                            .save(output, NoxiousDepths.MOD_ID + ":brewing/" + getItemName(potion) + "_blindnessa_potion");
                }
            }
        };
    }

    @Override
    public String getName() {
        return "Noxious Depths" +
                "Recipes";
    }
}