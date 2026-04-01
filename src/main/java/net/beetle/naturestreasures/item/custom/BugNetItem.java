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

public class BugNetItem extends Item {
    private final int maxCatchDifficulty;
    private final float catchChanceMultiplier;

    public BugNetItem(Settings settings, int maxCatchDifficulty, float catchChanceMultiplier) {
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

        // Calculate catch chance
        float baseChance = catchable.getBaseCatchChance();
        float finalChance = Math.min(baseChance * catchChanceMultiplier, 1.0f);

        // Failed catch
        if (world.getRandom().nextFloat() > finalChance) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENTITY_ITEM_BREAK, player.getSoundCategory(), 1.0f, 1.0f);

            String[] messages = new String[] {
                    "The insect slipped away...",
                    "It wriggled free!",
                    "So close... but it escaped!",
                    "The tiny creature fled!",
                    "Nature outsmarted you this time.",
                    "It darted out of your reach!",
                    "The insect vanished into the grass.",
                    "Better luck next time!",
                    "The net returned empty-handed..",
                    "It seems you were too slow..."
            };

            String randomMessage = messages[world.getRandom().nextInt(messages.length)];

            player.sendMessage(Text.literal(randomMessage), true);

            if (stack.damage(1, player.getRandom(), null) && stack.isEmpty()) {
                player.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
            }

            player.swingHand(hand);
            return ActionResult.FAIL;
        }

        // Successful catch → give item
        ItemStack caughtItem = catchable.getCaughtItem();
        if (!player.getInventory().insertStack(caughtItem)) {
            player.dropItem(caughtItem, false);
        }


        ItemStack encyclopedia = null;
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack s = player.getInventory().getStack(i);
            if (s.getItem() instanceof InsectEncyclopediaItem) {
                encyclopedia = s;
                break;
            }
        }

        if (encyclopedia != null) {
            InsectEncyclopediaItem.addEntry(
                    encyclopedia,
                    entity.getType().getRegistryEntry().getKey().get().getValue(),
                    catchable.getInsectDisplayName(),
                    catchable.getCatchDifficulty(),
                    catchable.getBaseCatchChance(),
                    catchable.getInsectLore()
            );
        }

        // Remove the entity
        entity.discard();

        // Play success sound
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENTITY_ITEM_PICKUP, player.getSoundCategory(), 1.0f, 1.0f);

        // Damage the net
        if (stack.damage(1, player.getRandom(), null) && stack.isEmpty()) {
            player.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
        }

        player.swingHand(hand);
        return ActionResult.SUCCESS;
    }
}