package net.beetle.naturestreasures.entity;

import net.minecraft.item.ItemStack;

public interface Catchable {

    int getCatchDifficulty();

    float getBaseCatchChance();

    ItemStack getCaughtItem();

    String getInsectDisplayName();  // e.g., "Ant"

    String getInsectLore();

}