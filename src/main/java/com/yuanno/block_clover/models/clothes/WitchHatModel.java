package com.yuanno.block_clover.models.clothes;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitchHatModel<T extends LivingEntity> extends BipedModel<T> {

    private final ModelRenderer hat;
    private final ModelRenderer hat2;
    private final ModelRenderer hat3;
    private final ModelRenderer hat4;

    private final ModelRenderer Head;

    private final EquipmentSlotType slot;

    public WitchHatModel(EquipmentSlotType slot) {

        super(1);
        texWidth = 64;
        texHeight = 128;

        this.slot = slot;

        Head = new ModelRenderer(this);
        Head.setPos(0.0F, 0.0F, 0.0F);

        hat = new ModelRenderer(this);
        hat.setPos(-5.0F, -8.0313F, -5.0F);
        Head.addChild(hat);
        hat.texOffs(0, 64).addBox(0.0F, -2.0187F, 0.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);

        hat2 = new ModelRenderer(this);
        hat2.setPos(6.75F, 0.0313F, 7.0F);
        hat.addChild(hat2);
        setRotationAngle(hat2, -0.0524F, 0.0F, 0.0262F);
        hat2.texOffs(0, 76).addBox(-5.0F, -5.5F, -5.0F, 7.0F, 4.0F, 7.0F, 0.0F, false);

        hat3 = new ModelRenderer(this);
        hat3.setPos(0.0F, -3.0F, 0.0F);
        hat2.addChild(hat3);
        setRotationAngle(hat3, -0.1047F, 0.0F, 0.0524F);
        hat3.texOffs(0, 87).addBox(-3.25F, -5.5F, -3.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        hat4 = new ModelRenderer(this);
        hat4.setPos(0.0F, -3.0F, 0.0F);
        hat3.addChild(hat4);
        setRotationAngle(hat4, -0.2094F, 0.0F, 0.1047F);
        hat4.texOffs(0, 95).addBox(-1.5F, -4.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.25F, false);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        this.Head.copyFrom(this.head);
        Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
