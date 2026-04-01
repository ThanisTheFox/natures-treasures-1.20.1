package net.beetle.naturestreasures.item.custom;

import net.beetle.naturestreasures.gui.InsectEncyclopediaScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class InsectEncyclopediaItem extends Item {
    public InsectEncyclopediaItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        System.out.println("=== use() called on " + (world.isClient ? "CLIENT" : "SERVER") + " side ===");
        if (!world.isClient) {
            System.out.println("=== Opening screen on SERVER ===");
            player.openHandledScreen(new NamedScreenHandlerFactory() {
                @Override
                public Text getDisplayName() {
                    return Text.literal("Encyclopedia");
                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player) {
                    System.out.println("=== Custom factory: creating handler on SERVER with syncId=" + syncId + " ===");
                    return new InsectEncyclopediaScreenHandler(syncId, inventory, stack);
                }
            });
        }
        return TypedActionResult.success(stack);
    }

    public static void addEntry(ItemStack encyclopedia, Identifier insectId, String displayName,
                                int difficulty, float baseChance, String lore) {
        NbtCompound tag = encyclopedia.getOrCreateNbt();
        NbtList entries = tag.getList("Entries", NbtList.COMPOUND_TYPE);

        for (int i = 0; i < entries.size(); i++) {
            NbtCompound entry = entries.getCompound(i);
            if (entry.getString("id").equals(insectId.toString())) {
                return;
            }
        }

        NbtCompound newEntry = new NbtCompound();
        newEntry.putString("id", insectId.toString());
        newEntry.putString("name", displayName);
        newEntry.putInt("difficulty", difficulty);
        newEntry.putFloat("chance", baseChance);
        newEntry.putString("lore", lore);
        entries.add(newEntry);

        tag.put("Entries", entries);
    }
}