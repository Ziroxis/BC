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
public class SolLineaModel extends EntityModel {
	private final ModelRenderer SolLineaMain;
	private final ModelRenderer SolLinea;
	private final ModelRenderer SolLineaDetails;
	private final ModelRenderer SolLineaD1_r1;

	public SolLineaModel() {
		texWidth = 64;
		texHeight = 64;

		SolLineaMain = new ModelRenderer(this);
		SolLineaMain.setPos(2.0F, 24.0F, 0.0F);
		

		SolLinea = new ModelRenderer(this);
		SolLinea.setPos(0.0F, 0.0F, 0.0F);
		SolLineaMain.addChild(SolLinea);
		SolLinea.texOffs(16, 0).addBox(-2.5F, -16.5F, -2.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		SolLinea.texOffs(24, 6).addBox(-4.5F, -16.5F, -2.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		SolLinea.texOffs(28, 0).addBox(-3.5F, -16.5F, -3.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		SolLinea.texOffs(32, 12).addBox(-3.5F, -16.5F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		SolLinea.texOffs(0, 8).addBox(-3.5F, -16.5F, -2.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		SolLinea.texOffs(12, 8).addBox(-3.5F, -17.5F, -2.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		SolLinea.texOffs(0, 0).addBox(-4.0F, -17.0F, -3.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		SolLineaDetails = new ModelRenderer(this);
		SolLineaDetails.setPos(0.0F, 0.0F, 0.0F);
		SolLineaMain.addChild(SolLineaDetails);
		

		SolLineaD1_r1 = new ModelRenderer(this);
		SolLineaD1_r1.setPos(0.0F, -13.0F, -3.0F);
		SolLineaDetails.addChild(SolLineaD1_r1);
		SolLineaD1_r1.texOffs(0, 0).addBox(-2.0F, -10.0F, -10.0F, 0.0F, 16.0F, 16.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

	}


	@Override
	public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		SolLineaMain.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);

	}
}