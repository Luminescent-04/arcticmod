package net.diego.arcticpoi.item;

import net.diego.arcticpoi.ArcticPoi;
import net.diego.arcticpoi.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused") // ARCTICPOI_TAB is registered via Forge
public class CreativeTab {

    // Deferred register for the creative tab
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ArcticPoi.MOD_ID);

    // The actual creative tab - kept public for Forge registration
    public static final RegistryObject<CreativeModeTab> ARCTICPOI_TAB = TABS.register("arctic_essentials",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.arctic_essentials")) // translation key in en_us.json
                    .icon(() -> new ItemStack(ModItems.HAMMER.get()))        // tab icon
                    .displayItems((params, output) -> {
                        // Add all blocks automatically
                        ModBlocks.BLOCKS.getEntries().forEach(block -> output.accept(block.get().asItem()));

                        // Add all items automatically (skip BlockItems since already added)
                        ModItems.ITEMS.getEntries().forEach(item -> {
                            if (!(item.get() instanceof BlockItem)) {
                                output.accept(item.get());
                            }
                        });
                    })
                    .build()
    );
}
