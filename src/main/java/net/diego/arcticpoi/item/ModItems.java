package net.diego.arcticpoi.item;

import net.diego.arcticpoi.ArcticPoi;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ArcticPoi.MOD_ID);

    // Register the Pocket Knife
    public static final RegistryObject<Item> POCKETKNIFE =
            ITEMS.register("pocketknife", PocketKnifeItem::new);

    public static final RegistryObject<Item> PLANK =
            ITEMS.register("plank", PlankItem::new);

    public static final RegistryObject<Item> MALLET =
            ITEMS.register("mallet", MalletItem::new);
}