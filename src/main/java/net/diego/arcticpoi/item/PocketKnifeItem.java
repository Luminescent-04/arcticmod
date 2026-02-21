package net.diego.arcticpoi.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import java.util.List;
import java.util.UUID;

public class PocketKnifeItem extends DiggerItem {

    private static final UUID POCKETKNIFE_REACH_UUID =
            UUID.fromString("123e4567-e89b-12d3-a456-426614174000"); //UUID MUST BE DIFFERENT FOR EVERY WEAPON

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public PocketKnifeItem() {
        super(
                3.5f,                   // Attack damage
                5.5f,                  // Attack speed (negative = faster)
                ModTiers.IMPROVISED,    // Custom tier
                BlockTags.MINEABLE_WITH_HOE, // What blocks it can break
                new Item.Properties().durability(160) // Standard item properties
        );

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder =
                ImmutableMultimap.builder();

        builder.putAll(super.getDefaultAttributeModifiers(EquipmentSlot.MAINHAND));

        builder.put(
                ForgeMod.ENTITY_REACH.get(),
                new AttributeModifier(
                        POCKETKNIFE_REACH_UUID,
                        "Knife reach",
                        -1.0D, // shorter than normal reach
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

    // Apply custom knockback on hit, the ghetto way
    @Override
    public boolean hurtEnemy(ItemStack stack, net.minecraft.world.entity.LivingEntity target, net.minecraft.world.entity.LivingEntity attacker) {
        if (!attacker.level().isClientSide()) {
            // Custom knockback strength
            float knockbackStrength = 0.0F;

            // Push the target away from attacker
            double dx = attacker.getX() - target.getX();
            double dz = attacker.getZ() - target.getZ();
            target.knockback(knockbackStrength, dx, dz);
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);

        // Add gray italic tooltip
        tooltip.add(Component.literal("Could riddle something with cuts in seconds")
                .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }
}