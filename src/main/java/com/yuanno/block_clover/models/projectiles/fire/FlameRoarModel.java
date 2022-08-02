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

@OnlyIn(Dist.CLIENT)
public class FlameRoarModel extends EntityModel {
	private final ModelRenderer FlameRoarMain;
	private final ModelRenderer FlameRoarHead;
	private final ModelRenderer Head5_r1;
	private final ModelRenderer Head4_r1;
	private final ModelRenderer UpperSnout;
	private final ModelRenderer TopTeeth;
	private final ModelRenderer Tooth13_r1;
	private final ModelRenderer LowerSnout;
	private final ModelRenderer BottomTeeth;
	private final ModelRenderer Tooth13_r2;
	private final ModelRenderer Tooth10_r1;
	private final ModelRenderer FlameRoarBlast;

	public FlameRoarModel() {
		texWidth = 256;
		texHeight = 256;

		FlameRoarMain = new ModelRenderer(this);
		FlameRoarMain.setPos(-1.0F, 1.0F, -13.0F);
		setRotationAngle(FlameRoarMain, 0.0F, 1.5708F, 0.0F);


		FlameRoarHead = new ModelRenderer(this);
		FlameRoarHead.setPos(-13.0F, 26.0F, 9.0F);
		FlameRoarMain.addChild(FlameRoarHead);
		FlameRoarHead.texOffs(128, 53).addBox(-13.0F, -49.0F, -23.0F, 18.0F, 38.0F, 29.0F, 0.0F, false);
		FlameRoarHead.texOffs(0, 82).addBox(-29.0F, -51.0F, -25.0F, 31.0F, 43.0F, 33.0F, 0.0F, false);
		FlameRoarHead.texOffs(0, 0).addBox(-33.0F, -52.0F, -27.0F, 30.0F, 45.0F, 37.0F, 0.0F, false);

		Head5_r1 = new ModelRenderer(this);
		Head5_r1.setPos(-19.0F, -29.0F, 8.0F);
		FlameRoarHead.addChild(Head5_r1);
		setRotationAngle(Head5_r1, -0.3491F, 0.0F, 0.0F);
		Head5_r1.texOffs(0, 0).addBox(-3.0F, -26.0F, -12.0F, 8.0F, 15.0F, 9.0F, 0.0F, false);

		Head4_r1 = new ModelRenderer(this);
		Head4_r1.setPos(-19.0F, -34.0F, -11.0F);
		FlameRoarHead.addChild(Head4_r1);
		setRotationAngle(Head4_r1, 0.3491F, 0.0F, 0.0F);
		Head4_r1.texOffs(112, 171).addBox(-3.0F, -26.0F, -12.0F, 8.0F, 15.0F, 9.0F, 0.0F, false);

		UpperSnout = new ModelRenderer(this);
		UpperSnout.setPos(599.0F, -32.0F, -9.0F);
		FlameRoarHead.addChild(UpperSnout);
		setRotationAngle(UpperSnout, 0.0F, 0.0F, -0.0436F);
		UpperSnout.texOffs(97, 0).addBox(-597.4089F, -39.0876F, -10.0F, 22.0F, 12.0F, 21.0F, 0.0F, false);
		UpperSnout.texOffs(0, 158).addBox(-584.4089F, -38.0876F, -9.0F, 10.0F, 11.0F, 19.0F, 0.0F, false);
		UpperSnout.texOffs(164, 156).addBox(-579.4089F, -40.0876F, -7.0F, 6.0F, 8.0F, 15.0F, 0.0F, false);

		TopTeeth = new ModelRenderer(this);
		TopTeeth.setPos(-3.0F, 23.0F, 0.0F);
		UpperSnout.addChild(TopTeeth);
		setRotationAngle(TopTeeth, 0.0F, 0.0F, -0.3927F);


		Tooth13_r1 = new ModelRenderer(this);
		Tooth13_r1.setPos(71.0F, -21.0F, 11.0F);
		TopTeeth.addChild(Tooth13_r1);
		setRotationAngle(Tooth13_r1, 0.0F, 0.0F, 0.3927F);
		Tooth13_r1.texOffs(95, 90).addBox(-637.6691F, -4.8896F, -18.5F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		Tooth13_r1.texOffs(97, 0).addBox(-640.6691F, -4.8896F, -18.5F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		Tooth13_r1.texOffs(97, 7).addBox(-643.6691F, -4.8896F, -18.5F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		Tooth13_r1.texOffs(97, 14).addBox(-646.6691F, -4.8896F, -18.5F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		Tooth13_r1.texOffs(95, 97).addBox(-646.6691F, -4.8896F, -4.5F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		Tooth13_r1.texOffs(0, 98).addBox(-643.6691F, -4.8896F, -4.5F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		Tooth13_r1.texOffs(8, 98).addBox(-640.6691F, -4.8896F, -4.5F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		Tooth13_r1.texOffs(16, 98).addBox(-637.6691F, -4.8896F, -4.5F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		Tooth13_r1.texOffs(24, 98).addBox(-632.6691F, -4.8896F, -8.5F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		Tooth13_r1.texOffs(101, 102).addBox(-632.6691F, -4.8896F, -14.5F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		Tooth13_r1.texOffs(103, 82).addBox(-631.6691F, -4.8896F, -11.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Tooth13_r1.texOffs(0, 24).addBox(-634.6691F, -4.8896F, -17.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
		Tooth13_r1.texOffs(8, 24).addBox(-634.6691F, -4.8896F, -6.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		LowerSnout = new ModelRenderer(this);
		LowerSnout.setPos(599.0F, -22.0F, -9.0F);
		FlameRoarHead.addChild(LowerSnout);
		setRotationAngle(LowerSnout, 0.0F, 0.0F, 0.3927F);
		LowerSnout.texOffs(128, 120).addBox(-549.7292F, 227.6464F, -8.0F, 19.0F, 10.0F, 17.0F, 0.0F, false);
		LowerSnout.texOffs(113, 147).addBox(-547.7292F, 227.6464F, -7.0F, 18.0F, 9.0F, 15.0F, 0.0F, false);

		BottomTeeth = new ModelRenderer(this);
		BottomTeeth.setPos(-1.0F, -6.0F, 0.0F);
		LowerSnout.addChild(BottomTeeth);
		setRotationAngle(BottomTeeth, 0.0F, 0.0F, -0.3927F);


		Tooth13_r2 = new ModelRenderer(this);
		Tooth13_r2.setPos(63.0F, -11.0F, 11.0F);
		BottomTeeth.addChild(Tooth13_r2);
		setRotationAngle(Tooth13_r2, 0.0F, 0.0F, 0.3927F);
		Tooth13_r2.texOffs(25, 0).addBox(-594.2292F, 265.8444F, -17.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth13_r2.texOffs(0, 82).addBox(-597.2292F, 265.8444F, -17.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth13_r2.texOffs(24, 24).addBox(-591.2292F, 265.8444F, -17.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth13_r2.texOffs(8, 82).addBox(-597.2292F, 265.8444F, -5.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth13_r2.texOffs(16, 82).addBox(-594.2292F, 265.8444F, -5.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth13_r2.texOffs(24, 82).addBox(-591.2292F, 265.8444F, -5.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth13_r2.texOffs(0, 90).addBox(-586.2292F, 264.8444F, -8.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth13_r2.texOffs(8, 90).addBox(-585.7292F, 266.8444F, -11.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth13_r2.texOffs(16, 90).addBox(-586.2292F, 264.8444F, -14.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth13_r2.texOffs(24, 90).addBox(-587.9894F, 262.8444F, -16.8F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		Tooth13_r2.texOffs(95, 82).addBox(-587.9894F, 262.8444F, -6.2F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		Tooth10_r1 = new ModelRenderer(this);
		Tooth10_r1.setPos(60.0F, -11.0F, 11.0F);
		BottomTeeth.addChild(Tooth10_r1);
		setRotationAngle(Tooth10_r1, 0.0F, 0.0F, 0.3927F);
		Tooth10_r1.texOffs(16, 24).addBox(-597.2292F, 264.8444F, -17.5F, 2.0F, 7.0F, 2.0F, 0.0F, false);
		Tooth10_r1.texOffs(0, 0).addBox(-597.2292F, 264.8444F, -5.5F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		FlameRoarBlast = new ModelRenderer(this);
		FlameRoarBlast.setPos(-13.0F, 26.0F, 9.0F);
		FlameRoarMain.addChild(FlameRoarBlast);
		FlameRoarBlast.texOffs(58, 158).addBox(3.0F, -35.0F, -14.0F, 16.0F, 21.0F, 11.0F, 0.0F, false);
	}


	@Override
	public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

	}


	@Override
	public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		FlameRoarMain.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);

	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}