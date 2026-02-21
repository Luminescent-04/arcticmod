package net.diego.arcticpoi.item;

import net.diego.arcticpoi.ArcticPoi;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ArcticPoi.MOD_ID);

    //custom weapons
    public static final RegistryObject<Item> POCKETKNIFE =
            ITEMS.register("pocketknife", PocketKnifeItem::new);
    public static final RegistryObject<Item> PLANK =
            ITEMS.register("plank", PlankItem::new);
    public static final RegistryObject<Item> MALLET =
            ITEMS.register("mallet", MalletItem::new);
    public static final RegistryObject<Item> FISHINGSPEAR =
            ITEMS.register("fishingspear", FishingSpearItem::new);
    public static final RegistryObject<Item> MAKESHIFTSPEAR =
            ITEMS.register("makeshiftspear", MakeShiftSpearItem::new);
    public static final RegistryObject<Item> SCRAPKNIFE =
            ITEMS.register("scrapknife", ScrapKnifeItem::new);
    public static final RegistryObject<Item> RUSTYPIPE =
            ITEMS.register("rustypipe", RustyPipeItem::new);
    public static final RegistryObject<Item> SCRAPKNIFE_S =
            ITEMS.register("scrapknife_s", ScrapKnife_SItem::new);
    public static final RegistryObject<Item> CLUBHAMMER =
            ITEMS.register("clubhammer", ClubHammerItem::new);




    //custom item ingredients
    public static final RegistryObject<Item> DUCT_TAPE =
            ITEMS.register("duct_tape",
                    () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SHAFT =
            ITEMS.register("shaft",
                    () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SERRATEDBLADE =
            ITEMS.register("serratedblade",
                    () -> new Item(new Item.Properties()));
}