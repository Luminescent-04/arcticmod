package net.diego.arcticpoi.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {

    // Improvised tier for knives, sticks, etc.
    public static final Tier IMPROVISED = new ForgeTier(
            1, // harvest level (irrelevant for weapons)
            120, // durability
            4.0F, // mining speed (irrelevant for weapons)
            1.0F, // base attack damage
            0,   // enchantability
            TagKey.create(Registries.BLOCK, new ResourceLocation("minecraft", "needs_stone_tool")),
            () -> Ingredient.EMPTY // repair ingredient
    );
}