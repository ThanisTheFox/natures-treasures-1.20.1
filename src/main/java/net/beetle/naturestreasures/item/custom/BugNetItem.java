package net.beetle.naturestreasures.item.custom;

import net.beetle.naturestreasures.entity.custom.AntEntity;
import net.beetle.naturestreasures.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BugNetItem extends Item {
    public BugNetItem(Settings settings) {
        super(settings);
    }

// Neue Ant = Ant Item Variante
// Für Beetle kopieren und umändern
@Override
public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
    World world = player.getWorld();

    if (world.isClient) {
        return ActionResult.SUCCESS;
    }

    if (!(entity instanceof AntEntity)) {
        return ActionResult.PASS;
    }
    ItemStack antStack = new ItemStack(ModItems.ANT_ITEM, 1);

    if (!player.getInventory().insertStack(antStack)) {
        player.dropItem(antStack, false);
    }

    entity.discard();

    world.playSound(null, player.getX(), player.getY(), player.getZ(),
            SoundEvents.ENTITY_ITEM_PICKUP, player.getSoundCategory(), 1.0f, 1.0f);

    if (stack.damage(1, player.getRandom(), null) && stack.isEmpty()) {
        player.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
    }

    player.swingHand(hand);

    return ActionResult.SUCCESS;
}
}
//Alte Spawnegg Variante:
//    @Override
//    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
//        World world = player.getWorld();
//
//        if (world.isClient) {
//            return ActionResult.SUCCESS;
//        }
//
//        if (!(entity instanceof MobEntity)) {
//            return ActionResult.PASS;
//        }
//
//        SpawnEggItem egg = SpawnEggItem.forEntity(entity.getType());
//        if (egg != null) {
//            // Give the egg
//            ItemStack eggStack = new ItemStack(egg, 1);
//            if (!player.getInventory().insertStack(eggStack)) {
//                player.dropItem(eggStack, false);
//            }
//            entity.discard();
//
//
//         world.playSound(null, player.getX(), player.getY(), player.getZ(),
//            SoundEvents.ENTITY_ITEM_PICKUP, player.getSoundCategory(), 1.0f, 1.0f);
//
//            if (stack.damage(1, player.getRandom(), null) && stack.isEmpty()) {
//                player.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
//            }
//
//            player.swingHand(hand);
//
//            return ActionResult.SUCCESS;
//        }
//
//        return ActionResult.PASS;
//    }
//