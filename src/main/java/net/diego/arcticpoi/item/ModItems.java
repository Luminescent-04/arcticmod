package net.diego.arcticpoi.item;

import net.diego.arcticpoi.ArcticPoi;
import net.diego.arcticpoi.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ArcticPoi.MOD_ID);

    public static final RegistryObject<Item> RADIO =
            ITEMS.register("radio",
                    () -> new BlockItem(ModBlocks.RADIO.get(),
                            new Item.Properties()));

    public static final RegistryObject<Item> CRATE =
            ITEMS.register("crate",
            () -> new BlockItem(ModBlocks.CRATE.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> CHAIR =
            ITEMS.register("chair",
                    () -> new BlockItem(ModBlocks.CHAIR.get(),
                            new Item.Properties()));

    public static final RegistryObject<Item> WOODSTACK =
            ITEMS.register("woodstack",
                    () -> new BlockItem(ModBlocks.WOODSTACK.get(),
                            new Item.Properties()));

    public static final RegistryObject<Item> BROKENCHAIR =
            ITEMS.register("brokenchair",
                    () -> new BlockItem(ModBlocks.BROKENCHAIR.get(),
                            new Item.Properties()));

}
