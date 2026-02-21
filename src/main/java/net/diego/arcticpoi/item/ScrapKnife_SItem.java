package net.diego.arcticpoi.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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

public class ScrapKnife_SItem extends DiggerItem {

    private static final UUID SCRAPKNIFE_S_REACH_UUID =
            UUID.fromString("03363920-34b3-492a-aae5-7f56f7f141ee"); //UUID MUST BE DIFFERENT FOR EVERY WEAPON

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ScrapKnife_SItem() {
        super(
                3.0f,                   // Attack damage
                5.0f,                  // Attack speed (negative = faster)
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
                        SCRAPKNIFE_S_REACH_UUID,
                        "Knife reach",
                        -1.25D, // shorter than normal reach
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
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);

        // Add gray italic tooltip
        tooltip.add(Component.literal("Sharper Than Ever Before!")
                .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);

        if (!attacker.level().isClientSide) {
            target.addEffect(new MobEffectInstance(
                    MobEffects.WITHER,
                    600,   // 30 seconds (20 ticks = 1 second)
                    0      // amplifier 0 = Wither I
            ));
        }

        return result;
    }
}