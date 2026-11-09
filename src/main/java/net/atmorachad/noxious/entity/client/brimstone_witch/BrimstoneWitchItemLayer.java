package net.atmorachad.noxious.entity.client.brimstone_witch;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.state.WitchRenderState;

@Environment(EnvType.CLIENT)
public class BrimstoneWitchItemLayer extends CrossedArmsItemLayer<WitchRenderState, BrimstoneWitchModel> {
    public BrimstoneWitchItemLayer(final RenderLayerParent<WitchRenderState, BrimstoneWitchModel> renderer) {
        super(renderer);
    }

    protected void applyTranslation(final WitchRenderState state, final PoseStack poseStack) {
        if (state.isHoldingPotion) {
            this.getParentModel().root().translateAndRotate(poseStack);
            this.getParentModel().translateToHead(poseStack);
            this.getParentModel().getNose().translateAndRotate(poseStack);
            poseStack.translate(0.0625F, 0.25F, 0.0F);
            poseStack.rotateDegrees(Axis.ZP, 180.0F);
            poseStack.rotateDegrees(Axis.XP, 140.0F);
            poseStack.rotateDegrees(Axis.ZP, 10.0F);
            poseStack.rotateDegrees(Axis.XP, 180.0F);
        } else {
            super.applyTranslation(state, poseStack);
        }
    }
}