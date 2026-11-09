package net.atmorachad.noxious.item;

import net.atmorachad.noxious.NoxiousDepths;
import net.atmorachad.noxious.block.ModBlocks;
import net.atmorachad.noxious.entity.ModEntities;
import net.atmorachad.noxious.item.items.SulfurItem;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.function.Function;

public class ModItems {

    public static final Item GOLD_BARS = registerItem("gold_bars", properties -> new BlockItem(ModBlocks.GOLD_BARS, properties.useItemDescriptionPrefix()));

    public static final Item GOLD_CHAIN = registerItem("gold_chain", properties -> new BlockItem(ModBlocks.GOLD_CHAIN, properties.useItemDescriptionPrefix()));

    public static final Item SULFUR_TORCH = registerItem("sulfur_torch",
            properties -> new StandingAndWallBlockItem(ModBlocks.SULFUR_TORCH, ModBlocks.WALL_SULFUR_TORCH, Direction.DOWN,
                    properties.useItemDescriptionPrefix()));

    public static final Item SULFUR_CAMPFIRE = registerItem("sulfur_campfire", properties -> new BlockItem(ModBlocks.SULFUR_CAMPFIRE, properties.useItemDescriptionPrefix()));

    public static final Item SULFUR_LANTERN = registerItem("sulfur_lantern", properties -> new BlockItem(ModBlocks.SULFUR_LANTERN, properties.useItemDescriptionPrefix()));

    public static final Item SULFUR = registerItem("sulfur_chunk", properties -> new SulfurItem(properties.useItemDescriptionPrefix()));

    public static final Item CINNABAR = registerItem("cinnabar_chunk", properties -> new Item(properties.useItemDescriptionPrefix()));

    public static final Item CINNABAR_SPIDER_SPAWN_EGG = registerSpawnEgg("cinnabar_spider_spawn_egg", ModEntities.CINNABAR_SPIDER);

    public static final Item BRIMSTONE_WITCH_SPAWN_EGG = registerSpawnEgg("brimstone_witch_spawn_egg", ModEntities.BRIMSTONE_WITCH);

    public static ResourceKey<Item> getRK(Item item) {return BuiltInRegistries.ITEM.getResourceKey(item).get();}

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, name)))));
    }

    private static Item registerSpawnEgg(String name, EntityType<?> type) {
        return registerItem(name, properties -> new SpawnEggItem(
                properties.spawnEgg(type)));
    }

    public static void registerModItems() {
        NoxiousDepths.LOGGER.info("Registering Mod Items for" + NoxiousDepths.MOD_ID);
    }

}
