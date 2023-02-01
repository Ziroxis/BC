package com.yuanno.block_clover.items.clothes;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModItemGroup;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.models.clothes.AstaClothesModel;
import com.yuanno.block_clover.models.clothes.WitchHatModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class WitchHatItem extends ArmorItem {


    public WitchHatItem() {
        super(WitchHatBase.WITCH_HAT, EquipmentSlotType.HEAD, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_CLOTHES));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            IEntityStats stats = EntityStatsCapability.get(player);
            if (stats.getRace().equals(ModValues.WITCH)) {
                if (player.getItemBySlot(EquipmentSlotType.HEAD).getItem().equals(this.getItem())) {
                    if (!stats.getHatBoost()) {
                        stats.alterManaRegeneration(0.2f);
                        stats.alterMana(20f);
                        stats.setHatBoost(true);
                    }
                } else {
                    if (stats.getHatBoost()) {
                        stats.alterManaRegeneration(-0.2f);
                        stats.alterMana(-20f);
                        stats.setHatBoost(false);
                    }
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    @Nullable
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default)
    {

        A armorModel = (A) new WitchHatModel(armorSlot);

        return armorModel;
    }

    @Override
    @Nullable
    public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type)
    {
        return Main.MODID+ ":textures/models/armor/witch_hat.png";
    }

}


