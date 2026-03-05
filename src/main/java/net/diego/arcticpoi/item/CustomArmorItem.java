package net.diego.arcticpoi.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.diego.arcticpoi.client.renderer.armor.Respirator3DRenderer;

public class CustomArmorItem extends ArmorItem {

    public CustomArmorItem(ArmorMaterial material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private Respirator3DRenderer renderer;

            public java.util.Optional<java.util.function.Function<ItemStack, net.minecraft.client.renderer.texture.TextureAtlasSprite>> getArmorTexture(ItemStack stack, net.minecraft.world.entity.Entity entity, EquipmentSlot slot, String type) {
                return java.util.Optional.empty();
            }});
    }
}
