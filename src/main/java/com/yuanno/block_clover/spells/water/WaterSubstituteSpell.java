package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.entities.summons.water.WaterSubstituteEntity;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;

import java.util.ArrayList;
import java.util.List;

public class WaterSubstituteSpell extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Water Substitutes", AbilityCategories.AbilityCategory.ATTRIBUTE, WaterSubstituteSpell.class)
            .setDescription("Summons multiple water substitutes, confusing the enemy")
            .build();
    List<WaterSubstituteEntity> waterSubstitutes = new ArrayList<>();

    public WaterSubstituteSpell() {
        super(INSTANCE);
        this.setMaxCooldown(10);
        this.setmanaCost(30);
        this.setExperiencePoint(35);
        this.setExperienceGainLevelCap(20);
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
        List<LivingEntity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, 12);
        if (entities.contains(player))
            entities.remove(player);
        entities.forEach(entityInList -> {
            if (entityInList instanceof WaterSubstituteEntity)
                waterSubstitutes.add((WaterSubstituteEntity) entityInList);
            if (!(entityInList instanceof WaterSubstituteEntity) && !(entityInList instanceof PlayerEntity))
            {
                if ((entityInList instanceof CreatureEntity) && !waterSubstitutes.isEmpty()) {
                    ((CreatureEntity) entityInList).setTarget(waterSubstitutes.get(BeJavapi.randomWithRange(0, waterSubstitutes.size() - 1)));
                }
            }

        });
        waterSubstitutes.clear();
        return true;
    }
}
