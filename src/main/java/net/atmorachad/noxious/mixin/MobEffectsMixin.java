package net.atmorachad.noxious.mixin;

import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MobEffects.class)
public abstract class MobEffectsMixin {

    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/effect/MobEffect;<init>(Lnet/minecraft/world/effect/MobEffectCategory;I)V",
                    ordinal = 6
            ),
            index = 1
    )
    private static int noxiousDepths$changeNauseaColor(int original) {
        return 0xE0D675;
    }
}