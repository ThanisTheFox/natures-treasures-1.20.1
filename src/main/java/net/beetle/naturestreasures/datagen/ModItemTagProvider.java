package net.beetle.naturestreasures.datagen;

import net.beetle.naturestreasures.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.beetle.naturestreasures.item.ModItems;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Items.BUGNET)
                .add(ModItems.BUG_NET)
                .add(ModItems.BUG_NET_COPPER)
                .add(ModItems.BUG_NET_IRON)
                .add(ModItems.BUG_NET_GOLD)
                .add(ModItems.BUG_NET_DIAMOND)
                .add(ModItems.BUG_NET_NETHERITE);
        getOrCreateTagBuilder(ModTags.Items.BUG)
                .add(ModItems.ANT_ITEM)
                .add(ModItems.WOODLAND_DOR_BEETLE_ITEM);
    }
}