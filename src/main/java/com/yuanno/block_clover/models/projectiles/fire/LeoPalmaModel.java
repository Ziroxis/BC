package com.yuanno.block_clover.models.projectiles.fire;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LeoPalmaModel<T extends LivingEntity> extends BipedModel<T>{
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
		super(RenderType::entityTranslucent, 1, 0.0F, 64, 64);
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
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.crouching = entityIn.isCrouching();


		/*
		if(!(entityIn instanceof PlayerEntity))
			return;

		 */

		AbstractClientPlayerEntity clientPlayer = (AbstractClientPlayerEntity) entityIn;


		this.swimAmount = clientPlayer.getSwimAmount(ageInTicks);
		this.LeoPalma.copyFrom(this.body);


	}

	@Override
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		ImmutableList.of(this.LeoPalmaMain).forEach((modelRenderer) -> {
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}




}