package net.beetle.naturestreasures;

import net.beetle.naturestreasures.entity.ModEntities;
import net.beetle.naturestreasures.entity.client.AntModel;
import net.beetle.naturestreasures.entity.client.AntRenderer;
import net.beetle.naturestreasures.entity.client.ModModelLayers;
import net.beetle.naturestreasures.gui.InsectEncyclopediaScreen;
import net.beetle.naturestreasures.gui.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class NaturesTreasuresClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("=== NaturesTreasuresClient: onInitializeClient called ===");
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ANT, AntModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.ANT, AntRenderer::new);
        // Register the screen on the client side
        HandledScreens.register(ModScreenHandlers.ENCYCLOPEDIA_SCREEN_HANDLER, InsectEncyclopediaScreen::new);
        System.out.println("=== Encyclopedia screen registered ===");
    }
}