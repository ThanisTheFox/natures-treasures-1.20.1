package net.beetle.naturestreasures.util;

import net.beetle.naturestreasures.NaturesTreasures;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;


public class ModTags {
    public static class Blocks {

    }

    public static class Items {
        public static final TagKey<Item> BUGNET =
                createTag("bug_net");

        public static final TagKey<Item> BUG =
                createTag("bug");


        public static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(NaturesTreasures.MOD_ID, name));
        }
    }
}
