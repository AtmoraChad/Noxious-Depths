package net.atmorachad.noxious;

import net.atmorachad.noxious.block.ModBlockEntities;
import net.atmorachad.noxious.block.ModBlocks;
import net.atmorachad.noxious.creativetab.ModCreativeTabs;
import net.atmorachad.noxious.item.ModItems;
import net.atmorachad.noxious.particle.ModParticles;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.Identifier;

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

		ParticleProviderRegistry.getInstance().register(
				ModParticles.SULFUR_FLAME, FlameParticle.Provider::new
		);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
