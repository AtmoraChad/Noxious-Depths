package net.atmorachad.noxious.tag;

import net.atmorachad.noxious.NoxiousDepths;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> SULFUR_FIRE_BASE_BLOCKS = createTag("sulfur_fire_base_blocks");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> SULFUR_FIRE_BASE_BLOCKS = createTag("sulfur_fire_base_blocks");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, name));
        }
    }
}
