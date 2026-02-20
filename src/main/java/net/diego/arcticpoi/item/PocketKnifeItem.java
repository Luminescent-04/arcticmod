package net.diego.arcticpoi.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;

public class PocketKnifeItem extends DiggerItem {

    public PocketKnifeItem() {
        super(
                2.0f,                  // Attack damage
                -2.0f,                 // Attack speed (negative = faster)
                ModTiers.IMPROVISED,   // Custom tier
                BlockTags.MINEABLE_WITH_AXE, // What blocks it can break
                new Item.Properties()  // Standard item properties
        );
    }
}