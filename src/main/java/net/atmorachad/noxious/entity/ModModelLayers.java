package net.atmorachad.noxious.entity;

import net.atmorachad.noxious.NoxiousDepths;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

import net.atmorachad.noxious.entity.client.brimstone_witch.BrimstoneWitchModel;

public class ModModelLayers {

    public static final ModelLayerLocation BRIMSTONE_WITCH = new ModelLayerLocation(
                    Identifier.fromNamespaceAndPath("noxious", "brimstone_witch"), "main");

    public static void registerModModelLayers() {
        NoxiousDepths.LOGGER.info("Registering Model Layers for " + NoxiousDepths.MOD_ID);
        ModelLayerRegistry.registerModelLayer(BRIMSTONE_WITCH, BrimstoneWitchModel::createBodyLayer);
    }
}