package net.beetle.naturestreasures.item;

import net.beetle.naturestreasures.NaturesTreasures;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup NATURES_TREASURES_TAB = Registry.register(Registries.ITEM_GROUP,
            new Identifier(NaturesTreasures.MOD_ID, "natures_treasures_tab"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.natures_treasures_tab"))
                    .icon(() -> new ItemStack(ModItems.WOODLAND_DOR_BEETLE_ITEM)).entries((displayContext, entries) -> {
                        entries.add(ModItems.ANT_ITEM);
                        entries.add(ModItems.WOODLAND_DOR_BEETLE_ITEM);

                        entries.add(ModItems.BUG_NET);
                        entries.add(ModItems.BUG_NET_COPPER);
                        entries.add(ModItems.BUG_NET_IRON);
                        entries.add(ModItems.BUG_NET_GOLD);
                        entries.add(ModItems.BUG_NET_DIAMOND);
                        entries.add(ModItems.BUG_NET_NETHERITE);

                        entries.add(ModItems.ANT_SPAWN_EGG);
                    }).build());


    public static void registerItemGroups() {
        NaturesTreasures.LOGGER.info("Registering Item Groups for" + NaturesTreasures.MOD_ID);
    }
}
