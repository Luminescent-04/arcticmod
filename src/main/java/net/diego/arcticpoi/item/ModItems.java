package net.diego.arcticpoi.item;

import net.diego.arcticpoi.ArcticPoi;
import net.diego.arcticpoi.item.armor.ProtectiveMaskItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ArcticPoi.MOD_ID);

    // CUTTERS (High damage, low range, low knockback, fast attack speed)
    public static final RegistryObject<Item> POCKETKNIFE =
            ITEMS.register("pocketknife", () -> new SimpleMeleeWeaponItem(
                    new Item.Properties(),
                    5.5f, // attack damage
                    4.0f, // attack speed
                    SimpleMeleeWeaponItem.KnockbackTier.WEAK,
                    80, //durability
                    SimpleMeleeWeaponItem.RangeType.CLOSE,
                    false, //hasSweeping
                    0.15f, // bleedChance
                    1.5f, // backstabMultiplier
                    0.00f   // armorPierce
            ));
    public static final RegistryObject<Item> SHIV =
            ITEMS.register("shiv", () -> new SimpleMeleeWeaponItem(
                    new Item.Properties(),
                    6.5f,
                    4.0f,
                    SimpleMeleeWeaponItem.KnockbackTier.WEAK,
                    25,
                    SimpleMeleeWeaponItem.RangeType.CLOSE,
                    false,
                    0.2f,
                    4.0f,
                    0.0f
            ));
    public static final RegistryObject<Item> TROUTKNIFE =
            ITEMS.register("troutknife", () -> new SimpleMeleeWeaponItem(
                    new Item.Properties(),
                    4.5f,
                    4.0f,
                    SimpleMeleeWeaponItem.KnockbackTier.WEAK,
                    100,
                    SimpleMeleeWeaponItem.RangeType.CLOSE,
                    false,
                    0.1f,
                    1.25f,
                    0.0f
            ));
    // HACKERS (Medium damage, medium range, medium knockback, medium attack speed)
    public static final RegistryObject<Item> MALLET =
            ITEMS.register("mallet", () ->
                    new SimpleMeleeWeaponItem(new Item.Properties(),
                            5.0f,
                            -2.0f,
                            SimpleMeleeWeaponItem.KnockbackTier.NORMAL,
                            170,
                            SimpleMeleeWeaponItem.RangeType.SHORT,
                            false,
                            0.0f,
                            0.0f,
                            0.2f  // armorPierce
                    ));
    public static final RegistryObject<Item> HATCHET =
            ITEMS.register("hatchet", () ->
                    new SimpleMeleeWeaponItem(new Item.Properties(),
                            6.5f,
                            -2.5f,
                            SimpleMeleeWeaponItem.KnockbackTier.NORMAL,
                            130,
                            SimpleMeleeWeaponItem.RangeType.MEDIUM,
                            false,
                            0.1f,
                            0.0f,
                            0.2f
                    ));
    public static final RegistryObject<Item> HAMMER =
            ITEMS.register("hammer", () ->
                    new SimpleMeleeWeaponItem(new Item.Properties(),
                            6.0f,
                            -2.5f,
                            SimpleMeleeWeaponItem.KnockbackTier.NORMAL,
                            200,
                            SimpleMeleeWeaponItem.RangeType.SHORT,
                            false,
                            0.0f,
                            0.0f,
                            0.2f
                    ));
    public static final RegistryObject<Item> PRYBAR =
            ITEMS.register("prybar", () ->
                    new SimpleMeleeWeaponItem(new Item.Properties(),
                            5.0f,
                            -2.0f,
                            SimpleMeleeWeaponItem.KnockbackTier.NORMAL,
                            150,
                            SimpleMeleeWeaponItem.RangeType.SHORT,
                            false,
                            0.0f,
                            0.0f,
                            0.3f
                    ));
    public static final RegistryObject<Item> MACHETE =
            ITEMS.register("machete", () ->
                    new SimpleMeleeWeaponItem(new Item.Properties(),
                            7.0f,
                            -2.2f,
                            SimpleMeleeWeaponItem.KnockbackTier.NORMAL,
                            120,
                            SimpleMeleeWeaponItem.RangeType.MEDIUM,
                            true, // hasSweeping
                            0.1f, // bleedChance
                            0.0f, // backstabMultiplier
                            0.0f   // armorPierce
                    ));
    //BREAKERS (High durability, high knockback, low damage, slow attack speed)
    public static final RegistryObject<Item> PLANK =
            ITEMS.register("plank", () -> new SimpleMeleeWeaponItem(
                    new Item.Properties(),
                    4.5f,
                    -3.2f,
                    SimpleMeleeWeaponItem.KnockbackTier.STRONG,
                    50,
                    SimpleMeleeWeaponItem.RangeType.MEDIUM,
                    true,
                    0.0f,
                    0.0f,
                    0.0f
            ));
    public static final RegistryObject<Item> PIPE =
            ITEMS.register("pipe", () -> new SimpleMeleeWeaponItem(
                    new Item.Properties(),
                    2.5f,
                    -3.2f,
                    SimpleMeleeWeaponItem.KnockbackTier.STRONG,
                    250,
                    SimpleMeleeWeaponItem.RangeType.MEDIUM,
                    true,
                    0.0f,
                    0.0f,
                    0.0f
            ));
    public static final RegistryObject<Item> LUMBERAXE =
            ITEMS.register("lumberaxe", () -> new SimpleMeleeWeaponItem(
                    new Item.Properties(),
                    7.5f, // attack damage
                    -3.75f, // attack speed
                    SimpleMeleeWeaponItem.KnockbackTier.NORMAL,
                    185, //durability
                    SimpleMeleeWeaponItem.RangeType.MEDIUM,
                    false, //hasSweeping
                    0.15f, // bleedChance
                    0.0f, // backstabMultiplier
                    0.05f   // armorPierce
            ));
    public static final RegistryObject<Item> SLEDGEHAMMER =
            ITEMS.register("sledgehammer", () -> new SimpleMeleeWeaponItem(
                    new Item.Properties(),
                    8.0f,
                    -3.75f,
                    SimpleMeleeWeaponItem.KnockbackTier.VERY_STRONG,
                    300,
                    SimpleMeleeWeaponItem.RangeType.MEDIUM,
                    true,
                    0.0f,
                    0.0f,
                    0.5f
            ));
    //SPEARS (Long range, low durability, low knockback, high attack speed)
    public static final RegistryObject<Item> BOXCUTTERSPEAR =
            ITEMS.register("boxcutterspear", () -> new SimpleMeleeWeaponItem(
                    new Item.Properties(),
                    4.5f,
                    -1.0f,
                    SimpleMeleeWeaponItem.KnockbackTier.NONE,
                    60,
                    SimpleMeleeWeaponItem.RangeType.LONG,
                    false,
                    0.0f,
                    0.0f,
                    0.0f
            ));
    public static final RegistryObject<Item> SHIVSPEAR =
            ITEMS.register("shivspear", () -> new SimpleMeleeWeaponItem(
                    new Item.Properties(),
                    6.5f,
                    -1.0f,
                    SimpleMeleeWeaponItem.KnockbackTier.NONE,
                    20,
                    SimpleMeleeWeaponItem.RangeType.LONG,
                    false,
                    0.0f,
                    0.0f,
                    0.0f
            ));
    // INGREDIENTS
    public static final RegistryObject<Item> DUCT_TAPE =
            ITEMS.register("duct_tape", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SHAFT =
            ITEMS.register("shaft", () -> new Item(new Item.Properties()));

    // ARMOR - PROTECTIVE MASKS
    // All masks are helmet-only items with varying protection levels

    // SURGICAL MASK - Basic disposable mask (cosmetic/RP value)
    public static final RegistryObject<Item> SURGICAL_MASK = ITEMS.register("surgical_mask",
            () -> new ProtectiveMaskItem(ModArmorMaterials.SURGICAL_MASK, ArmorItem.Type.HELMET,
                    new Item.Properties(), "surgical_mask", ProtectiveMaskItem.ProtectionLevel.NONE));

    // RESPIRATOR (N95/FFP2) - Reduces poison duration
    public static final RegistryObject<Item> RESPIRATOR = ITEMS.register("respirator",
            () -> new ProtectiveMaskItem(ModArmorMaterials.RESPIRATOR, ArmorItem.Type.HELMET,
                    new Item.Properties(), "halfmask", ProtectiveMaskItem.ProtectionLevel.MINIMAL));

    // HALF-FACE MASK - Blocks poison, reduces other airborne effects
    public static final RegistryObject<Item> HALFMASK = ITEMS.register("halfmask",
            () -> new ProtectiveMaskItem(ModArmorMaterials.HALFMASK, ArmorItem.Type.HELMET,
                    new Item.Properties(), "halfmask", ProtectiveMaskItem.ProtectionLevel.MODERATE));

    // FULL-FACE MASK - Blocks poison, blindness, nausea (protects eyes)
    public static final RegistryObject<Item> FULLMASK = ITEMS.register("fullmask",
            () -> new ProtectiveMaskItem(ModArmorMaterials.FULLMASK, ArmorItem.Type.HELMET,
                    new Item.Properties(), "fullmask", ProtectiveMaskItem.ProtectionLevel.HIGH));

    // GASMASK - Military-grade: Complete poison & wither immunity
    public static final RegistryObject<Item> GASMASK = ITEMS.register("gasmask",
            () -> new ProtectiveMaskItem(ModArmorMaterials.GASMASK, ArmorItem.Type.HELMET,
                    new Item.Properties(), "gasmask", ProtectiveMaskItem.ProtectionLevel.COMPLETE));

    // PAPR SYSTEM - Powered respirator: Complete protection + regeneration
    public static final RegistryObject<Item> PAPR_SYSTEM = ITEMS.register("papr_system",
            () -> new ProtectiveMaskItem(ModArmorMaterials.PAPR_SYSTEM, ArmorItem.Type.HELMET,
                    new Item.Properties(), "papr_system", ProtectiveMaskItem.ProtectionLevel.POWERED));

}