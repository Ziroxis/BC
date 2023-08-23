package com.yuanno.block_clover.models.entities.beastial;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.block_clover.entities.beastial.CloverSharkEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CloverSharkModel<T extends CloverSharkEntity> extends EntityModel<T> {
	private final ModelRenderer Black_Clover_Shark;
	private final ModelRenderer Back_Fins;
	private final ModelRenderer cube_r1;
	private final ModelRenderer Head;
	private final ModelRenderer Fin_Left_Big;
	private final ModelRenderer Fin_Right_Big;
	private final ModelRenderer Tail;
	private final ModelRenderer Base_Body;

	public CloverSharkModel() {
		texWidth = 128;
		texHeight = 128;

		Black_Clover_Shark = new ModelRenderer(this);
		Black_Clover_Shark.setPos(-1.0F, 17.0F, 5.0F);


		Back_Fins = new ModelRenderer(this);
		Back_Fins.setPos(0.0F, -7.0F, 3.0F);
		Black_Clover_Shark.addChild(Back_Fins);


		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(1.0F, 14.0F, -8.0F);
		Back_Fins.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.3491F, 0.0F, 0.0F);
		cube_r1.texOffs(55, 0).addBox(-3.0F, -20.0F, -4.0F, 5.0F, 7.0F, 13.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setPos(1.0F, 0.0F, 12.0F);
		Black_Clover_Shark.addChild(Head);
		Head.texOffs(44, 39).addBox(-7.0F, -6.0F, -1.0F, 13.0F, 12.0F, 7.0F, 0.0F, false);
		Head.texOffs(58, 58).addBox(-6.0F, 1.0F, 5.0F, 11.0F, 4.0F, 2.0F, 0.0F, false);

		Fin_Left_Big = new ModelRenderer(this);
		Fin_Left_Big.setPos(-7.0F, 4.0F, 2.0F);
		Black_Clover_Shark.addChild(Fin_Left_Big);
		Fin_Left_Big.texOffs(34, 58).addBox(-7.0F, -3.0F, -6.0F, 7.0F, 6.0F, 10.0F, 0.0F, false);

		Fin_Right_Big = new ModelRenderer(this);
		Fin_Right_Big.setPos(8.0F, 6.0F, 1.0F);
		Black_Clover_Shark.addChild(Fin_Right_Big);
		Fin_Right_Big.texOffs(0, 59).addBox(0.0F, -5.0F, -5.0F, 7.0F, 6.0F, 10.0F, 0.0F, false);

		Tail = new ModelRenderer(this);
		Tail.setPos(1.0F, -1.0F, -14.0F);
		Black_Clover_Shark.addChild(Tail);
		setRotationAngle(Tail, 0.2618F, 0.0F, 0.0F);
		Tail.texOffs(0, 39).addBox(-5.0F, -3.587F, -11.0544F, 9.0F, 7.0F, 13.0F, 0.0F, false);
		Tail.texOffs(0, 0).addBox(-3.0F, -2.587F, -13.0544F, 5.0F, 4.0F, 2.0F, 0.0F, false);

		Base_Body = new ModelRenderer(this);
		Base_Body.setPos(0.0F, 1.0F, -5.0F);
		Black_Clover_Shark.addChild(Base_Body);
		Base_Body.texOffs(0, 0).addBox(-7.0F, -8.0F, -8.0F, 15.0F, 14.0F, 25.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		Black_Clover_Shark.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}