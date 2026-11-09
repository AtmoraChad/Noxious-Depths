package net.atmorachad.noxious.entity.client.cinnabar_spider;

import net.minecraft.client.model.monster.spider.SpiderModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;

public class CinnabarSpiderEyes <M extends SpiderModel> extends EyesLayer<LivingEntityRenderState, M> {
    private static final RenderType SPIDER_EYES = RenderTypes.eyes(Identifier.fromNamespaceAndPath("noxious","textures/entity/spider/cinnabar_spider_eyes.png"));

    public CinnabarSpiderEyes(final RenderLayerParent<LivingEntityRenderState, M> renderer) {
        super(renderer);
    }

    @Override
    public RenderType renderType() {
        return SPIDER_EYES;
    }
}

