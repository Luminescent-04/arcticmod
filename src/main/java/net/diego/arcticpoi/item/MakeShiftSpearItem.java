package net.diego.arcticpoi.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
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

public class MakeShiftSpearItem extends DiggerItem {

    private static final UUID MAKESHIFTSPEAR_REACH_UUID =
            UUID.fromString("95191051-2e25-4af9-bb31-254f10587c97"); //UUID MUST BE DIFFERENT FOR EVERY WEAPON

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public MakeShiftSpearItem() {
        super(
                2.25f,                   // Attack damage
                -1.0f,                  // Attack speed (negative = faster)
                ModTiers.IMPROVISED,    // Custom tier
                BlockTags.MINEABLE_WITH_HOE, // What blocks it can break
                new Item.Properties().durability(100) // Standard item properties
        );

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder =
                ImmutableMultimap.builder();

        builder.putAll(super.getDefaultAttributeModifiers(EquipmentSlot.MAINHAND));

        builder.put(
                ForgeMod.ENTITY_REACH.get(),
                new AttributeModifier(
                        MAKESHIFTSPEAR_REACH_UUID,
                        "Spear reach",
                        1.5D, // longer than normal reach
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
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);

        // Remove knockback velocity
        target.setDeltaMovement(
                target.getDeltaMovement().x * 0.1,
                target.getDeltaMovement().y *0.1,
                target.getDeltaMovement().z * 0.1
        );

        return result;
    }
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);

        // Add gray italic tooltip
        tooltip.add(Component.literal("Thin blade, thinner stick, held by hopes and dreams")
                .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }
}