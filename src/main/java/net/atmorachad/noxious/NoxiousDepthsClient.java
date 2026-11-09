package net.atmorachad.noxious;

import net.atmorachad.noxious.entity.ModEntities;
import net.atmorachad.noxious.entity.ModModelLayers;
import net.atmorachad.noxious.entity.client.brimstone_witch.BrimstoneWitchRenderer;
import net.atmorachad.noxious.entity.client.cinnabar_spider.CinnabarSpiderRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class NoxiousDepthsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelLayers.registerModModelLayers();

        EntityRenderers.register(ModEntities.CINNABAR_SPIDER, CinnabarSpiderRenderer::new);
        EntityRenderers.register(ModEntities.BRIMSTONE_WITCH, BrimstoneWitchRenderer::new);
    }
}
