package net.beetle.naturestreasures.datagen;

import net.beetle.naturestreasures.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BUG_NET, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BUG_NET_COPPER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BUG_NET_IRON, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BUG_NET_GOLD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BUG_NET_DIAMOND, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BUG_NET_NETHERITE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.ANT_SPAWN_EGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(ModItems.WOODLAND_DOR_BEETLE_SPAWN_EGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));

    }
}
