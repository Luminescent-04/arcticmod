package net.diego.arcticpoi.block;

import net.diego.arcticpoi.ArcticPoi;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ArcticPoi.MOD_ID);

    public static final RegistryObject<Block> RADIO = BLOCKS.register("radio",
            () -> new Block(BlockBehaviour.Properties
                    .of()
                    .strength(2.0f)
            ));
}
