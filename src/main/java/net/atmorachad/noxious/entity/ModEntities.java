package net.atmorachad.noxious.entity;

import net.atmorachad.noxious.NoxiousDepths;
import net.atmorachad.noxious.entity.custom.BrimstoneWitchEntity;
import net.atmorachad.noxious.entity.custom.CinnabarSpiderEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities{
    
    public static final ResourceKey<EntityType<?>> CINNABAR_SPIDER_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, "cinnabar_spider"));

    public static final ResourceKey<EntityType<?>> BRIMSTONE_WITCH_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, "brimstone_witch"));

    public static final EntityType<CinnabarSpiderEntity> CINNABAR_SPIDER = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, "cinnabar_spider"),
            EntityType.Builder.of(CinnabarSpiderEntity::new, MobCategory.MONSTER).sized(1.4f, 0.9f).build(CINNABAR_SPIDER_KEY));

    public static final EntityType<BrimstoneWitchEntity> BRIMSTONE_WITCH = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, "brimstone_witch"),
            EntityType.Builder.of(BrimstoneWitchEntity::new, MobCategory.MONSTER).sized(0.6f, 1.95f).build(BRIMSTONE_WITCH_KEY));


    public static void registerModEntities() {
        NoxiousDepths.LOGGER.info("Registering Mod Entities for " + NoxiousDepths.MOD_ID);
    }
}