package net.beetle.naturestreasures.entity;

import net.beetle.naturestreasures.NaturesTreasures;
import net.beetle.naturestreasures.entity.custom.AntEntity;
import net.beetle.naturestreasures.entity.custom.WoodlandDorBeetleEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntities {
    public static final EntityType<AntEntity> ANT = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(NaturesTreasures.MOD_ID, "ant"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AntEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                    .build());
    public static final EntityType<WoodlandDorBeetleEntity> WOODLANDDORBEETLE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(NaturesTreasures.MOD_ID, "woodlanddorbeetle"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WoodlandDorBeetleEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 0.4f))
                    .build());
    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ANT, AntEntity.createAntAttributes());
        FabricDefaultAttributeRegistry.register(WOODLANDDORBEETLE, WoodlandDorBeetleEntity.createWoodlandDorBeetleAttributes());
    }

    public static void registerModEntities() {
        NaturesTreasures.LOGGER.info("Registering Entities for " + NaturesTreasures.MOD_ID);

        SpawnRestriction.register(ANT, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, spawnReason, pos, random) -> world.getLightLevel(pos) > 7);

        // BiomeSelectors.includeByKey für mehr Biome; Weight noch ändern
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(
                BiomeKeys.PLAINS, BiomeKeys.MEADOW, BiomeKeys.CHERRY_GROVE, BiomeKeys.FOREST, BiomeKeys.FLOWER_FOREST,
                        BiomeKeys.SUNFLOWER_PLAINS, BiomeKeys.BIRCH_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
                SpawnGroup.CREATURE, ANT, 1000, 3, 5);
    }
}
