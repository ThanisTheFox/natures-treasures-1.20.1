package net.beetle.naturestreasures.item;

import net.beetle.naturestreasures.NaturesTreasures;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item ANT_ITEM = registerItem("ant_item", new Item(new FabricItemSettings()));
    public static final Item WOODLAND_DOR_BEETLE_ITEM = registerItem("woodland_dor_beetle_item", new Item(new FabricItemSettings()));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(ANT_ITEM);
        entries.add(WOODLAND_DOR_BEETLE_ITEM);
    }

//sneaky Iron Party

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(NaturesTreasures.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NaturesTreasures.LOGGER.info("Registering Mod Items for " + NaturesTreasures.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
