package com.yuanno.block_clover.models.projectiles.water;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WaterDomeModel extends EntityModel {
	private final ModelRenderer WaterDomeMain;
	private final ModelRenderer WaterDome1;
	private final ModelRenderer WaterDome2;
	private final ModelRenderer WaterDome3;
	private final ModelRenderer WaterDome4;
	private final ModelRenderer WaterDome5;
	private final ModelRenderer WaterDome6;
	private final ModelRenderer WaterDome7;
	private final ModelRenderer WaterDome8;
	private final ModelRenderer WaterDome9;
	private final ModelRenderer WaterDome10;

	public WaterDomeModel() {
		texWidth = 128;
		texHeight = 128;

		WaterDomeMain = new ModelRenderer(this);
		WaterDomeMain.setPos(1.0F, 28.0F, -17.0F);
		

		WaterDome1 = new ModelRenderer(this);
		WaterDome1.setPos(0.0F, 0.0F, 0.0F);
		WaterDomeMain.addChild(WaterDome1);
		WaterDome1.texOffs(0, 4).addBox(-10.0F, -5.0F, -2.0F, 18.0F, 1.0F, 3.0F, 0.0F, false);
		WaterDome1.texOffs(0, 36).addBox(-10.0F, -9.0F, 1.0F, 18.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome1.texOffs(0, 32).addBox(-10.0F, -9.0F, 0.0F, 18.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome1.texOffs(0, 28).addBox(-10.0F, -9.0F, -1.0F, 18.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome1.texOffs(0, 24).addBox(-10.0F, -9.0F, -2.0F, 18.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome1.texOffs(47, 62).addBox(-3.0F, -6.0F, -2.0F, 11.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome1.texOffs(48, 52).addBox(-9.0F, -6.0F, -2.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome1.texOffs(23, 62).addBox(-7.0F, -6.0F, -1.0F, 11.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome1.texOffs(0, 61).addBox(-9.0F, -6.0F, 0.0F, 11.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome1.texOffs(47, 60).addBox(-6.0F, -6.0F, 0.0F, 11.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome1.texOffs(21, 66).addBox(-4.0F, -6.0F, -1.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome1.texOffs(60, 38).addBox(-3.0F, -6.0F, 0.0F, 11.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome1.texOffs(60, 36).addBox(-10.0F, -6.0F, -1.0F, 11.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome1.texOffs(66, 18).addBox(-5.0F, -6.0F, -2.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome1.texOffs(66, 14).addBox(-2.0F, -6.0F, -1.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome1.texOffs(66, 10).addBox(-6.0F, -7.0F, -1.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome1.texOffs(66, 6).addBox(-10.0F, -7.0F, -1.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome1.texOffs(66, 4).addBox(-8.0F, -7.0F, -2.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome1.texOffs(66, 2).addBox(-8.0F, -7.0F, 0.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome1.texOffs(66, 0).addBox(-6.0F, -7.0F, 0.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome1.texOffs(64, 65).addBox(-2.0F, -7.0F, 0.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);

		WaterDome2 = new ModelRenderer(this);
		WaterDome2.setPos(15.0F, 0.0F, 3.0F);
		WaterDomeMain.addChild(WaterDome2);
		WaterDome2.texOffs(60, 32).addBox(-10.0F, -5.0F, -2.0F, 8.0F, 1.0F, 3.0F, 0.0F, false);
		WaterDome2.texOffs(0, 75).addBox(-10.0F, -9.0F, 1.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome2.texOffs(74, 44).addBox(-10.0F, -9.0F, 0.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome2.texOffs(32, 74).addBox(-10.0F, -9.0F, -1.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome2.texOffs(72, 48).addBox(-10.0F, -9.0F, -2.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome2.texOffs(88, 14).addBox(-3.0F, -6.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome2.texOffs(86, 68).addBox(-9.0F, -6.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome2.texOffs(11, 88).addBox(-7.0F, -6.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome2.texOffs(88, 10).addBox(-9.0F, -6.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome2.texOffs(7, 88).addBox(-6.0F, -6.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome2.texOffs(37, 55).addBox(-4.0F, -6.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome2.texOffs(87, 82).addBox(-3.0F, -6.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome2.texOffs(87, 76).addBox(-10.0F, -6.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome2.texOffs(35, 55).addBox(-5.0F, -6.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome2.texOffs(33, 55).addBox(-2.0F, -6.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome2.texOffs(31, 55).addBox(-6.0F, -7.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome2.texOffs(29, 55).addBox(-10.0F, -7.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome2.texOffs(27, 55).addBox(-8.0F, -7.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome2.texOffs(25, 55).addBox(-8.0F, -7.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome2.texOffs(0, 55).addBox(-6.0F, -7.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome2.texOffs(0, 54).addBox(-2.0F, -7.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		WaterDome3 = new ModelRenderer(this);
		WaterDome3.setPos(-23.0F, 0.0F, 0.0F);
		WaterDomeMain.addChild(WaterDome3);
		WaterDome3.texOffs(60, 28).addBox(8.0F, -5.0F, 1.0F, 8.0F, 1.0F, 3.0F, 0.0F, false);
		WaterDome3.texOffs(72, 40).addBox(8.0F, -9.0F, 4.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome3.texOffs(16, 72).addBox(8.0F, -9.0F, 3.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome3.texOffs(64, 71).addBox(8.0F, -9.0F, 2.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome3.texOffs(71, 60).addBox(8.0F, -9.0F, 1.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome3.texOffs(87, 62).addBox(15.0F, -6.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome3.texOffs(67, 86).addBox(9.0F, -6.0F, 1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome3.texOffs(87, 60).addBox(11.0F, -6.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome3.texOffs(87, 51).addBox(9.0F, -6.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome3.texOffs(44, 87).addBox(12.0F, -6.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome3.texOffs(21, 53).addBox(14.0F, -6.0F, 2.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome3.texOffs(40, 87).addBox(15.0F, -6.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome3.texOffs(36, 87).addBox(8.0F, -6.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome3.texOffs(19, 53).addBox(13.0F, -6.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome3.texOffs(0, 53).addBox(16.0F, -6.0F, 2.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome3.texOffs(24, 46).addBox(12.0F, -7.0F, 2.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome3.texOffs(24, 45).addBox(8.0F, -7.0F, 2.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome3.texOffs(39, 5).addBox(10.0F, -7.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome3.texOffs(39, 4).addBox(10.0F, -7.0F, 3.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome3.texOffs(39, 3).addBox(12.0F, -7.0F, 3.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome3.texOffs(39, 1).addBox(16.0F, -7.0F, 3.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		WaterDome4 = new ModelRenderer(this);
		WaterDome4.setPos(-14.0F, 0.0F, 19.0F);
		WaterDomeMain.addChild(WaterDome4);
		setRotationAngle(WaterDome4, 0.0F, -1.5708F, 0.0F);
		WaterDome4.texOffs(36, 20).addBox(-15.0F, -5.0F, 1.0F, 12.0F, 1.0F, 3.0F, 0.0F, false);
		WaterDome4.texOffs(24, 52).addBox(-15.0F, -9.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome4.texOffs(50, 44).addBox(-15.0F, -9.0F, 3.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome4.texOffs(0, 50).addBox(-15.0F, -9.0F, 2.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome4.texOffs(48, 48).addBox(-15.0F, -9.0F, 1.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome4.texOffs(46, 81).addBox(-8.0F, -6.0F, 1.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome4.texOffs(62, 75).addBox(-14.0F, -6.0F, 1.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome4.texOffs(34, 81).addBox(-12.0F, -6.0F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome4.texOffs(0, 81).addBox(-14.0F, -6.0F, 3.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome4.texOffs(73, 80).addBox(-11.0F, -6.0F, 3.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome4.texOffs(57, 86).addBox(-9.0F, -6.0F, 2.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome4.texOffs(80, 71).addBox(-8.0F, -6.0F, 3.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome4.texOffs(61, 80).addBox(-15.0F, -6.0F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome4.texOffs(86, 54).addBox(-10.0F, -6.0F, 1.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome4.texOffs(47, 86).addBox(-7.0F, -6.0F, 2.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome4.texOffs(19, 86).addBox(-11.0F, -7.0F, 2.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome4.texOffs(9, 86).addBox(-15.0F, -7.0F, 2.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome4.texOffs(85, 80).addBox(-13.0F, -7.0F, 1.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome4.texOffs(85, 64).addBox(-13.0F, -7.0F, 3.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome4.texOffs(38, 85).addBox(-11.0F, -7.0F, 3.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome4.texOffs(28, 85).addBox(-7.0F, -7.0F, 3.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);

		WaterDome5 = new ModelRenderer(this);
		WaterDome5.setPos(-7.0F, 0.0F, -17.0F);
		WaterDomeMain.addChild(WaterDome5);
		setRotationAngle(WaterDome5, 0.0F, 1.5708F, 0.0F);
		WaterDome5.texOffs(36, 16).addBox(-33.0F, -5.0F, 20.0F, 12.0F, 1.0F, 3.0F, 0.0F, false);
		WaterDome5.texOffs(48, 40).addBox(-33.0F, -9.0F, 23.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome5.texOffs(24, 48).addBox(-33.0F, -9.0F, 22.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome5.texOffs(0, 46).addBox(-33.0F, -9.0F, 21.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome5.texOffs(26, 44).addBox(-33.0F, -9.0F, 20.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome5.texOffs(23, 80).addBox(-26.0F, -6.0F, 20.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome5.texOffs(48, 75).addBox(-32.0F, -6.0F, 20.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome5.texOffs(80, 22).addBox(-30.0F, -6.0F, 21.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome5.texOffs(11, 80).addBox(-32.0F, -6.0F, 22.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome5.texOffs(79, 74).addBox(-29.0F, -6.0F, 22.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome5.texOffs(85, 20).addBox(-27.0F, -6.0F, 21.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome5.texOffs(50, 79).addBox(-26.0F, -6.0F, 22.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome5.texOffs(38, 79).addBox(-33.0F, -6.0F, 21.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome5.texOffs(85, 16).addBox(-28.0F, -6.0F, 20.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome5.texOffs(85, 12).addBox(-25.0F, -6.0F, 21.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome5.texOffs(85, 8).addBox(-29.0F, -7.0F, 21.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome5.texOffs(0, 85).addBox(-33.0F, -7.0F, 21.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome5.texOffs(79, 84).addBox(-31.0F, -7.0F, 20.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome5.texOffs(69, 84).addBox(-31.0F, -7.0F, 22.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome5.texOffs(59, 84).addBox(-29.0F, -7.0F, 22.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome5.texOffs(84, 58).addBox(-25.0F, -7.0F, 22.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);

		WaterDome6 = new ModelRenderer(this);
		WaterDome6.setPos(0.0F, 0.0F, 33.0F);
		WaterDomeMain.addChild(WaterDome6);
		WaterDome6.texOffs(0, 0).addBox(-10.0F, -5.0F, -2.0F, 18.0F, 1.0F, 3.0F, 0.0F, false);
		WaterDome6.texOffs(0, 20).addBox(-10.0F, -9.0F, 1.0F, 18.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome6.texOffs(0, 16).addBox(-10.0F, -9.0F, 0.0F, 18.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome6.texOffs(0, 12).addBox(-10.0F, -9.0F, -1.0F, 18.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome6.texOffs(0, 8).addBox(-10.0F, -9.0F, -2.0F, 18.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome6.texOffs(60, 26).addBox(-3.0F, -6.0F, -2.0F, 11.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome6.texOffs(0, 44).addBox(-9.0F, -6.0F, -2.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome6.texOffs(60, 24).addBox(-7.0F, -6.0F, -1.0F, 11.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome6.texOffs(23, 60).addBox(-9.0F, -6.0F, 0.0F, 11.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome6.texOffs(0, 59).addBox(-6.0F, -6.0F, 0.0F, 11.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome6.texOffs(0, 65).addBox(-4.0F, -6.0F, -1.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome6.texOffs(44, 58).addBox(-3.0F, -6.0F, 0.0F, 11.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome6.texOffs(21, 57).addBox(-10.0F, -6.0F, -1.0F, 11.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome6.texOffs(64, 54).addBox(-5.0F, -6.0F, -2.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome6.texOffs(43, 64).addBox(-2.0F, -6.0F, -1.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome6.texOffs(21, 64).addBox(-6.0F, -7.0F, -1.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome6.texOffs(63, 20).addBox(-10.0F, -7.0F, -1.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome6.texOffs(63, 16).addBox(-8.0F, -7.0F, -2.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome6.texOffs(63, 12).addBox(-8.0F, -7.0F, 0.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome6.texOffs(63, 8).addBox(-6.0F, -7.0F, 0.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome6.texOffs(0, 63).addBox(-2.0F, -7.0F, 0.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);

		WaterDome7 = new ModelRenderer(this);
		WaterDome7.setPos(15.0F, 0.0F, 30.0F);
		WaterDomeMain.addChild(WaterDome7);
		WaterDome7.texOffs(45, 54).addBox(-10.0F, -5.0F, -2.0F, 8.0F, 1.0F, 3.0F, 0.0F, false);
		WaterDome7.texOffs(48, 71).addBox(-10.0F, -9.0F, 1.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome7.texOffs(0, 71).addBox(-10.0F, -9.0F, 0.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome7.texOffs(32, 70).addBox(-10.0F, -9.0F, -1.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome7.texOffs(68, 56).addBox(-10.0F, -9.0F, -2.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome7.texOffs(32, 87).addBox(-3.0F, -6.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome7.texOffs(86, 66).addBox(-9.0F, -6.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome7.texOffs(28, 87).addBox(-7.0F, -6.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome7.texOffs(87, 5).addBox(-9.0F, -6.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome7.texOffs(4, 87).addBox(-6.0F, -6.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome7.texOffs(39, 0).addBox(-4.0F, -6.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome7.texOffs(87, 3).addBox(-3.0F, -6.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome7.texOffs(87, 1).addBox(-10.0F, -6.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome7.texOffs(36, 21).addBox(-5.0F, -6.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome7.texOffs(36, 20).addBox(-2.0F, -6.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome7.texOffs(36, 19).addBox(-6.0F, -7.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome7.texOffs(36, 17).addBox(-10.0F, -7.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome7.texOffs(36, 16).addBox(-8.0F, -7.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome7.texOffs(36, 15).addBox(-8.0F, -7.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome7.texOffs(36, 13).addBox(-6.0F, -7.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome7.texOffs(36, 12).addBox(-2.0F, -7.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		WaterDome8 = new ModelRenderer(this);
		WaterDome8.setPos(-23.0F, 0.0F, 27.0F);
		WaterDomeMain.addChild(WaterDome8);
		WaterDome8.texOffs(0, 54).addBox(8.0F, -5.0F, 1.0F, 8.0F, 1.0F, 3.0F, 0.0F, false);
		WaterDome8.texOffs(16, 68).addBox(8.0F, -9.0F, 4.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome8.texOffs(59, 67).addBox(8.0F, -9.0F, 3.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome8.texOffs(0, 67).addBox(8.0F, -9.0F, 2.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome8.texOffs(43, 66).addBox(8.0F, -9.0F, 1.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome8.texOffs(0, 87).addBox(15.0F, -6.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome8.texOffs(19, 55).addBox(9.0F, -6.0F, 1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome8.texOffs(85, 86).addBox(11.0F, -6.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome8.texOffs(81, 86).addBox(9.0F, -6.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome8.texOffs(77, 86).addBox(12.0F, -6.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome8.texOffs(36, 11).addBox(14.0F, -6.0F, 2.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome8.texOffs(73, 86).addBox(15.0F, -6.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome8.texOffs(28, 76).addBox(8.0F, -6.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome8.texOffs(36, 9).addBox(13.0F, -6.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome8.texOffs(36, 8).addBox(16.0F, -6.0F, 2.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome8.texOffs(36, 7).addBox(12.0F, -7.0F, 2.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome8.texOffs(0, 5).addBox(8.0F, -7.0F, 2.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome8.texOffs(0, 4).addBox(10.0F, -7.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome8.texOffs(0, 3).addBox(10.0F, -7.0F, 3.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome8.texOffs(0, 1).addBox(12.0F, -7.0F, 3.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome8.texOffs(0, 0).addBox(16.0F, -7.0F, 3.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		WaterDome9 = new ModelRenderer(this);
		WaterDome9.setPos(-14.0F, 0.0F, 35.0F);
		WaterDomeMain.addChild(WaterDome9);
		setRotationAngle(WaterDome9, 0.0F, -1.5708F, 0.0F);
		WaterDome9.texOffs(36, 12).addBox(-19.0F, -5.0F, 1.0F, 12.0F, 1.0F, 3.0F, 0.0F, false);
		WaterDome9.texOffs(42, 4).addBox(-19.0F, -9.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome9.texOffs(42, 0).addBox(-19.0F, -9.0F, 3.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome9.texOffs(24, 40).addBox(-19.0F, -9.0F, 2.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome9.texOffs(0, 40).addBox(-19.0F, -9.0F, 1.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome9.texOffs(79, 32).addBox(-12.0F, -6.0F, 1.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome9.texOffs(74, 52).addBox(-18.0F, -6.0F, 1.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome9.texOffs(79, 28).addBox(-16.0F, -6.0F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome9.texOffs(0, 79).addBox(-18.0F, -6.0F, 3.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome9.texOffs(70, 78).addBox(-15.0F, -6.0F, 3.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome9.texOffs(84, 56).addBox(-13.0F, -6.0F, 2.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome9.texOffs(27, 78).addBox(-12.0F, -6.0F, 3.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome9.texOffs(15, 78).addBox(-19.0F, -6.0F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome9.texOffs(49, 84).addBox(-14.0F, -6.0F, 1.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome9.texOffs(19, 84).addBox(-11.0F, -6.0F, 2.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome9.texOffs(9, 84).addBox(-15.0F, -7.0F, 2.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome9.texOffs(40, 83).addBox(-19.0F, -7.0F, 2.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome9.texOffs(83, 37).addBox(-17.0F, -7.0F, 1.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome9.texOffs(30, 83).addBox(-17.0F, -7.0F, 3.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome9.texOffs(83, 25).addBox(-15.0F, -7.0F, 3.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome9.texOffs(0, 83).addBox(-11.0F, -7.0F, 3.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);

		WaterDome10 = new ModelRenderer(this);
		WaterDome10.setPos(-7.0F, 0.0F, -1.0F);
		WaterDomeMain.addChild(WaterDome10);
		setRotationAngle(WaterDome10, 0.0F, 1.5708F, 0.0F);
		WaterDome10.texOffs(36, 8).addBox(-29.0F, -5.0F, 20.0F, 12.0F, 1.0F, 3.0F, 0.0F, false);
		WaterDome10.texOffs(36, 36).addBox(-29.0F, -9.0F, 23.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome10.texOffs(36, 32).addBox(-29.0F, -9.0F, 22.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome10.texOffs(36, 28).addBox(-29.0F, -9.0F, 21.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome10.texOffs(36, 24).addBox(-29.0F, -9.0F, 20.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);
		WaterDome10.texOffs(59, 77).addBox(-22.0F, -6.0F, 20.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome10.texOffs(66, 22).addBox(-28.0F, -6.0F, 20.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome10.texOffs(47, 77).addBox(-26.0F, -6.0F, 21.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome10.texOffs(75, 76).addBox(-28.0F, -6.0F, 22.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome10.texOffs(16, 76).addBox(-25.0F, -6.0F, 22.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome10.texOffs(82, 78).addBox(-23.0F, -6.0F, 21.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome10.texOffs(75, 69).addBox(-22.0F, -6.0F, 22.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome10.texOffs(75, 67).addBox(-29.0F, -6.0F, 21.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome10.texOffs(77, 82).addBox(-24.0F, -6.0F, 20.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome10.texOffs(67, 82).addBox(-21.0F, -6.0F, 21.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome10.texOffs(57, 82).addBox(-25.0F, -7.0F, 21.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome10.texOffs(82, 34).addBox(-29.0F, -7.0F, 21.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome10.texOffs(82, 30).addBox(-27.0F, -7.0F, 20.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome10.texOffs(21, 82).addBox(-27.0F, -7.0F, 22.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome10.texOffs(11, 82).addBox(-25.0F, -7.0F, 22.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		WaterDome10.texOffs(32, 68).addBox(-21.0F, -7.0F, 22.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
							   float alpha) {
		WaterDomeMain.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}