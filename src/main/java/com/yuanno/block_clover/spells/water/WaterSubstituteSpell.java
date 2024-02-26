package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.entities.summons.water.WaterSubstituteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class WaterSubstituteSpell extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Water Substitutes", AbilityCategories.AbilityCategory.ATTRIBUTE, WaterSubstituteSpell.class)
            .setDescription("Summons multiple water substitutes, confusing the enemy")
            .build();

    public WaterSubstituteSpell() {
        super(INSTANCE);

        this.onUseEvent = this::onUse;
    }

    public boolean onUse(PlayerEntity player)
    {
        for (int i = 0; i < 4; i++)
        {
            WaterSubstituteEntity waterSubstituteEntity = new WaterSubstituteEntity(player.level);
            waterSubstituteEntity.moveTo(player.getX(), player.getY(), player.getZ(), 180, 0);
            waterSubstituteEntity.setOwner(player.getUUID());
            waterSubstituteEntity.setLastHurtByMob(player);
            waterSubstituteEntity.setMaxAliveTicks(300);
            for(EquipmentSlotType slot : EquipmentSlotType.values())
            {
                ItemStack stack = player.getItemBySlot(slot);
                waterSubstituteEntity.setItemSlot(slot, stack);
            }
            player.level.addFreshEntity(waterSubstituteEntity);

        }
        return true;
    }
}
