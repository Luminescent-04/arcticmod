package net.diego.arcticpoi.block;

import net.diego.arcticpoi.ArcticPoi;
import net.diego.arcticpoi.block.custom.RotationalBlock;
import net.diego.arcticpoi.block.custom.RotationalNoCollideBlock;
import net.diego.arcticpoi.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ArcticPoi.MOD_ID);

    private static RegistryObject<Block> registerBlock(String name,
                                                       Supplier<Block> blockSupplier) {

        RegistryObject<Block> block = BLOCKS.register(name, blockSupplier);

        ModItems.ITEMS.register(name,
                () -> new BlockItem(block.get(), new Item.Properties()));

        return block;
    }

    // RADIO
    public static final RegistryObject<Block> RADIO =
            registerBlock("radio",
                    () -> new RotationalNoCollideBlock(BlockBehaviour.Properties
                            .of()
                            .mapColor(MapColor.METAL)
                            .strength(2.5f)
                            .noOcclusion()
                            .noCollission()
                    ));

    // CRATE
    public static final RegistryObject<Block> CRATE =
            registerBlock("crate",
                    () -> new Block(BlockBehaviour.Properties
                            .of()
                            .strength(1.5F)
                            .sound(SoundType.WOOD)
                    ));

    public static final RegistryObject<Block> CHAIR =
            registerBlock("chair",
                    () -> new RotationalBlock(BlockBehaviour.Properties
                            .of()
                            .strength(1.5F)
                            .sound(SoundType.WOOD)
                            .noOcclusion()
                    ));

    public static final RegistryObject<Block> WOODSTACK =
            registerBlock("woodstack",
                    () -> new RotationalBlock(BlockBehaviour.Properties
                            .of()
                            .strength(1.5F)
                            .sound(SoundType.WOOD)
                            .noOcclusion()
                    ));

    public static final RegistryObject<Block> BROKENCHAIR =
            registerBlock("brokenchair",
                    () -> new RotationalNoCollideBlock(BlockBehaviour.Properties
                            .of()
                            .strength(1.5F)
                            .sound(SoundType.WOOD)
                            .noOcclusion()
                    ));

    public static final RegistryObject<Block> SHELF =
            registerBlock("shelf",
                    () -> new RotationalBlock(BlockBehaviour.Properties
                            .of()
                            .strength(1.5F)
                            .sound(SoundType.WOOD)
                            .noOcclusion()
                    ));

    public static final RegistryObject<Block> FOLDINGTABLE =
            registerBlock("foldingtable",
                    () -> new RotationalBlock(BlockBehaviour.Properties
                            .of()
                            .strength(2.0F)
                            .sound(SoundType.STONE)
                            .noOcclusion()
                    ));

    public static final RegistryObject<Block> WATERJUG =
            registerBlock("waterjug",
                    () -> new RotationalBlock(BlockBehaviour.Properties
                            .of()
                            .strength(1.5F)
                            .sound(SoundType.STONE)
                            .noOcclusion()
                    ));
}
