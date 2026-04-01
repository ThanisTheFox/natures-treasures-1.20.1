package net.beetle.naturestreasures.item;

import net.beetle.naturestreasures.NaturesTreasures;
import net.beetle.naturestreasures.entity.ModEntities;
import net.beetle.naturestreasures.item.custom.BugNetItem;
import net.beetle.naturestreasures.item.custom.InsectEncyclopediaItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item ANT_ITEM = registerItem("ant_item", new Item(new FabricItemSettings()));
    public static final Item BUG_NET = registerItem("bug_net", new BugNetItem(
            new FabricItemSettings().maxDamage(32), 0, 1f));
    public static final Item WOODLAND_DOR_BEETLE_ITEM = registerItem("woodland_dor_beetle_item", new Item(new FabricItemSettings()));
    // Register the encyclopedia with registerItem for consistency
    public static final Item INSECT_ENCYCLOPEDIA = registerItem("insect_encyclopedia", new InsectEncyclopediaItem(new FabricItemSettings().maxCount(1)));
    public static final Item ANT_SPAWN_EGG = registerItem("ant_spawn_egg", new SpawnEggItem(ModEntities.ANT,0x362c28,0x4a1a06, new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(NaturesTreasures.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NaturesTreasures.LOGGER.info("Registering Mod Items for " + NaturesTreasures.MOD_ID);
        // The static initializer already runs, but this method is called from main
    }
}