package com.yuanno.block_clover.spells.copy;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.entities.BanditEntity;
import com.yuanno.block_clover.entities.CloverSharkEntity;
import com.yuanno.block_clover.entities.MonkeyEntity;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.spells.water.*;
import com.yuanno.block_clover.spells.wind.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class CopyAbility extends PunchAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Copy", AbilityCategories.AbilityCategory.ATTRIBUTE, CopyAbility.class)
            .setDescription("Touch a magic entity or player, copying a spell in their repertoire")
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
        IAbilityData abilityDataPlayer = AbilityDataCapability.get(player);
        if (target instanceof PlayerEntity)
        {
            PlayerEntity playerTarget = (PlayerEntity) target;
            IAbilityData abilityDataTarget = AbilityDataCapability.get(playerTarget);
            List<Ability> abilityListUnlocked = abilityDataTarget.getUnlockedAbilities();
            int amountAbility = abilityListUnlocked.size();
            int random = Beapi.RNG(amountAbility) + 1;
            abilityDataPlayer.addUnlockedAbility(player, abilityListUnlocked.get(random));
        }
        if (target instanceof BanditEntity)
        {
            BanditEntity targetEntity = (BanditEntity) target;
            int amountAbility = targetEntity.getAbilityList().size();
            int random = Beapi.RNG(amountAbility) + 1;
            abilityDataPlayer.addUnlockedAbility(player, targetEntity.getAbilityList().get(random));
        }
        // TODO link this with bandits
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityDataPlayer), player);
        player.sendMessage(new TranslationTextComponent("You copied a spell!"), Util.NIL_UUID);
        return 0;
    }
}
