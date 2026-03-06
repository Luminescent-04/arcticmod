package net.diego.arcticpoi.client.model.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import net.diego.arcticpoi.ArcticPoi;
import net.diego.arcticpoi.item.armor.ProtectiveMaskItem;

public class ProtectiveMask3DModel extends GeoModel<ProtectiveMaskItem> {
    private String modelName;

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public ResourceLocation getModelResource(ProtectiveMaskItem object) {
        String name = modelName != null ? modelName : object.getModelName();
        return ResourceLocation.fromNamespaceAndPath(ArcticPoi.MOD_ID,
                "geo/armor/" + name + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ProtectiveMaskItem object) {
        String name = modelName != null ? modelName : object.getModelName();
        return ResourceLocation.fromNamespaceAndPath(ArcticPoi.MOD_ID,
                "textures/armor/" + name + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(ProtectiveMaskItem object) {
        String name = modelName != null ? modelName : object.getModelName();
        return ResourceLocation.fromNamespaceAndPath(ArcticPoi.MOD_ID,
                "animations/armor/" + name + ".animation.json");
    }
}

