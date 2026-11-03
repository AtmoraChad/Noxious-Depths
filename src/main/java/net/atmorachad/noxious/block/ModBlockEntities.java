package net.atmorachad.noxious.block;

import net.atmorachad.noxious.NoxiousDepths;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTypes;

public class ModBlockEntities {

    public static void registerModBlockEntities() {

        FabricBlockEntityType campfireType =
                (FabricBlockEntityType) BlockEntityTypes.CAMPFIRE;
        campfireType.addValidBlock(ModBlocks.SULFUR_CAMPFIRE);

    }
}
