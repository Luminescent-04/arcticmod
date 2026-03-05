package net.diego.arcticpoi.client.renderer.armor;

import net.diego.arcticpoi.client.model.armor.Respirator3DModel;
import net.diego.arcticpoi.item.armor.RespiratorsItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class Respirator3DRenderer extends GeoArmorRenderer<RespiratorsItem> {

    public Respirator3DRenderer(String modelName) {
        super(new Respirator3DModel());
        Respirator3DModel model = (Respirator3DModel) this.model;
        model.setModelName(modelName);
    }
}
