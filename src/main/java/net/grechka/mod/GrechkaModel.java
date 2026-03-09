package net.grechka.mod;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

public class GrechkaModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart body;

    public GrechkaModel(ModelPart root) {
        this.body = root.getChild("body");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        
        ModelPartData body = modelPartData.addChild("body", 
            ModelPartBuilder.create()
                .uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -3.0F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
                .uv(44, 1).cuboid(3.0F, -14.0F, -3.0F, 4.0F, 4.0F, 6.0F, new Dilation(0.0F))
                .uv(49, 18).cuboid(7.0F, -12.0F, -1.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 10).cuboid(-7.0F, -9.0F, -2.0F, 6.0F, 3.0F, 4.0F, new Dilation(0.0F))
                .uv(27, 21).cuboid(5.0F, -16.0F, -3.0F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 26).cuboid(3.0F, -6.0F, -2.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 26).cuboid(3.0F, -6.0F, 1.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 26).cuboid(-6.0F, -6.0F, 1.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 26).cuboid(-6.0F, -6.0F, -2.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)),
            ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.body.yaw = netHeadYaw * ((float) Math.PI / 180F);
        this.body.pitch = headPitch * ((float) Math.PI / 180F);
    }

    @Override
    public void render(net.minecraft.client.util.math.MatrixStack matrices, net.minecraft.client.render.VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}
