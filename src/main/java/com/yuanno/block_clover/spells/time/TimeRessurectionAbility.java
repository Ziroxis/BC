package com.yuanno.block_clover.spells.time;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;
import com.yuanno.block_clover.api.data.IExtraUpdateData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import com.yuanno.block_clover.networking.server.SUpdateEquippedAbilityPacket;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;

public class TimeRessurectionAbility extends PassiveAbility implements IExtraUpdateData {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Time ressurection", AbilityCategories.AbilityCategory.ATTRIBUTE, TimeRessurectionAbility.class)
            .setDescription("Consumes all your time, giving yourself another chance.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    private double strain = 180 * 20;
    private int healTicks = 0;
    private int outTicks = 0;
    public TimeRessurectionAbility()
    {
        super(INSTANCE);
        this.hideInGUI(false);
        this.setMaxCooldown(180);
        this.setCooldown(180);
        this.duringPassiveEvent = this::duringPassiveEvent;
    }

    public void duringPassiveEvent(PlayerEntity user)
    {
        if(user.level.isClientSide)
            return;
        if (strain < 180 * 20)
            strain++;
        if (user.getHealth() < 2 && this.strain >= 180 * 20) {
            IEntityStats entityStats = EntityStatsCapability.get(user);
            if (entityStats.getTime() > 300)
            {
                user.heal(user.getMaxHealth() - user.getHealth());
                entityStats.setTime(0);
                strain = 0;
                PacketHandler.sendTo(new SSyncEntityStatsPacket(user.getId(), entityStats), user);
            }
        }
    }

    public void increaseReviveTime()
    {
        this.strain = Math.min(this.strain + Math.max(Beapi.randomDouble(), 0.25), 4);
        this.healTicks = 0;
    }

    public void resetReviveTime(PlayerEntity user, boolean resetStrain)
    {
        if(resetStrain)
            this.strain = 0;

        this.healTicks = 0;
        this.stopCooldown(user);
    }

    public double getStrain() {
        return this.strain;
    }

    @Override
    public CompoundNBT getExtraData()
    {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putDouble("strain", this.strain);
        nbt.putInt("healTicks", this.healTicks);
        return nbt;
    }

    @Override
    public void setExtraData(CompoundNBT nbt)
    {
        this.strain = nbt.getDouble("strain");
        this.healTicks = nbt.getInt("healTicks");
    }
}
