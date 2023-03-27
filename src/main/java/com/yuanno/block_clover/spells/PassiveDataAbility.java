package com.yuanno.block_clover.spells;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;
import com.yuanno.block_clover.api.data.IExtraUpdateData;
import com.yuanno.block_clover.data.ability.AbilityDataBase;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SUpdateEquippedAbilityPacket;
import com.yuanno.block_clover.networking.server.SUpdateExtraDataPacket;
import com.yuanno.block_clover.spells.fire.FireBallAbility;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassiveDataAbility extends PassiveAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Passive Data", AbilityCategories.AbilityCategory.ATTRIBUTE, PassiveDataAbility.class)
            .setDescription("")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    Map<String, Integer> experienceMap = new HashMap<>();
    Map<String, Integer> experienceMapConstant = new HashMap<>();
    public int experienceTemporary = 0;
    public String abilityName;
    public PassiveDataAbility()
    {
        super(INSTANCE);
        //this.hideInGUI(true);
        this.duringPassiveEvent = this::duringPassiveEvent;
    }

    public void alterExperience(String abilityName)
    {
        this.abilityName = abilityName;
        if (experienceMap.containsKey(abilityName))
        {
            experienceMap.put(abilityName, experienceMap.get(abilityName) + 1);
        }
        else
        {
            experienceMap.put(abilityName, 1);
        }
        System.out.println(experienceMap.get(FireBallAbility.INSTANCE.getName()));
        System.out.println(experienceMap);
    }
    public int getExperience()
    {
        return this.experienceTemporary;
    }

    public void duringPassiveEvent(PlayerEntity player)
    {
        if (!player.level.isClientSide)
        {

            experienceMapConstant.putAll(experienceMap);
            if (player.tickCount % 20 == 0)
            {
                System.out.println(experienceMapConstant);
            }
        }
    }

}
