package net.diego.arcticpoi.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class SimpleMeleeWeaponItem extends Item {

    private static final UUID DAMAGE_UUID =
            UUID.fromString("fa233e1c-4180-4865-b01b-bcce9785aca3");
    private static final UUID SPEED_UUID =
            UUID.fromString("fa233e1c-4180-4865-b01b-bcce9785aca4");
    private static final UUID KNOCKBACK_UUID =
            UUID.fromString("fa233e1c-4180-4865-b01b-bcce9785aca5");
    private static final UUID REACH_UUID =
            UUID.fromString("fa233e1c-4180-4865-b01b-bcce9785aca6");

    private final float damage;
    private final float speed;
    private final KnockbackTier knockbackTier;
    private final RangeType rangeType;
    private final boolean hasSweeping;
    private final float bleedChance;
    private final float backstabMultiplier;
    private final float armorPierce;              // % armor ignored (0.0f - 1.0f)

    private static final Random RANDOM = new Random();

    public enum RangeType {
        CLOSE, SHORT, MEDIUM, LONG
    }

    public enum KnockbackTier {
        NONE(0.0f),
        WEAK(0.0f),
        NORMAL(0.6f),
        STRONG(1.0f),
        VERY_STRONG(1.2f),
        WEIGHTED(1.6f);

        public final float value;

        KnockbackTier(float value) {
            this.value = value;
        }
    }

    public enum DurabilityTier {
        FRAGILE,
        FLIMSY,
        STANDARD,
        DURABLE,
        STURDY,
        REINFORCED
    }

    public SimpleMeleeWeaponItem(
            @NotNull Properties properties,
            float damage,
            float speed,
            KnockbackTier knockbackTier,
            int durability,
            RangeType rangeType,
            boolean hasSweeping,
            float bleedChance,
            float backstabMultiplier,
            float armorPierce
    ) {
        super(properties.durability(durability));
        this.damage = damage;
        this.speed = speed;
        this.knockbackTier = knockbackTier;
        this.rangeType = rangeType;
        this.hasSweeping = hasSweeping;
        this.bleedChance = bleedChance;
        this.backstabMultiplier = backstabMultiplier;
        this.armorPierce = armorPierce;
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        stack.hideTooltipPart(ItemStack.TooltipPart.MODIFIERS);
        return stack;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack,
                             @NotNull LivingEntity target,
                             @NotNull LivingEntity attacker) {

        boolean result = super.hurtEnemy(stack, target, attacker);

        if (!attacker.level().isClientSide()) {
            // BLEED
            if (bleedChance > 0.0F && RANDOM.nextFloat() < bleedChance) {
                target.addEffect(new MobEffectInstance(
                        MobEffects.WITHER,
                        600,
                        0
                ));
            }
            // ARMOR PIERCE
            if (armorPierce > 0f) {
                float armor = target.getArmorValue();
                float bonusDamage = armor * armorPierce * 0.5f;
                target.hurt(
                        attacker.damageSources().generic(),
                        bonusDamage
                );
            }
            //KNOCKBACK
            if (knockbackTier != KnockbackTier.NONE) {

                double dx = attacker.getX() - target.getX();
                double dz = attacker.getZ() - target.getZ();

                target.knockback(knockbackTier.value, dx, dz);

            } else {
                target.setDeltaMovement(0, target.getDeltaMovement().y, 0);
            }
            // SWEEPING
            if (hasSweeping && attacker instanceof Player player) {

                double sweepRange = 1.0D;
                AABB area = target.getBoundingBox().inflate(sweepRange, 0.25D, sweepRange);

                for (LivingEntity entity :
                        attacker.level().getEntitiesOfClass(LivingEntity.class, area)) {

                    if (entity != attacker &&
                            entity != target &&
                            !attacker.isAlliedTo(entity)) {

                        entity.knockback(
                                0.4F,
                                Math.sin(attacker.getYRot() * ((float)Math.PI / 180F)),
                                -Math.cos(attacker.getYRot() * ((float)Math.PI / 180F))
                        );

                        entity.hurt(
                                player.damageSources().playerAttack(player),
                                1.0F
                        );
                    }
                }
            }

            // BACKSTAB
            boolean isBackstab = false;

            if (attacker instanceof Player) {
                double attackerYaw = attacker.getYRot();
                double targetYaw = target.getYRot();
                double angleDiff = Math.abs(attackerYaw - targetYaw) % 360;

                if (angleDiff < 30 || angleDiff > 330) {
                    isBackstab = true;
                }
            }

            if (isBackstab && backstabMultiplier > 1.0f) {
                target.hurt(
                        target.damageSources().generic(),
                        damage * (backstabMultiplier - 1.0f)
                );
            }
        }

        stack.hurtAndBreak(1, attacker,
                e -> e.broadcastBreakEvent(attacker.getUsedItemHand()));

        return result;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                Level level,
                                @NotNull List<Component> tooltip,
                                @NotNull TooltipFlag flag) {

        stack.hideTooltipPart(ItemStack.TooltipPart.MODIFIERS);

        tooltip.add(Component.literal(""));

        tooltip.add(Component.literal("Damage: " + damage)
                .withStyle(ChatFormatting.WHITE));

        tooltip.add(Component.literal("Speed: " + speed)
                .withStyle(ChatFormatting.WHITE));

        // Durability Tier
        DurabilityTier tier = getDurabilityTier(stack.getMaxDamage());
        tooltip.add(Component.literal("Durability: " + formatDurability(tier))
                .withStyle(getDurabilityColor(tier)));

        tooltip.add(Component.literal("Reach: " + formatReach())
                .withStyle(getReachColor()));

        tooltip.add(Component.literal("Knockback: " + formatKnockback())
                .withStyle(getKnockbackColor()));

        if (bleedChance > 0f) {
            tooltip.add(Component.literal("Bleed Chance: " + (int)(bleedChance * 100) + "%")
                    .withStyle(ChatFormatting.DARK_RED));
        }
        if (backstabMultiplier > 1.0f) {
            tooltip.add(Component.literal("Backstab: x" + backstabMultiplier)
                    .withStyle(ChatFormatting.DARK_RED));
        }
        if (armorPierce > 0f) {
            tooltip.add(Component.literal("Armor Pierce: " + (int)(armorPierce * 100) + "%")
                    .withStyle(ChatFormatting.WHITE));
        }

        super.appendHoverText(stack, level, tooltip, flag);
    }

    private DurabilityTier getDurabilityTier(int maxDurability) {

        if (maxDurability <= 40) return DurabilityTier.FRAGILE;
        if (maxDurability <= 120) return DurabilityTier.FLIMSY;
        if (maxDurability <= 180) return DurabilityTier.STANDARD;
        if (maxDurability <= 350) return DurabilityTier.DURABLE;
        if (maxDurability <= 600) return DurabilityTier.STURDY;

        return DurabilityTier.REINFORCED;
    }

    private String formatDurability(DurabilityTier tier) {
        return switch (tier) {
            case FRAGILE -> "Fragile";
            case FLIMSY -> "Flimsy";
            case STANDARD -> "Standard";
            case DURABLE -> "Durable";
            case STURDY -> "Sturdy";
            case REINFORCED -> "REINFORCED";
        };
    }

    private ChatFormatting getDurabilityColor(DurabilityTier tier) {
        return switch (tier) {
            case FRAGILE -> ChatFormatting.DARK_GRAY;
            case FLIMSY -> ChatFormatting.GRAY;
            case STANDARD -> ChatFormatting.WHITE;
            case DURABLE -> ChatFormatting.YELLOW;
            case STURDY -> ChatFormatting.GOLD;
            case REINFORCED -> ChatFormatting.BLUE;
        };
    }


    private ChatFormatting getReachColor() {
        return switch (rangeType) {
            case CLOSE -> ChatFormatting.DARK_GRAY;
            case SHORT -> ChatFormatting.GRAY;
            case MEDIUM -> ChatFormatting.WHITE;
            case LONG -> ChatFormatting.YELLOW;
        };
    }

    private ChatFormatting getKnockbackColor() {
        return switch (knockbackTier) {
            case NONE -> ChatFormatting.DARK_GRAY;
            case WEAK -> ChatFormatting.GRAY;
            case NORMAL -> ChatFormatting.WHITE;
            case STRONG -> ChatFormatting.YELLOW;
            case VERY_STRONG -> ChatFormatting.GOLD;
            case WEIGHTED -> ChatFormatting.BLUE;
        };
    }

    private String formatReach() {
        return switch (rangeType) {
            case CLOSE -> "Close";
            case SHORT -> "Short";
            case MEDIUM -> "Medium";
            case LONG -> "Long";
        };
    }

    private String formatKnockback() {
        return switch (knockbackTier) {
            case NONE -> "None";
            case WEAK -> "Weak";
            case NORMAL -> "Normal";
            case STRONG -> "Strong";
            case VERY_STRONG -> "Very Strong";
            case WEIGHTED -> "WEIGHTED";
        };
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack,
                                             @NotNull Enchantment enchantment) {
        if (enchantment == Enchantments.SWEEPING_EDGE) {
            return hasSweeping;
        }
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier>
    getDefaultAttributeModifiers(@NotNull EquipmentSlot slot) {

        if (slot == EquipmentSlot.MAINHAND) {

            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder =
                    ImmutableMultimap.builder();

            builder.put(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(DAMAGE_UUID,
                            "Weapon damage",
                            damage,
                            AttributeModifier.Operation.ADDITION));

            builder.put(Attributes.ATTACK_SPEED,
                    new AttributeModifier(SPEED_UUID,
                            "Weapon speed",
                            speed,
                            AttributeModifier.Operation.ADDITION));

            // Only add knockback attribute if not NONE
            if (knockbackTier != KnockbackTier.NONE) {
                builder.put(Attributes.ATTACK_KNOCKBACK,
                        new AttributeModifier(KNOCKBACK_UUID,
                                "Weapon knockback",
                                knockbackTier.value,
                                AttributeModifier.Operation.ADDITION));
            }

            float reach = switch (rangeType) {
                case CLOSE -> -1.25f;
                case SHORT -> -0.5f;
                case MEDIUM -> 0.0f;
                case LONG -> 1.25f;
            };

            if (reach != 0.0f) {
                builder.put(
                        ForgeMod.ENTITY_REACH.get(),
                        new AttributeModifier(REACH_UUID,
                                "Weapon reach",
                                reach,
                                AttributeModifier.Operation.ADDITION)
                );
            }

            return builder.build();
        }

        return super.getDefaultAttributeModifiers(slot);
    }
}