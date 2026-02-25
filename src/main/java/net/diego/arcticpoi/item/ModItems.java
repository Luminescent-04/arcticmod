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
    // Simple Weapons
    // -------------------------------

    public static final RegistryObject<Item> PLANK =
            ITEMS.register("plank", () ->
                    new SimpleMeleeWeaponItem(
                            new Item.Properties(),
                            2.0f,   // damage
                            -3.4f,  // speed
                            0.3f,    // knockback
                            50,     // durability
                            0.0f    // reach
                    )
            );

    public static final RegistryObject<Item> PIPE =
            ITEMS.register("pipe", () ->
                    new SimpleMeleeWeaponItem(
                            new Item.Properties(),
                            2.5f, //damage
                            -3.8f, //atk speed
                            0.6f, //knockback
                            150, //durability
                            0.0f // reach
                    )
            );

    public static final RegistryObject<Item> POCKETKNIFE =
            ITEMS.register("pocketknife", () ->
                    new SimpleMeleeWeaponItem(
                            new Item.Properties(),
                            1.5f,   // damage
                            -2.0f,  // speed
                            0.1f,   // knockback
                            40,      // durability
                            -1.0f    // reach (shorter)
                    )
            );

    public static final RegistryObject<Item> SCRAPKNIFE =
            ITEMS.register("scrapknife", () ->
                    new SimpleMeleeWeaponItem(
                            new Item.Properties(),
                            2.2f,   // damage
                            -2.4f,  // speed
                            0.2f,   // knockback
                            60,      // durability
                            -0.75f   // reach (shorter)
                    )
            );

    public static final RegistryObject<Item> FISHINGSPEAR =
            ITEMS.register("fishingspear", () ->
                    new SimpleMeleeWeaponItem(
                            new Item.Properties(),
                            2.0f,   // damage
                            -2.8f,  // speed
                            0.0f,   // knockback
                            55,      // durability
                            1.0f     // reach (longer)
                    )
            );

    public static final RegistryObject<Item> MAKESHIFTSPEAR =
            ITEMS.register("makeshiftspear", () ->
                    new SimpleMeleeWeaponItem(
                            new Item.Properties(),
                            2.5f,   // damage
                            -3.0f,  // speed
                            0.0f,   // knockback
                            65,      // durability
                            1.25f    // reach (longer)
                    )
            );

    public static final RegistryObject<Item> MALLET =
            ITEMS.register("mallet", () ->
                    new SimpleMeleeWeaponItem(
                            new Item.Properties(),
                            4.0f,   // damage
                            -3.5f,  // speed
                            1.0f,   // knockback
                            100,     // durability
                            0.0f     // reach
                    )
            );

    // -------------------------------
    // Ingredients for upgrades
    // (Register these when they are used in crafting recipes)
    // public static final RegistryObject<Item> DUCT_TAPE =
    //         ITEMS.register("duct_tape", () -> new Item(new Item.Properties()));
    // public static final RegistryObject<Item> SHAFT =
    //         ITEMS.register("shaft", () -> new Item(new Item.Properties()));
    // public static final RegistryObject<Item> SERRATED_BLADE =
    //         ITEMS.register("serrated_blade", () -> new Item(new Item.Properties()));

}