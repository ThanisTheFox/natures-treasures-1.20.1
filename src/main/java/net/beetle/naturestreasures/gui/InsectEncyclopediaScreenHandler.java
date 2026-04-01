package net.beetle.naturestreasures.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

public class InsectEncyclopediaScreenHandler extends ScreenHandler {
    public InsectEncyclopediaScreenHandler(int syncId, PlayerInventory inventory, ItemStack encyclopedia) {
        super(ModScreenHandlers.ENCYCLOPEDIA_SCREEN_HANDLER, syncId);
        System.out.println("=== InsectEncyclopediaScreenHandler created with syncId=" + syncId + " ===");
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}