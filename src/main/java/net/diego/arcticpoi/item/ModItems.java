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
}
