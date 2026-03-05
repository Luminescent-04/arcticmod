package net.diego.arcticpoi.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public class ModArmorMaterials {

    /**
     * Creates a custom armor material
     *
     * @param name The name of the armor material (for registry)
     * @param durabilityMultiplier How many uses per piece (base is 13, so 15 means 13*15=195 durability)
     * @param protectionAmounts Protection values: [boots, leggings, chestplate, helmet]
     * @param enchantability How easy to enchant (higher = more enchantments)
     * @param equipSound Sound when armor is equipped
     * @param knockbackResistance Knockback resistance (0-1)
     * @param repairIngredient What item repairs this armor
     */
    public static ArmorMaterial createArmorMaterial(String name, int durabilityMultiplier,
                                                      int[] protectionAmounts, int enchantability,
                                                      Supplier<ArmorMaterial> equipSound, float knockbackResistance,
                                                      Supplier<Ingredient> repairIngredient) {
        return new ArmorMaterial() {
            @Override
            public int getDurabilityForType(ArmorItem.Type type) {
                return new int[]{13, 15, 16, 11}[type.ordinal()] * durabilityMultiplier;
            }

            @Override
            public int getDefenseForType(ArmorItem.Type type) {
                return protectionAmounts[type.ordinal()];
            }

            @Override
            public int getEnchantmentValue() {
                return enchantability;
            }

            @Override
            public net.minecraft.sounds.SoundEvent getEquipSound() {
                return SoundEvents.ARMOR_EQUIP_GENERIC;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return repairIngredient.get();
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public float getToughness() {
                return 0.0f; // Reduces damage from strong attacks
            }

            @Override
            public float getKnockbackResistance() {
                return knockbackResistance;
            }
        };
    }

    // MASK_ARMOR (Light armor like leather)
    // Gas mask with low durability but good enchantability and repairable with duct tape
    public static final ArmorMaterial MASK = createArmorMaterial(
            "mask",
            5, // Leather-level durability (5x multiplier)
            new int[]{1, 2, 3, 1}, // [boots, leggings, chestplate, helmet]
            15, // Good enchantability
            () -> (ArmorMaterial) SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0f,
            () -> Ingredient.of(ModItems.DUCT_TAPE.get())
    );
}





