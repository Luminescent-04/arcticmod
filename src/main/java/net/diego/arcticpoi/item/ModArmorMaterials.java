package net.diego.arcticpoi.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;
import java.util.function.Supplier;

public final class ModArmorMaterials {
    private ModArmorMaterials() {}

    // ArmorItem.Type ordinal order in 1.20.1: BOOTS, LEGGINGS, CHESTPLATE, HELMET
    private static final int[] BASE_DURABILITY_PER_TYPE = {13, 15, 16, 11};

    // Reusable defaults for gas-mask style materials
    public static final int DEFAULT_DURABILITY_MULTIPLIER = 5; // leather-ish
    public static final int[] DEFAULT_PROTECTION = {1, 2, 3, 1}; // boots, leggings, chest, helmet
    public static final int DEFAULT_ENCHANTABILITY = 15;
    public static final float DEFAULT_TOUGHNESS = 0.0f;
    public static final float DEFAULT_KB_RESIST = 0.0f;

    public static ArmorMaterial createArmorMaterial(
            String name,
            int durabilityMultiplier,
            int[] protectionAmounts,
            int enchantability,
            Supplier<SoundEvent> equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairIngredient
    ) {
        if (protectionAmounts.length != 4) {
            throw new IllegalArgumentException("protectionAmounts must be [boots, leggings, chestplate, helmet]");
        }

        final int[] defenseByType = Arrays.copyOf(protectionAmounts, protectionAmounts.length);

        return new ArmorMaterial() {
            @Override
            public int getDurabilityForType(ArmorItem.Type type) {
                return BASE_DURABILITY_PER_TYPE[type.ordinal()] * durabilityMultiplier;
            }

            @Override
            public int getDefenseForType(ArmorItem.Type type) {
                return defenseByType[type.ordinal()];
            }

            @Override
            public int getEnchantmentValue() {
                return enchantability;
            }

            @Override
            public SoundEvent getEquipSound() {
                return equipSound.get();
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
                return toughness;
            }

            @Override
            public float getKnockbackResistance() {
                return knockbackResistance;
            }
        };
    }

    public static MaskMaterialSpec maskBase(String name) {
        return new MaskMaterialSpec(name)
                .durabilityMultiplier(DEFAULT_DURABILITY_MULTIPLIER)
                .protection(DEFAULT_PROTECTION)
                .enchantability(DEFAULT_ENCHANTABILITY)
                .equipSound(() -> SoundEvents.ARMOR_EQUIP_GENERIC)
                .toughness(DEFAULT_TOUGHNESS)
                .knockbackResistance(DEFAULT_KB_RESIST)
                .repairIngredient(() -> Ingredient.of(ModItems.DUCT_TAPE.get()));
    }

    public static final class MaskMaterialSpec {
        private final String name;
        private int durabilityMultiplier;
        private int[] protection;
        private int enchantability;
        private Supplier<SoundEvent> equipSound;
        private float toughness;
        private float knockbackResistance;
        private Supplier<Ingredient> repairIngredient;

        private MaskMaterialSpec(String name) {
            this.name = name;
        }

        public MaskMaterialSpec durabilityMultiplier(int value) { this.durabilityMultiplier = value; return this; }
        public MaskMaterialSpec protection(int[] value) { this.protection = Arrays.copyOf(value, value.length); return this; }
        public MaskMaterialSpec enchantability(int value) { this.enchantability = value; return this; }
        public MaskMaterialSpec equipSound(Supplier<SoundEvent> value) { this.equipSound = value; return this; }
        public MaskMaterialSpec toughness(float value) { this.toughness = value; return this; }
        public MaskMaterialSpec knockbackResistance(float value) { this.knockbackResistance = value; return this; }
        public MaskMaterialSpec repairIngredient(Supplier<Ingredient> value) { this.repairIngredient = value; return this; }

        public ArmorMaterial build() {
            return createArmorMaterial(
                    name,
                    durabilityMultiplier,
                    protection,
                    enchantability,
                    equipSound,
                    toughness,
                    knockbackResistance,
                    repairIngredient
            );
        }
    }

    // ===== MASK VARIANTS (based on real-world protective equipment) =====

    // SURGICAL MASK - Basic disposable mask, minimal protection, very fragile
    // Real-world: single-use paper/cloth mask, filters large droplets only
    // Effect: No special protection, purely cosmetic/RP value
    public static final ArmorMaterial SURGICAL_MASK = maskBase("surgical_mask")
            .durabilityMultiplier(2)           // Very fragile
            .protection(new int[]{0, 0, 0, 0}) // No armor value
            .enchantability(5)                 // Hard to enchant
            .build();

    // RESPIRATOR (N95/FFP2) - Better filtration, disposable but sturdier
    // Real-world: N95/FFP2 masks, filters 95% of particles
    // Effect: Minimal poison resistance
    public static final ArmorMaterial RESPIRATOR = maskBase("respirator")
            .durabilityMultiplier(4)           // Fragile but better than surgical
            .protection(new int[]{0, 0, 0, 1}) // Minimal armor
            .enchantability(10)
            .build();

    // HALFMASK - Reusable rubber mask with replaceable filters
    // Real-world: 3M 6000 series, covers nose and mouth only
    // Effect: Moderate poison resistance
    public static final ArmorMaterial HALFMASK = maskBase("halfmask")
            .durabilityMultiplier(7)           // Durable rubber construction
            .protection(new int[]{0, 0, 0, 2}) // Better protection
            .enchantability(12)
            .toughness(0.5f)                   // Some impact resistance
            .build();

    // FULL-FACE MASK - Covers entire face including eyes
    // Real-world: 3M 6800 series, Scott AV-3000, protects eyes too
    // Effect: High poison resistance, blindness immunity
    public static final ArmorMaterial FULLMASK = maskBase("fullmask")
            .durabilityMultiplier(10)          // Very durable
            .protection(new int[]{0, 0, 0, 2}) // Good protection
            .enchantability(14)
            .toughness(1.0f)                   // Impact-resistant faceplate
            .build();

    // GASMASK - Military-grade full face protection
    // Real-world: M50 JSGPM, MSA Millennium, Israeli M15
    // Effect: Complete poison immunity, wither resistance, damage resistance buff
    public static final ArmorMaterial GASMASK = maskBase("gasmask")
            .durabilityMultiplier(15)          // Military durability
            .protection(new int[]{0, 0, 0, 3}) // Best protection
            .enchantability(16)
            .toughness(2.0f)                   // Enhanced military-grade materials
            .knockbackResistance(0.10f)        // Improved stability from military design
            .build();

    // PAPR SYSTEM - Powered Air Purifying Respirator
    // Real-world: 3M Versaflo, battery-powered with fan and HEPA filter
    // Effect: Complete poison immunity, constant regeneration (air supply)
    public static final ArmorMaterial PAPR_SYSTEM = maskBase("papr_system")
            .durabilityMultiplier(12)          // Electronic components are delicate
            .protection(new int[]{0, 0, 0, 2}) // Good protection but bulky
            .enchantability(20)                // High-tech, enchants well
            .toughness(0.5f)
            .equipSound(() -> SoundEvents.ARMOR_EQUIP_NETHERITE) // Mechanical sound
            .build();
}
