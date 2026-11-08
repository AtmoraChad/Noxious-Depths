package net.atmorachad.noxious.potion;

import net.atmorachad.noxious.NoxiousDepths;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;

public class ModPotions {

    public static final Holder<Potion> NAUSEA_POTION = registerPotion("nausea_potion",
            new Potion("nausea_potion", new MobEffectInstance(MobEffects.NAUSEA, 25, 0)));

    private static Holder<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, name), potion);
    }

    public static void registerPotions() {
        NoxiousDepths.LOGGER.info("Registering potions for " + NoxiousDepths.MOD_ID);
    }
}
