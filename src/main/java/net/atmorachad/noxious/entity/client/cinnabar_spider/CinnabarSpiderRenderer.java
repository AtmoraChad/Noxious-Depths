package net.atmorachad.noxious.entity.client.cinnabar_spider;

import net.atmorachad.noxious.entity.custom.CinnabarSpiderEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.spider.SpiderModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class CinnabarSpiderRenderer <T extends CinnabarSpiderEntity> extends MobRenderer<T, LivingEntityRenderState, SpiderModel> {
    private static final Identifier SPIDER_LOCATION = Identifier.fromNamespaceAndPath("noxious", "textures/entity/spider/cinnabar_spider.png");

    public CinnabarSpiderRenderer(final EntityRendererProvider.Context context) {
        this(context, ModelLayers.SPIDER);
    }

    public CinnabarSpiderRenderer(final EntityRendererProvider.Context context, final ModelLayerLocation model) {
        super(context, new SpiderModel(context.bakeLayer(model)), 0.8F);
        this.addLayer(new CinnabarSpiderEyes<>(this));
    }

    @Override
    protected float getFlipDegrees() {
        return 180.0F;
    }

    @Override
    public Identifier getTextureLocation(final LivingEntityRenderState state) {
        return SPIDER_LOCATION;
    }

    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    public void extractRenderState(final T entity, final LivingEntityRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
    }
}
