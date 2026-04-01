package net.beetle.naturestreasures;

import net.beetle.naturestreasures.entity.ModEntities;
import net.beetle.naturestreasures.entity.client.AntModel;
import net.beetle.naturestreasures.entity.client.AntRenderer;
import net.beetle.naturestreasures.entity.client.ModModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class NaturesTreasuresClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ANT, AntModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.ANT, AntRenderer::new);

    }
}

