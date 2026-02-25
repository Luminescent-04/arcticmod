package net.diego.arcticpoi.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
    private final float knockback;
    private final int durability;
    private final float reach;

    public SimpleMeleeWeaponItem(
            Properties properties,
            float damage,
            float speed,
            float knockback,
            int durability,
            float reach
    ) {
        super(properties.durability(durability));
        this.damage = damage;
        this.speed = speed;
        this.knockback = knockback;
        this.durability = durability;
        this.reach = reach;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {

            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder =
                    ImmutableMultimap.builder();

            builder.put(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(
                            DAMAGE_UUID,
                            "Weapon damage",
                            damage,
                            AttributeModifier.Operation.ADDITION
                    ));

            builder.put(Attributes.ATTACK_SPEED,
                    new AttributeModifier(
                            SPEED_UUID,
                            "Weapon speed",
                            speed,
                            AttributeModifier.Operation.ADDITION
                    ));

            builder.put(Attributes.ATTACK_KNOCKBACK,
                    new AttributeModifier(
                            KNOCKBACK_UUID,
                            "Weapon knockback",
                            knockback,
                            AttributeModifier.Operation.ADDITION
                    ));

            // Add reach modifier if nonzero
            if (reach != 0.0f) {
                builder.put(
                        net.minecraftforge.common.ForgeMod.ENTITY_REACH.get(),
                        new AttributeModifier(
                                REACH_UUID,
                                "Weapon reach",
                                reach,
                                AttributeModifier.Operation.ADDITION
                        )
                );
            }

            return builder.build();
        }

        return super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, net.minecraft.world.entity.LivingEntity target, net.minecraft.world.entity.LivingEntity attacker) {
        if (!attacker.level().isClientSide()) {
            // Handle knockback and pull
            double dx = attacker.getX() - target.getX();
            double dz = attacker.getZ() - target.getZ();
            double directionX = dx;
            double directionZ = dz;
            float strength = Math.abs(knockback);
            if (knockback < 0) {
                // Pull: reverse direction
                directionX = -dx;
                directionZ = -dz;
            }
            target.knockback(strength, directionX, directionZ);
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}