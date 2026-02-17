package net.diego.arcticpoi.block;

import net.diego.arcticpoi.ArcticPoi;
import net.diego.arcticpoi.block.custom.RotationalBlock;
import net.diego.arcticpoi.block.custom.RotationalNoCollideBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    // This creates a DeferredRegister for blocks.
    // It tells Forge: "I will be registering blocks for my mod."
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ArcticPoi.MOD_ID);

    // RADIO BLOCK REGISTRATION
    public static final RegistryObject<Block> RADIO = BLOCKS.register("radio",
            () -> new RotationalBlock(BlockBehaviour.Properties
                    .of()                      // Basic block settings
                    .mapColor(MapColor.METAL)  // Map color (used on maps)
                    .strength(2.0f)            // Hardness (how long to break)
                    .noOcclusion()             // Doesn't block light / full cube rendering
                    .randomTicks()             // Enables randomTick() if used
            ));

    // CRATE BLOCK REGISTRATION
    public static final RegistryObject<Block> CRATE = BLOCKS.register("crate",
            () -> new Block(BlockBehaviour.Properties
                    .of()                      // Basic block settings
                    .strength(1.5F)            // Slightly weaker than stone
                    .sound(SoundType.WOOD)     // Wood break/place sounds
            ));

    public static final RegistryObject<Block> CHAIR = BLOCKS.register("chair",
            () -> new RotationalBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));

    public static final RegistryObject<Block> WOODSTACK = BLOCKS.register("woodstack",
            () -> new RotationalBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));

    public static final RegistryObject<Block> BROKENCHAIR = BLOCKS.register("brokenchair",
            () -> new RotationalNoCollideBlock(BlockBehaviour.Properties
                    .of()
                    .strength(1.5F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));
}
