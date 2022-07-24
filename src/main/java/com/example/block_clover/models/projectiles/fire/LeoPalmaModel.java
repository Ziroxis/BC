package com.example.block_clover.models.projectiles.fire;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LeoPalmaModel extends EntityModel {
	private final ModelRenderer LeoPalmaMain;
	private final ModelRenderer LeoPalma;
	private final ModelRenderer LeoPalma8_r1;
	private final ModelRenderer LeoPalma7_r1;
	private final ModelRenderer LeoPalma6_r1;
	private final ModelRenderer LeoPalma5_r1;
	private final ModelRenderer LeoPalma4_r1;
	private final ModelRenderer LeoPalma3_r1;
	private final ModelRenderer LeoPalma2_r1;
	private final ModelRenderer LeoPalma1_r1;

	public LeoPalmaModel() {
		texWidth = 32;
		texHeight = 32;

		LeoPalmaMain = new ModelRenderer(this);
		LeoPalmaMain.setPos(1.0F, 39.0F, 0.0F);
		

		LeoPalma = new ModelRenderer(this);
		LeoPalma.setPos(0.0F, 0.0F, 0.0F);
		LeoPalmaMain.addChild(LeoPalma);
		

		LeoPalma8_r1 = new ModelRenderer(this);
		LeoPalma8_r1.setPos(3.0F, -14.0F, -3.0F);
		LeoPalma.addChild(LeoPalma8_r1);
		LeoPalma8_r1.texOffs(0, 0).addBox(-2.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		LeoPalma7_r1 = new ModelRenderer(this);
		LeoPalma7_r1.setPos(-3.0F, -14.0F, -4.0F);
		LeoPalma.addChild(LeoPalma7_r1);
		LeoPalma7_r1.texOffs(8, 0).addBox(-2.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		LeoPalma6_r1 = new ModelRenderer(this);
		LeoPalma6_r1.setPos(-3.0F, -14.0F, 3.0F);
		LeoPalma.addChild(LeoPalma6_r1);
		LeoPalma6_r1.texOffs(0, 10).addBox(-2.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		LeoPalma5_r1 = new ModelRenderer(this);
		LeoPalma5_r1.setPos(2.0F, -14.0F, 3.0F);
		LeoPalma.addChild(LeoPalma5_r1);
		LeoPalma5_r1.texOffs(8, 10).addBox(-2.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		LeoPalma4_r1 = new ModelRenderer(this);
		LeoPalma4_r1.setPos(3.0F, -14.0F, 0.0F);
		LeoPalma.addChild(LeoPalma4_r1);
		LeoPalma4_r1.texOffs(16, 0).addBox(-2.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		LeoPalma3_r1 = new ModelRenderer(this);
		LeoPalma3_r1.setPos(0.0F, -14.0F, 4.0F);
		LeoPalma.addChild(LeoPalma3_r1);
		LeoPalma3_r1.texOffs(16, 10).addBox(-2.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		LeoPalma2_r1 = new ModelRenderer(this);
		LeoPalma2_r1.setPos(-3.0F, -14.0F, 0.0F);
		LeoPalma.addChild(LeoPalma2_r1);
		LeoPalma2_r1.texOffs(0, 20).addBox(-2.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		LeoPalma1_r1 = new ModelRenderer(this);
		LeoPalma1_r1.setPos(0.0F, -14.0F, -3.0F);
		LeoPalma.addChild(LeoPalma1_r1);
		LeoPalma1_r1.texOffs(8, 20).addBox(-2.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

	}


	@Override
	public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		LeoPalmaMain.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);

	}
}