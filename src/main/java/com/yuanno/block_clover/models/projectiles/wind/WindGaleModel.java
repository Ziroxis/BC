package com.yuanno.block_clover.models.projectiles.wind;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WindGaleModel extends EntityModel {

    private final ModelRenderer Gale_Bow;

    public WindGaleModel() {
        texWidth = 64;
        texHeight = 64;

        Gale_Bow = new ModelRenderer(this);
        Gale_Bow.setPos(0.0F, 24.0F, 0.0F);
        Gale_Bow.texOffs(0, 0).addBox(-1.0F, -11.0F, -7.0F, 2.0F, 2.0F, 22.0F, 0.0F, false);
        Gale_Bow.texOffs(14, 10).addBox(1.0F, -10.0F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        Gale_Bow.texOffs(8, 12).addBox(2.0F, -9.0F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        Gale_Bow.texOffs(8, 8).addBox(1.0F, -9.0F, -4.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        Gale_Bow.texOffs(0, 8).addBox(-3.0F, -9.0F, -4.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        Gale_Bow.texOffs(0, 12).addBox(-3.0F, -10.0F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        Gale_Bow.texOffs(11, 0).addBox(-4.0F, -9.0F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        Gale_Bow.texOffs(0, 0).addBox(-1.0F, -12.0F, -6.0F, 2.0F, 1.0F, 7.0F, 0.0F, false);
        Gale_Bow.texOffs(11, 4).addBox(-1.0F, -9.0F, -8.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                               float alpha) {
        Gale_Bow.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
