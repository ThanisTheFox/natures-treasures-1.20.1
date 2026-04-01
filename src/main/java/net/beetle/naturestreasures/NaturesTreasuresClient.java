package net.beetle.naturestreasures;

import net.beetle.naturestreasures.entity.ModEntities;
import net.beetle.naturestreasures.entity.client.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class NaturesTreasuresClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ANT, AntModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.ANT, AntRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.WOODLANDDORBEETLE, WoodlandDorBeetleModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.WOODLANDDORBEETLE, WoodlandDorBeetleRenderer::new);

    }
}

