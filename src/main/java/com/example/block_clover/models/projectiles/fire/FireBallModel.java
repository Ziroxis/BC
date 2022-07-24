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
public class FireBallModel extends EntityModel {
	private final ModelRenderer ExplodingFireBallMain;
	private final ModelRenderer ExplodingFireBall;
	private final ModelRenderer ExplodingFireBallDetails;
	private final ModelRenderer FireballD4_r1;
	private final ModelRenderer FireballD3_r1;

	public FireBallModel() {
		texWidth = 64;
		texHeight = 64;

		ExplodingFireBallMain = new ModelRenderer(this);
		ExplodingFireBallMain.setPos(0.0F, 15.0F, 0.0F);
		setRotationAngle(ExplodingFireBallMain, 0.0F, 1.5708F, 0.0F);


		ExplodingFireBall = new ModelRenderer(this);
		ExplodingFireBall.setPos(2.0F, 21.0F, 1.0F);
		ExplodingFireBallMain.addChild(ExplodingFireBall);
		ExplodingFireBall.texOffs(0, 12).addBox(-4.0F, -17.0F, -3.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		ExplodingFireBall.texOffs(16, 16).addBox(-3.5F, -17.5F, -2.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		ExplodingFireBall.texOffs(16, 9).addBox(-3.5F, -16.5F, -2.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		ExplodingFireBall.texOffs(21, 23).addBox(-3.5F, -16.5F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		ExplodingFireBall.texOffs(9, 23).addBox(-3.5F, -16.5F, -3.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		ExplodingFireBall.texOffs(0, 20).addBox(-4.5F, -16.5F, -2.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		ExplodingFireBall.texOffs(19, 0).addBox(-2.5F, -16.5F, -2.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		ExplodingFireBallDetails = new ModelRenderer(this);
		ExplodingFireBallDetails.setPos(2.0F, 21.0F, 1.0F);
		ExplodingFireBallMain.addChild(ExplodingFireBallDetails);
		ExplodingFireBallDetails.texOffs(0, 9).addBox(-7.0F, -17.5F, -2.5F, 5.0F, 0.0F, 3.0F, 0.0F, false);
		ExplodingFireBallDetails.texOffs(0, 6).addBox(-7.0F, -12.5F, -2.5F, 5.0F, 0.0F, 3.0F, 0.0F, false);

		FireballD4_r1 = new ModelRenderer(this);
		FireballD4_r1.setPos(-2.0F, -15.0F, 1.0F);
		ExplodingFireBallDetails.addChild(FireballD4_r1);
		setRotationAngle(FireballD4_r1, -1.5708F, 0.0F, 0.0F);
		FireballD4_r1.texOffs(0, 0).addBox(-5.0F, -0.5F, -1.5F, 5.0F, 0.0F, 3.0F, 0.0F, false);

		FireballD3_r1 = new ModelRenderer(this);
		FireballD3_r1.setPos(-2.0F, -15.0F, -5.0F);
		ExplodingFireBallDetails.addChild(FireballD3_r1);
		setRotationAngle(FireballD3_r1, -1.5708F, 0.0F, 0.0F);
		FireballD3_r1.texOffs(0, 3).addBox(-5.0F, -1.5F, -1.5F, 5.0F, 0.0F, 3.0F, 0.0F, false);
	}
	
	@Override
	public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

	}


	@Override
	public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		ExplodingFireBallMain.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);

	}


	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}