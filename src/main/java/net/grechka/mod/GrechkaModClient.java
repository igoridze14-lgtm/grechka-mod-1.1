package net.grechka.mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class GrechkaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(GrechkaModelLayer.GRECHKA_LAYER, GrechkaModel::getTexturedModelData);
        EntityRendererRegistry.register(GreczkaMod.GRECHKA, GrechkaRenderer::new);
    }
}
