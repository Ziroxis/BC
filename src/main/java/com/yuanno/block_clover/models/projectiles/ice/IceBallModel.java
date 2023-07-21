package com.yuanno.block_clover.models.projectiles.ice;// Made with Blockbench 4.2.4
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
public class IceBallModel extends EntityModel {
	private final ModelRenderer bone;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;

	public IceBallModel() {
		texWidth = 256;
		texHeight = 256;

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 24.0F, 0.0F);
		bone.texOffs(0, 116).addBox(-4.0F, -35.0F, -15.0F, 8.0F, 30.0F, 30.0F, 0.0F, false);
		bone.texOffs(0, 0).addBox(-14.0F, -34.0F, -14.0F, 28.0F, 28.0F, 28.0F, 0.0F, false);
		bone.texOffs(152, 60).addBox(14.1F, -30.0F, -10.0F, 4.0F, 20.0F, 20.0F, 0.0F, false);
		bone.texOffs(84, 0).addBox(18.1F, -26.0F, -5.0F, 2.0F, 10.0F, 10.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(3.0F, -4.0F, 0.0F);
		bone.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 1.5708F, 0.0F);
		cube_r1.texOffs(122, 40).addBox(14.1F, -26.0F, -13.0F, 4.0F, 20.0F, 20.0F, 0.0F, false);
		cube_r1.texOffs(46, 56).addBox(18.1F, -22.0F, -8.0F, 2.0F, 10.0F, 10.0F, 0.0F, false);
		cube_r1.texOffs(76, 56).addBox(-4.0F, -31.0F, -18.0F, 8.0F, 30.0F, 30.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(3.0F, -4.0F, 0.0F);
		bone.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 1.5708F);
		cube_r2.texOffs(0, 0).addBox(2.1F, -2.0F, -5.0F, 2.0F, 10.0F, 10.0F, 0.0F, false);
		cube_r2.texOffs(112, 0).addBox(-1.9F, -7.0F, -10.0F, 4.0F, 20.0F, 20.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(3.0F, -4.0F, 0.0F);
		bone.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -1.5708F);
		cube_r3.texOffs(76, 116).addBox(30.1F, -13.0F, -10.0F, 4.0F, 20.0F, 20.0F, 0.0F, false);
		cube_r3.texOffs(0, 56).addBox(34.1F, -8.0F, -5.0F, 2.0F, 10.0F, 10.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(3.0F, -4.0F, 0.0F);
		bone.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, -1.5708F, 0.0F);
		cube_r4.texOffs(124, 116).addBox(14.1F, -26.0F, -7.0F, 4.0F, 20.0F, 20.0F, 0.0F, false);
		cube_r4.texOffs(60, 66).addBox(18.1F, -22.0F, -2.0F, 2.0F, 10.0F, 10.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(3.0F, -4.0F, 0.0F);
		bone.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 3.1416F, 0.0F);
		cube_r5.texOffs(74, 56).addBox(21.1F, -22.0F, -5.0F, 2.0F, 10.0F, 10.0F, 0.0F, false);
		cube_r5.texOffs(150, 20).addBox(17.1F, -26.0F, -10.0F, 4.0F, 20.0F, 20.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(0.0F, -20.0F, 0.0F);
		bone.addChild(cube_r6);
		setRotationAngle(cube_r6, 1.5708F, 0.0F, 1.5708F);
		cube_r6.texOffs(0, 56).addBox(-4.0F, -15.0F, -15.0F, 8.0F, 30.0F, 30.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

	}


	@Override
	public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		bone.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);

	}


	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}