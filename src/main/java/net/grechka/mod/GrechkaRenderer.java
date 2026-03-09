package net.grechka.mod;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class GrechkaRenderer extends MobEntityRenderer<GrechkaEntity, GrechkaModel<GrechkaEntity>> {
    
    private static final Identifier TEXTURE = new Identifier("grechka", "textures/entity/grechka/grechka.png");

    public GrechkaRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new GrechkaModel<>(ctx.getPart(GrechkaModelLayer.GRECHKA_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(GrechkaEntity entity) {
        return TEXTURE;
    }
}
