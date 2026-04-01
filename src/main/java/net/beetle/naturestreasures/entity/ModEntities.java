package net.beetle.naturestreasures.entity;

import net.beetle.naturestreasures.NaturesTreasures;
import net.beetle.naturestreasures.entity.custom.AntEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<AntEntity> ANT = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(NaturesTreasures.MOD_ID, "ant"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AntEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f,0.5f)).build());
    //EntityDimensions.fixed() Größe der Hitbox

    public static void registerModEntities() {
        NaturesTreasures.LOGGER.info("Registering Entities for " + NaturesTreasures.MOD_ID);
    }
}
