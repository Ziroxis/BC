package com.yuanno.block_clover.models.projectiles.fire;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.io.DataInput;

@OnlyIn(Dist.CLIENT)
public class SpiralFlameModel extends EntityModel {
	private final ModelRenderer SpiralFlameMain;
	private final ModelRenderer SpiralFlame;
	private final ModelRenderer SpiralFlameDetails;
	private final ModelRenderer SpiralG1;
	private final ModelRenderer SpiralG2;
	private final ModelRenderer SpiralG3;
	private final ModelRenderer SpiralG4;
	private final ModelRenderer SpiralG5;
	private final ModelRenderer SpiralG6;
	private final ModelRenderer SpiralG7;
	private final ModelRenderer SpiralG8;
	private final ModelRenderer SpiralG9;
	private final ModelRenderer SpiralG10;
	private final ModelRenderer SpiralG11;
	private final ModelRenderer SpiralG12;
	private final ModelRenderer SpiralG13;

	public SpiralFlameModel() {
		texWidth = 256;
		texHeight = 256;

		SpiralFlameMain = new ModelRenderer(this);
		SpiralFlameMain.setPos(7.0F, 41.0F, -55.0F);
		

		SpiralFlame = new ModelRenderer(this);
		SpiralFlame.setPos(-5.0F, 0.0F, 0.0F);
		SpiralFlameMain.addChild(SpiralFlame);
		SpiralFlame.texOffs(96, 4).addBox(-4.0F, -24.0F, -37.0F, 4.0F, 4.0F, 14.0F, 0.0F, false);
		SpiralFlame.texOffs(58, 92).addBox(-4.0F, -24.0F, -23.0F, 4.0F, 4.0F, 14.0F, 0.0F, false);
		SpiralFlame.texOffs(36, 88).addBox(-4.0F, -24.0F, -9.0F, 4.0F, 4.0F, 14.0F, 0.0F, false);
		SpiralFlame.texOffs(0, 88).addBox(-4.0F, -24.0F, 5.0F, 4.0F, 4.0F, 14.0F, 0.0F, false);
		SpiralFlame.texOffs(82, 60).addBox(-4.0F, -24.0F, 19.0F, 4.0F, 4.0F, 14.0F, 0.0F, false);
		SpiralFlame.texOffs(80, 78).addBox(-4.0F, -24.0F, 33.0F, 4.0F, 4.0F, 14.0F, 0.0F, false);
		SpiralFlame.texOffs(58, 74).addBox(-4.0F, -24.0F, 47.0F, 4.0F, 4.0F, 14.0F, 0.0F, false);
		SpiralFlame.texOffs(74, 36).addBox(-4.0F, -24.0F, 61.0F, 4.0F, 4.0F, 14.0F, 0.0F, false);
		SpiralFlame.texOffs(74, 18).addBox(-4.0F, -24.0F, 75.0F, 4.0F, 4.0F, 14.0F, 0.0F, false);
		SpiralFlame.texOffs(74, 0).addBox(-4.0F, -24.0F, 89.0F, 4.0F, 4.0F, 14.0F, 0.0F, false);
		SpiralFlame.texOffs(36, 70).addBox(-4.0F, -24.0F, 103.0F, 4.0F, 4.0F, 14.0F, 0.0F, false);
		SpiralFlame.texOffs(0, 70).addBox(-4.0F, -24.0F, 117.0F, 4.0F, 4.0F, 14.0F, 0.0F, false);
		SpiralFlame.texOffs(60, 56).addBox(-4.0F, -24.0F, 131.0F, 4.0F, 4.0F, 14.0F, 0.0F, false);

		SpiralFlameDetails = new ModelRenderer(this);
		SpiralFlameDetails.setPos(-5.0F, 0.0F, 0.0F);
		SpiralFlameMain.addChild(SpiralFlameDetails);
		

		SpiralG1 = new ModelRenderer(this);
		SpiralG1.setPos(-5.0F, -14.0F, 0.0F);
		SpiralFlameDetails.addChild(SpiralG1);
		SpiralG1.texOffs(56, 108).addBox(6.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG1.texOffs(84, 106).addBox(0.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG1.texOffs(60, 0).addBox(0.0F, -11.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);
		SpiralG1.texOffs(48, 56).addBox(0.0F, -5.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);

		SpiralG2 = new ModelRenderer(this);
		SpiralG2.setPos(-5.0F, -14.0F, 14.0F);
		SpiralFlameDetails.addChild(SpiralG2);
		SpiralG2.texOffs(104, 52).addBox(6.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG2.texOffs(104, 46).addBox(0.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG2.texOffs(36, 56).addBox(0.0F, -11.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);
		SpiralG2.texOffs(24, 56).addBox(0.0F, -5.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);

		SpiralG3 = new ModelRenderer(this);
		SpiralG3.setPos(-5.0F, -14.0F, 28.0F);
		SpiralFlameDetails.addChild(SpiralG3);
		SpiralG3.texOffs(28, 104).addBox(6.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG3.texOffs(0, 104).addBox(0.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG3.texOffs(12, 56).addBox(0.0F, -11.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);
		SpiralG3.texOffs(0, 56).addBox(0.0F, -5.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);

		SpiralG4 = new ModelRenderer(this);
		SpiralG4.setPos(-5.0F, -14.0F, 42.0F);
		SpiralFlameDetails.addChild(SpiralG4);
		SpiralG4.texOffs(102, 70).addBox(6.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG4.texOffs(102, 64).addBox(0.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG4.texOffs(48, 42).addBox(0.0F, -11.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);
		SpiralG4.texOffs(48, 28).addBox(0.0F, -5.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);

		SpiralG5 = new ModelRenderer(this);
		SpiralG5.setPos(-5.0F, -14.0F, 56.0F);
		SpiralFlameDetails.addChild(SpiralG5);
		SpiralG5.texOffs(56, 102).addBox(6.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG5.texOffs(84, 100).addBox(0.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG5.texOffs(48, 14).addBox(0.0F, -11.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);
		SpiralG5.texOffs(48, 0).addBox(0.0F, -5.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);

		SpiralG6 = new ModelRenderer(this);
		SpiralG6.setPos(-5.0F, -14.0F, 70.0F);
		SpiralFlameDetails.addChild(SpiralG6);
		SpiralG6.texOffs(28, 98).addBox(6.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG6.texOffs(0, 98).addBox(0.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG6.texOffs(36, 42).addBox(0.0F, -11.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);
		SpiralG6.texOffs(24, 42).addBox(0.0F, -5.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);

		SpiralG7 = new ModelRenderer(this);
		SpiralG7.setPos(-5.0F, -14.0F, 84.0F);
		SpiralFlameDetails.addChild(SpiralG7);
		SpiralG7.texOffs(56, 96).addBox(6.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG7.texOffs(96, 28).addBox(0.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG7.texOffs(12, 42).addBox(0.0F, -11.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);
		SpiralG7.texOffs(0, 42).addBox(0.0F, -5.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);

		SpiralG8 = new ModelRenderer(this);
		SpiralG8.setPos(-5.0F, -14.0F, 98.0F);
		SpiralFlameDetails.addChild(SpiralG8);
		SpiralG8.texOffs(96, 22).addBox(6.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG8.texOffs(96, 8).addBox(0.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG8.texOffs(36, 28).addBox(0.0F, -11.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);
		SpiralG8.texOffs(36, 14).addBox(0.0F, -5.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);

		SpiralG9 = new ModelRenderer(this);
		SpiralG9.setPos(-5.0F, -14.0F, 112.0F);
		SpiralFlameDetails.addChild(SpiralG9);
		SpiralG9.texOffs(94, 94).addBox(6.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG9.texOffs(94, 88).addBox(0.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG9.texOffs(36, 0).addBox(0.0F, -11.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);
		SpiralG9.texOffs(24, 28).addBox(0.0F, -5.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);

		SpiralG10 = new ModelRenderer(this);
		SpiralG10.setPos(-5.0F, -14.0F, 126.0F);
		SpiralFlameDetails.addChild(SpiralG10);
		SpiralG10.texOffs(28, 92).addBox(6.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG10.texOffs(0, 92).addBox(0.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG10.texOffs(12, 28).addBox(0.0F, -11.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);
		SpiralG10.texOffs(0, 28).addBox(0.0F, -5.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);

		SpiralG11 = new ModelRenderer(this);
		SpiralG11.setPos(-5.0F, -14.0F, 140.0F);
		SpiralFlameDetails.addChild(SpiralG11);
		SpiralG11.texOffs(80, 82).addBox(6.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG11.texOffs(82, 40).addBox(0.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG11.texOffs(24, 14).addBox(0.0F, -11.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);
		SpiralG11.texOffs(24, 0).addBox(0.0F, -5.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);

		SpiralG12 = new ModelRenderer(this);
		SpiralG12.setPos(-5.0F, -14.0F, 154.0F);
		SpiralFlameDetails.addChild(SpiralG12);
		SpiralG12.texOffs(22, 80).addBox(6.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG12.texOffs(22, 74).addBox(0.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG12.texOffs(12, 14).addBox(0.0F, -11.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);
		SpiralG12.texOffs(0, 14).addBox(0.0F, -5.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);

		SpiralG13 = new ModelRenderer(this);
		SpiralG13.setPos(-5.0F, -14.0F, 168.0F);
		SpiralFlameDetails.addChild(SpiralG13);
		SpiralG13.texOffs(22, 62).addBox(6.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG13.texOffs(22, 56).addBox(0.0F, -11.0F, -37.0F, 0.0F, 6.0F, 14.0F, 0.0F, false);
		SpiralG13.texOffs(12, 0).addBox(0.0F, -11.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);
		SpiralG13.texOffs(0, 0).addBox(0.0F, -5.0F, -37.0F, 6.0F, 0.0F, 14.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

	}


	@Override
	public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		SpiralFlameMain.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
	}
}