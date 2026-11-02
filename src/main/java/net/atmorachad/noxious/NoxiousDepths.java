package net.atmorachad.noxious;

import net.atmorachad.noxious.block.ModBlocks;
import net.atmorachad.noxious.creativetab.ModCreativeTabs;
import net.atmorachad.noxious.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoxiousDepths implements ModInitializer {
	public static final String MOD_ID = "noxious";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModCreativeTabs.registerModCreativeModeTabs();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
