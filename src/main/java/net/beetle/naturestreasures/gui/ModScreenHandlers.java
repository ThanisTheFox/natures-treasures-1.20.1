package net.beetle.naturestreasures.gui;

import net.beetle.naturestreasures.NaturesTreasures;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<InsectEncyclopediaScreenHandler> ENCYCLOPEDIA_SCREEN_HANDLER;

    public static void registerScreenHandlers() {
        if (ENCYCLOPEDIA_SCREEN_HANDLER != null) return;
        System.out.println("=== ModScreenHandlers: registering screen handler ===");
        ENCYCLOPEDIA_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(
                new Identifier(NaturesTreasures.MOD_ID, "encyclopedia"),
                (syncId, inventory) -> new InsectEncyclopediaScreenHandler(syncId, inventory, null)
        );
        System.out.println("=== ModScreenHandlers: handler type = " + ENCYCLOPEDIA_SCREEN_HANDLER);
    }
}