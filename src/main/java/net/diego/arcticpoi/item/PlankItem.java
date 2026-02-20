package net.diego.arcticpoi.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class PlankItem extends DiggerItem {

    private static final UUID PLANK_REACH_UUID =
            UUID.fromString("907dc4d0-ddbf-4096-8ef4-8c4ec49b3e24");

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public PlankItem() {
        super(
                0.5f,                   // Attack damage
                -2.0f,                  // Attack speed (negative = faster)
                ModTiers.IMPROVISED,    // Custom tier
                BlockTags.MINEABLE_WITH_HOE, // What blocks it can break
                new Item.Properties().durability(165) // Standard item properties
        );

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder =
                ImmutableMultimap.builder();

        builder.putAll(super.getDefaultAttributeModifiers(EquipmentSlot.MAINHAND));

        builder.put(
                ForgeMod.ENTITY_REACH.get(),
                new AttributeModifier(
                        PLANK_REACH_UUID,
                        "Plank reach",
                        -0.5D, // shorter than normal reach
                        AttributeModifier.Operation.ADDITION
                )
        );

        this.defaultModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND
                ? defaultModifiers
                : super.getDefaultAttributeModifiers(slot); //this bridge syncs this class for multiplayer
    }
}