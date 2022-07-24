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
public class IgnisColumnaModel extends EntityModel {
	private final ModelRenderer IgnisColumnaMain;
	private final ModelRenderer IgnisColumna;
	private final ModelRenderer IgnisColumnaG1;
	private final ModelRenderer Ignis4_r1;
	private final ModelRenderer Ignis3_r1;
	private final ModelRenderer IgnisColumnaG2;
	private final ModelRenderer Ignis4_r2;
	private final ModelRenderer Ignis3_r2;
	private final ModelRenderer IgnisColumnaG3;
	private final ModelRenderer Ignis4_r3;
	private final ModelRenderer Ignis3_r3;

	public IgnisColumnaModel() {
		texWidth = 512;
		texHeight = 512;

		IgnisColumnaMain = new ModelRenderer(this);
		IgnisColumnaMain.setPos(8.0F, 19.0F, -8.0F);
		

		IgnisColumna = new ModelRenderer(this);
		IgnisColumna.setPos(0.0F, 0.0F, 0.0F);
		IgnisColumnaMain.addChild(IgnisColumna);
		

		IgnisColumnaG1 = new ModelRenderer(this);
		IgnisColumnaG1.setPos(-15.0F, -11.0F, 15.0F);
		IgnisColumna.addChild(IgnisColumnaG1);
		IgnisColumnaG1.texOffs(196, 213).addBox(1.0F, -56.0F, -14.0F, 13.0F, 71.0F, 0.0F, 0.0F, false);
		IgnisColumnaG1.texOffs(183, 142).addBox(1.0F, -56.0F, -1.0F, 13.0F, 71.0F, 0.0F, 0.0F, false);

		Ignis4_r1 = new ModelRenderer(this);
		Ignis4_r1.setPos(1.0F, 0.0F, 0.0F);
		IgnisColumnaG1.addChild(Ignis4_r1);
		Ignis4_r1.texOffs(183, 0).addBox(-14.0F, -56.0F, 0.0F, 13.0F, 71.0F, 0.0F, 0.0F, false);

		Ignis3_r1 = new ModelRenderer(this);
		Ignis3_r1.setPos(14.0F, 0.0F, 0.0F);
		IgnisColumnaG1.addChild(Ignis3_r1);
		Ignis3_r1.texOffs(196, 71).addBox(-14.0F, -56.0F, 0.0F, 13.0F, 71.0F, 0.0F, 0.0F, false);

		IgnisColumnaG2 = new ModelRenderer(this);
		IgnisColumnaG2.setPos(-15.0F, -11.0F, 15.0F);
		IgnisColumna.addChild(IgnisColumnaG2);
		IgnisColumnaG2.texOffs(151, 163).addBox(0.0F, -148.0F, 0.0F, 15.0F, 163.0F, 0.0F, 0.0F, false);
		IgnisColumnaG2.texOffs(166, 0).addBox(0.0F, -148.0F, -15.0F, 15.0F, 163.0F, 0.0F, 0.0F, false);

		Ignis4_r2 = new ModelRenderer(this);
		Ignis4_r2.setPos(0.0F, 0.0F, 0.0F);
		IgnisColumnaG2.addChild(Ignis4_r2);
		Ignis4_r2.texOffs(121, 0).addBox(-15.0F, -148.0F, 0.0F, 15.0F, 163.0F, 0.0F, 0.0F, false);

		Ignis3_r2 = new ModelRenderer(this);
		Ignis3_r2.setPos(15.0F, 0.0F, 0.0F);
		IgnisColumnaG2.addChild(Ignis3_r2);
		Ignis3_r2.texOffs(136, 163).addBox(-15.0F, -148.0F, 0.0F, 15.0F, 163.0F, 0.0F, 0.0F, false);

		IgnisColumnaG3 = new ModelRenderer(this);
		IgnisColumnaG3.setPos(-15.0F, -11.0F, 15.0F);
		IgnisColumna.addChild(IgnisColumnaG3);
		IgnisColumnaG3.texOffs(102, 0).addBox(-1.0F, -225.0F, -16.0F, 17.0F, 240.0F, 0.0F, 0.0F, false);
		IgnisColumnaG3.texOffs(68, 0).addBox(-1.0F, -225.0F, 1.0F, 17.0F, 240.0F, 0.0F, 0.0F, false);

		Ignis4_r3 = new ModelRenderer(this);
		Ignis4_r3.setPos(-1.0F, 0.0F, 0.0F);
		IgnisColumnaG3.addChild(Ignis4_r3);
		Ignis4_r3.texOffs(0, 0).addBox(-16.0F, -225.0F, 0.0F, 17.0F, 240.0F, 0.0F, 0.0F, false);

		Ignis3_r3 = new ModelRenderer(this);
		Ignis3_r3.setPos(16.0F, 0.0F, 0.0F);
		IgnisColumnaG3.addChild(Ignis3_r3);
		Ignis3_r3.texOffs(34, 0).addBox(-16.0F, -225.0F, 0.0F, 17.0F, 240.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

	}


	@Override
	public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		IgnisColumnaMain.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);

	}
}