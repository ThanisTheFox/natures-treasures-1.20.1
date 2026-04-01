package net.beetle.naturestreasures.datagen;

import net.beetle.naturestreasures.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
//  private static final List<ItemConvertible> ITEM_SMELTABLES = List.of(ModItems.ITEM,...);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
//      offerSmelting(exporter, ITEM_SMELTABLES, RecipeCategory.MISC, ModItems.ITEM, 0.7, 200, "item")
//      offerBlasting(exporter, ITEM_SMELTABLES, RecipeCategory.MISC, ModItems.ITEM, 0.7, 100, "item")


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BUG_NET, 1)
                .pattern(" SS")
                .pattern(" TS")
                .pattern("T  ")
                .input('S', Items.STRING)
                .input('T', Items.STICK)
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BUG_NET)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BUG_NET_COPPER, 1)
                .pattern(" SS")
                .pattern(" CS")
                .pattern("B  ")
                .input('S', Items.STRING)
                .input('B', ModItems.BUG_NET)
                .input('C', Items.COPPER_INGOT)
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BUG_NET_COPPER)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BUG_NET_IRON, 1)
                .pattern(" SS")
                .pattern(" CS")
                .pattern("B  ")
                .input('S', Items.STRING)
                .input('B', ModItems.BUG_NET_COPPER)
                .input('C', Items.IRON_INGOT)
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BUG_NET_IRON)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BUG_NET_GOLD, 1)
                .pattern(" SS")
                .pattern(" CS")
                .pattern("B  ")
                .input('S', Items.STRING)
                .input('B', ModItems.BUG_NET)
                .input('C', Items.GOLD_INGOT)
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BUG_NET_GOLD)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BUG_NET_DIAMOND, 1)
                .pattern(" SS")
                .pattern(" CS")
                .pattern("B  ")
                .input('S', Items.STRING)
                .input('B', ModItems.BUG_NET_IRON)
                .input('C', Items.DIAMOND)
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BUG_NET_DIAMOND)));
        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.ofItems(ModItems.BUG_NET_DIAMOND),
                Ingredient.ofItems(Items.NETHERITE_INGOT),
                RecipeCategory.MISC, ModItems.BUG_NET_NETHERITE
        ).criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT)).offerTo(exporter, new Identifier(getRecipeName(ModItems.BUG_NET_NETHERITE)));

    }
}
