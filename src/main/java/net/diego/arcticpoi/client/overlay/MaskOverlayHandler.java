package net.diego.arcticpoi.client.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.diego.arcticpoi.ArcticPoi;
import net.diego.arcticpoi.item.armor.ProtectiveMaskItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArcticPoi.MOD_ID, value = Dist.CLIENT)
public final class MaskOverlayHandler {

    private MaskOverlayHandler() {}

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.getCameraType().isMirrored()) return;

        // Only replace the helmet overlay pass (vanilla pumpkin-style behavior)
        if (!event.getOverlay().id().equals(net.minecraftforge.client.gui.overlay.VanillaGuiOverlay.HELMET.id())) {
            return;
        }

        ItemStack head = mc.player.getItemBySlot(EquipmentSlot.HEAD);
        if (!(head.getItem() instanceof ProtectiveMaskItem mask)) return;

        ResourceLocation overlay = getOverlayFor(mask);
        if (overlay == null) return;

        GuiGraphics gui = event.getGuiGraphics();
        int w = mc.getWindow().getGuiScaledWidth();
        int h = mc.getWindow().getGuiScaledHeight();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,0.75F);

        gui.blit(overlay,0,0, -90,0.0F,0.0F, w, h, w, h);

        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.disableBlend();

    }

    private static ResourceLocation getOverlayFor(ProtectiveMaskItem mask) {
        // Only explicit per-item overrides - no fallback to prevent unwanted overlays
        String itemPath = net.minecraft.core.registries.BuiltInRegistries.ITEM
                .getKey(mask)
                .getPath();

        switch (itemPath) {
            case "halfmask":
                return ResourceLocation.fromNamespaceAndPath(ArcticPoi.MOD_ID, "textures/misc/overlay/halfmaskoverlay.png");
            case "fullmask":
                return ResourceLocation.fromNamespaceAndPath(ArcticPoi.MOD_ID, "textures/misc/overlay/fullmaskoverlay.png");
            case "gasmask":
                return ResourceLocation.fromNamespaceAndPath(ArcticPoi.MOD_ID, "textures/misc/overlay/gasmaskoverlay.png");
            case "paprsystem":
                return ResourceLocation.fromNamespaceAndPath(ArcticPoi.MOD_ID, "textures/misc/overlay/paproverlay.png");
            default:
                // No overlay for unknown mask types
                return null;
        }
    }

}
