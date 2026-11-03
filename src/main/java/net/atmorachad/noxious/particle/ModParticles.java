package net.atmorachad.noxious.particle;

import net.atmorachad.noxious.NoxiousDepths;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModParticles {

    public static final SimpleParticleType SULFUR_FLAME =
            register("sulfur_flame", FabricParticleTypes.simple());

    private static SimpleParticleType register(String name, SimpleParticleType particle) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE,
                Identifier.fromNamespaceAndPath(NoxiousDepths.MOD_ID, name), particle);
    }

    public static void registerModParticles() {
        NoxiousDepths.LOGGER.info("Registering Mod Particles for " + NoxiousDepths.MOD_ID);
    }
}