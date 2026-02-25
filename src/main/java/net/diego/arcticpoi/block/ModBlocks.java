package net.diego.arcticpoi.block;

import net.diego.arcticpoi.ArcticPoi;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.diego.arcticpoi.item.ModItems.ITEMS;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ArcticPoi.MOD_ID);

    private static RegistryObject<Block> registerBlock(String name,
                                                       Supplier<Block> blockSupplier) {

        RegistryObject<Block> block = BLOCKS.register(name, blockSupplier);

        ITEMS.register(name,
                () -> new BlockItem(block.get(), new Item.Properties()));

        return block;
    }
}
