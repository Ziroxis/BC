package com.yuanno.block_clover.spells.curse;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.spells.beast.LionsHowlAbility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;

public class HealingCurseAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Healing curse", AbilityCategories.AbilityCategory.DEVIL, HealingCurseAbility.class)
            .setDescription("Curse yourslelf with amazing healing prowess")
            .setDamageKind(AbilityDamageKind.CURSE)
            .build();
    public HealingCurseAbility()
    {
        super(INSTANCE);
        this.setmanaCost(30);
        this.setEvolutionCost(50);
        this.setMaxCooldown(25);
        this.setExperiencePoint(25);
    }

    public boolean onUseEvent(PlayerEntity player)
    {
        player.addEffect(new EffectInstance(ModEffects.HEALING_CURSE.get(), 180 , 0));
        return true;
    }
}
