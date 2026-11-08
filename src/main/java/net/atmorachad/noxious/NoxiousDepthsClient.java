package net.atmorachad.noxious;

import net.atmorachad.noxious.entity.ModEntities;
import net.atmorachad.noxious.entity.client.CinnabarSpiderRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class NoxiousDepthsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRenderers.register(ModEntities.CINNABAR_SPIDER, CinnabarSpiderRenderer::new);

    }
}
