package net.diego.arcticpoi;

import com.mojang.logging.LogUtils;
import net.diego.arcticpoi.block.ModBlocks;
import net.diego.arcticpoi.item.ModItems;
import net.diego.arcticpoi.sound.ModSounds; // <-- import your sound registry
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ArcticPoi.MOD_ID)
public class ArcticPoi {

    public static final String MOD_ID = "arcticpoi";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ArcticPoi(FMLJavaModLoadingContext context) {

        // Use this mod event bus for registrations
        IEventBus modEventBus = context.getModEventBus();

        // Register blocks and items
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);

        // Register custom sounds
        ModSounds.SOUND_EVENTS.register(modEventBus);

        // Lifecycle listeners
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        // Register to the main Forge event bus (for gameplay events)
        MinecraftForge.EVENT_BUS.register(this);

        // Config
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("{}{}", Config.magicNumberIntroduction, Config.magicNumber);
        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == net.minecraft.world.item.CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModItems.RADIO.get());
            event.accept(ModItems.CRATE.get());
        }
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Client setup logic
        }
    }
}
