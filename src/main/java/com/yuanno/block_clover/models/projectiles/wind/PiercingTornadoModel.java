package com.yuanno.block_clover.models.projectiles.wind;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class PiercingTornadoModel extends EntityModel {

    private final ModelRenderer Tornado;
    private final ModelRenderer Blunt;
    private final ModelRenderer third_part;
    private final ModelRenderer fourth_part;
    private final ModelRenderer second_part;
    private final ModelRenderer First_part;

    public PiercingTornadoModel() {
        texWidth = 64;
        texHeight = 64;

        Tornado = new ModelRenderer(this);
        Tornado.setPos(0.0F, 24.0F, -1.0F);


        Blunt = new ModelRenderer(this);
        Blunt.setPos(0.0F, 0.0F, 0.0F);
        Tornado.addChild(Blunt);
        Blunt.texOffs(0, 20).addBox(-7.0F, -17.0F, -6.0F, 12.0F, 7.0F, 1.0F, 0.0F, false);
        Blunt.texOffs(28, 5).addBox(-5.0F, -15.0F, -7.0F, 8.0F, 3.0F, 1.0F, 0.0F, false);
        Blunt.texOffs(5, 28).addBox(-2.0F, -14.0F, -8.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        third_part = new ModelRenderer(this);
        third_part.setPos(0.0F, 0.0F, 3.0F);
        Tornado.addChild(third_part);
        third_part.texOffs(24, 29).addBox(5.0F, -19.0F, -5.0F, 1.0F, 12.0F, 3.0F, 0.0F, false);
        third_part.texOffs(0, 5).addBox(-7.0F, -9.0F, -5.0F, 13.0F, 1.0F, 4.0F, 0.0F, false);
        third_part.texOffs(26, 20).addBox(-7.0F, -19.0F, -5.0F, 12.0F, 1.0F, 3.0F, 0.0F, false);
        third_part.texOffs(16, 28).addBox(-8.0F, -19.0F, -5.0F, 1.0F, 12.0F, 3.0F, 0.0F, false);

        fourth_part = new ModelRenderer(this);
        fourth_part.setPos(0.0F, 0.0F, 0.0F);
        Tornado.addChild(fourth_part);
        fourth_part.texOffs(40, 6).addBox(5.0F, -18.0F, -5.0F, 0.0F, 11.0F, 3.0F, 0.0F, false);
        fourth_part.texOffs(0, 0).addBox(-7.0F, -10.0F, -5.0F, 12.0F, 1.0F, 4.0F, 0.0F, false);
        fourth_part.texOffs(23, 25).addBox(-7.0F, -18.0F, -5.0F, 12.0F, 1.0F, 3.0F, 0.0F, false);
        fourth_part.texOffs(34, 6).addBox(-7.0F, -18.0F, -5.0F, 0.0F, 11.0F, 3.0F, 0.0F, false);

        second_part = new ModelRenderer(this);
        second_part.setPos(0.0F, 0.0F, 5.0F);
        Tornado.addChild(second_part);
        second_part.texOffs(8, 28).addBox(6.0F, -20.0F, -4.0F, 1.0F, 13.0F, 3.0F, 0.0F, false);
        second_part.texOffs(28, 0).addBox(-7.0F, -8.0F, -3.0F, 13.0F, 1.0F, 2.0F, 0.0F, false);
        second_part.texOffs(0, 16).addBox(-7.0F, -20.0F, -4.0F, 13.0F, 1.0F, 3.0F, 0.0F, false);
        second_part.texOffs(0, 28).addBox(-8.0F, -20.0F, -4.0F, 1.0F, 13.0F, 3.0F, 0.0F, false);

        First_part = new ModelRenderer(this);
        First_part.setPos(0.0F, 0.0F, 5.0F);
        Tornado.addChild(First_part);
        First_part.texOffs(32, 29).addBox(7.0F, -20.0F, -1.0F, 1.0F, 13.0F, 2.0F, 0.0F, false);
        First_part.texOffs(38, 29).addBox(-9.0F, -20.0F, -1.0F, 1.0F, 13.0F, 2.0F, 0.0F, false);
        First_part.texOffs(0, 10).addBox(-8.0F, -21.0F, -1.0F, 15.0F, 1.0F, 2.0F, 0.0F, false);
        First_part.texOffs(0, 13).addBox(-8.0F, -7.0F, -1.0F, 15.0F, 1.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                               float alpha) {
        Tornado.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
