package com.example.block_clover.items.clothes;

import com.example.block_clover.Main;
import com.example.block_clover.init.ArmorMaterials;
import com.example.block_clover.init.ModItemGroup;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class BasicArmorItem extends DyeableArmorItem {

    protected String name;
    protected boolean hasOverlay;

    public BasicArmorItem(String name, EquipmentSlotType type)
    {
        this(name, type, false);
    }

    public BasicArmorItem(String name, EquipmentSlotType type, boolean hasOverlay)
    {
        super(ArmorMaterials.BASIC_ARMOR_MATERIAL, type, (new Properties()).tab(ModItemGroup.BLOCK_CLOVER_CLOTHES));
        this.name = name;
        this.hasOverlay = hasOverlay;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    @Nullable
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default)
    {
        A armorModel = (A) new BipedModel(0.5F);

        return armorModel;
    }

    @Override
    @Nullable
    public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type)
    {
        return String.format("%s:textures/models/armor/%s_%d%s.png", Main.MODID, this.name, slot == EquipmentSlotType.LEGS ? 2 : 1, type == null || !this.hasOverlay ? "" : String.format("_%s", type));
    }

}
