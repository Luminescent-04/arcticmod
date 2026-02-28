package net.diego.arcticpoi.item;

import net.diego.arcticpoi.ArcticPoi;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ArcticPoi.MOD_ID);

    // -------------------------------
    // CUTTERS (Fast, bleed, fragile)
    // -------------------------------

    public static final RegistryObject<Item> POCKETKNIFE =
            ITEMS.register("pocketknife", () -> new SimpleMeleeWeaponItem(
                    new Item.Properties(),
                    5.5f,
                    4.0f,
                    SimpleMeleeWeaponItem.KnockbackTier.WEAK,
                    80,
                    SimpleMeleeWeaponItem.RangeType.CLOSE,
                    false,
                    0.15f,
                    1.5f,
                    0.05f,   // armorPierce
                    false // no execution
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
                    0.1f,    // armorPierce
                    false
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
                    0.05f,
                    false
            ));

    // -------------------------------
    // HACKERS (Balanced)
    // -------------------------------

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
                            0.1f,  // armorPierce
                            false
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
                            0.15f,
                            false
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
                            0.2f,
                            false
                    ));

    // -------------------------------
    // BREAKERS (Crowd control snowball)
    // -------------------------------

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
                    0.0f,
                    true
            ));

    public static final RegistryObject<Item> PIPE =
            ITEMS.register("pipe", () -> new SimpleMeleeWeaponItem(
                    new Item.Properties(),
                    2.5f,
                    -3.8f,
                    SimpleMeleeWeaponItem.KnockbackTier.STRONG,
                    250,
                    SimpleMeleeWeaponItem.RangeType.MEDIUM,
                    true,
                    0.0f,
                    0.0f,
                    0.2f,
                    true
            ));

    // -------------------------------
    // SPEARS (Reach, precision, low durability)
    // -------------------------------

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
                    0.1f,
                    false
            ));

    public static final RegistryObject<Item> SHIVSPEAR =
            ITEMS.register("shivspear", () -> new SimpleMeleeWeaponItem(
                    new Item.Properties(),
                    10.5f,
                    -1.0f,
                    SimpleMeleeWeaponItem.KnockbackTier.NONE,
                    20,
                    SimpleMeleeWeaponItem.RangeType.LONG,
                    false,
                    0.0f,
                    0.0f,
                    0.25f,
                    false
            ));

    public static final RegistryObject<Item> POST =
            ITEMS.register("post", () -> new SimpleMeleeWeaponItem(
                    new Item.Properties(),
                    2.0f,
                    -2.0f,
                    SimpleMeleeWeaponItem.KnockbackTier.NONE,
                    120,
                    SimpleMeleeWeaponItem.RangeType.LONG,
                    false,
                    0.0f,
                    0.0f,
                    0.05f,
                    false
            ));

    // -------------------------------
    // INGREDIENTS
    // -------------------------------

    public static final RegistryObject<Item> DUCT_TAPE =
            ITEMS.register("duct_tape", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHAFT =
            ITEMS.register("shaft", () -> new Item(new Item.Properties()));
}