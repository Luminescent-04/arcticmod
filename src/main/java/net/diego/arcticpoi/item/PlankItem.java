package net.diego.arcticpoi.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;

public class PlankItem extends DiggerItem {

    public PlankItem() {
        super(
                0.5f,                  // Attack damage
                1.5f,                 // Attack speed (negative = faster)
                ModTiers.IMPROVISED,   // Custom tier
                BlockTags.MINEABLE_WITH_HOE, // What blocks it can break
                new Item.Properties()  // Standard item properties
        );
    }
}