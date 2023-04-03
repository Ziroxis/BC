package com.yuanno.block_clover.spells.copy;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.entities.MonkeyEntity;
import com.yuanno.block_clover.init.ModAbilities;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.spells.wind.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;

public class CopyAbility extends PunchAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Copy", AbilityCategories.AbilityCategory.ATTRIBUTE, CopyAbility.class)
            .setDescription("Touch a magic entity, copying a spell in their repertoire")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();

    public CopyAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(360);
        this.setmanaCost(0);
        this.setExperiencePoint(0);
        this.onHitEntityEvent = this::onHitEntityEvent;
    }

    private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        if (target instanceof MonkeyEntity)
        {
            int random = Beapi.RNG(7);
            switch (random)
            {
                case 0:
                    abilityData.addUnlockedAbility(player, TornadoPiercingAbility.INSTANCE);
                    break;
                case 1:
                    abilityData.addUnlockedAbility(player, ToweringTornadoAbility.INSTANCE);
                    break;
                case 2:
                    abilityData.addUnlockedAbility(player, WindBladeAbility.INSTANCE);
                    break;
                case 3:
                    abilityData.addUnlockedAbility(player, WindBladeShowerAbility.INSTANCE);
                    break;
                case 4:
                    abilityData.addUnlockedAbility(player, WindCrescentAbility.INSTANCE);
                    break;
                case 5:
                    abilityData.addUnlockedAbility(player, WindFlightAbility.INSTANCE);
                    break;
                case 6:
                    abilityData.addUnlockedAbility(player, WindGaleAbility.INSTANCE);
                    break;
            }
        }
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        player.sendMessage(new TranslationTextComponent("You copied a spell!"), Util.NIL_UUID);
        return 0;
    }
}
