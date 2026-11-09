package net.atmorachad.noxious.entity.client.brimstone_witch;

import net.atmorachad.noxious.entity.ModModelLayers;
import net.atmorachad.noxious.entity.custom.BrimstoneWitchEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.WitchItemLayer;
import net.minecraft.client.renderer.entity.state.HoldingEntityRenderState;
import net.minecraft.client.renderer.entity.state.WitchRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@Environment(EnvType.CLIENT)
public class BrimstoneWitchRenderer extends MobRenderer<BrimstoneWitchEntity, WitchRenderState, BrimstoneWitchModel> {
    private static final Identifier WITCH_LOCATION = Identifier.fromNamespaceAndPath("noxious","textures/entity/witch/brimstone_witch.png");

    public BrimstoneWitchRenderer(final EntityRendererProvider.Context context) {
        super(context, new BrimstoneWitchModel(context.bakeLayer(ModModelLayers.BRIMSTONE_WITCH)), 0.5F);
        this.addLayer(new BrimstoneWitchItemLayer(this));
    }

    public Identifier getTextureLocation(final WitchRenderState state) {
        return WITCH_LOCATION;
    }

    public WitchRenderState createRenderState() {
        return new WitchRenderState();
    }

    public void extractRenderState(final BrimstoneWitchEntity entity, final WitchRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        HoldingEntityRenderState.extractHoldingEntityRenderState(entity, state, this.itemModelResolver);
        state.entityId = entity.getId();
        ItemStack mainHandItem = entity.getMainHandItem();
        state.isHoldingItem = !mainHandItem.isEmpty();
        state.isHoldingPotion = mainHandItem.is(Items.POTION);
    }
}
