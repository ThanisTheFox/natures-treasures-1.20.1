package net.beetle.naturestreasures.item.custom;

import net.beetle.naturestreasures.entity.Catchable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BugNetItemCopper extends Item {
    private final int maxCatchDifficulty;
    private final float catchChanceMultiplier;

    public BugNetItemCopper(Settings settings, int maxCatchDifficulty, float catchChanceMultiplier) {
        super(settings);
        this.maxCatchDifficulty = maxCatchDifficulty;
        this.catchChanceMultiplier = catchChanceMultiplier;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        World world = player.getWorld();

        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        if (!(entity instanceof Catchable catchable)) {
            return ActionResult.PASS;
        }

        int difficulty = catchable.getCatchDifficulty();
        if (difficulty > maxCatchDifficulty) {
            player.sendMessage(Text.literal("This bug net is too weak to catch that!"), true);
            return ActionResult.FAIL;
        }

        float baseChance = catchable.getBaseCatchChance();
        float finalChance = Math.min(baseChance * catchChanceMultiplier, 1.0f);

        if (world.getRandom().nextFloat() > finalChance) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENTITY_ITEM_BREAK, player.getSoundCategory(), 1.0f, 1.0f);
            if (stack.damage(1, player.getRandom(), null) && stack.isEmpty()) {
                player.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
            }
            player.swingHand(hand);
            return ActionResult.FAIL;
        }

        ItemStack caughtItem = catchable.getCaughtItem();
        if (!player.getInventory().insertStack(caughtItem)) {
            player.dropItem(caughtItem, false);
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