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

public class RustyPipeItem extends DiggerItem {

    // Unique UUIDs for reach modifier
    private static final UUID RUSTYPIPE_REACH_UUID =
            UUID.fromString("8212a2d0-cf38-4dbb-b2c1-9c6e65b29af0");

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public RustyPipeItem() {
        super(
                1.5f,                   // Attack damage
                -3.4f,                  // Attack speed (negative = slower)
                ModTiers.IMPROVISED,    // Custom tier
                BlockTags.MINEABLE_WITH_HOE, // What blocks it can break
                new Item.Properties().durability(170)
        );

        // Build attributes for this weapon
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getDefaultAttributeModifiers(EquipmentSlot.MAINHAND));

        // Add custom reach (shorter than vanilla)
        builder.put(
                ForgeMod.ENTITY_REACH.get(),
                new AttributeModifier(
                        RUSTYPIPE_REACH_UUID,
                        "Rustypipe reach",
                        0.0D, // slightly shorter reach
                        AttributeModifier.Operation.ADDITION
                )
        );

        this.defaultModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    // Apply custom knockback on hit, the ghetto way
    @Override
    public boolean hurtEnemy(ItemStack stack, net.minecraft.world.entity.LivingEntity target, net.minecraft.world.entity.LivingEntity attacker) {
        if (!attacker.level().isClientSide()) {
            // Custom knockback strength
            float knockbackStrength = 1.0F;

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
        tooltip.add(Component.literal("Freezing to the touch and quite heavy")
                .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }
}