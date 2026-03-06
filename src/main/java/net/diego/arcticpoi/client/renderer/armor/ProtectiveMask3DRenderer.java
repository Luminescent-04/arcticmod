package net.diego.arcticpoi.client.renderer.armor;

import net.diego.arcticpoi.client.model.armor.ProtectiveMask3DModel;
import net.diego.arcticpoi.item.armor.ProtectiveMaskItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ProtectiveMask3DRenderer extends GeoArmorRenderer<ProtectiveMaskItem> {

    public ProtectiveMask3DRenderer(String modelName) {
        super(new ProtectiveMask3DModel());
        ProtectiveMask3DModel model = (ProtectiveMask3DModel) this.model;
        model.setModelName(modelName);
    }
}

