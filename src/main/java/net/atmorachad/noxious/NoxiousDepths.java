package net.atmorachad.noxious;

import net.atmorachad.noxious.block.ModBlockEntities;
import net.atmorachad.noxious.block.ModBlocks;
import net.atmorachad.noxious.creativetab.ModCreativeTabs;
import net.atmorachad.noxious.entity.ModEntities;
import net.atmorachad.noxious.entity.client.CinnabarSpiderRenderer;
import net.atmorachad.noxious.entity.custom.CinnabarSpiderEntity;
import net.atmorachad.noxious.item.ModItems;
import net.atmorachad.noxious.particle.ModParticles;
import net.atmorachad.noxious.potion.ModPotions;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.Identifier;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoxiousDepths implements ModInitializer {
	public static final String MOD_ID = "noxious";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBlockEntities();
		ModItems.registerModItems();
		ModCreativeTabs.registerModCreativeModeTabs();
		ModParticles.registerModParticles();
		ModPotions.registerPotions();
		ModEntities.registerModEntities();

		ParticleProviderRegistry.getInstance().register(
				ModParticles.SULFUR_FLAME, FlameParticle.Provider::new
		);

		FabricDefaultAttributeRegistry.register(ModEntities.CINNABAR_SPIDER, CinnabarSpiderEntity.createAttributes());

		SpawnPlacements.register(ModEntities.CINNABAR_SPIDER, SpawnPlacementTypes.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CinnabarSpiderEntity::checkAnyLightMonsterSpawnRules);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
