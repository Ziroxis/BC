package com.yuanno.block_clover.spells.lightning;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.darkness.BluntStrikeParticleEffect;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;

public class ThunderSlashAbility extends PunchAbility implements IParallelContinuousAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Thunder Slash", AbilityCategories.AbilityCategory.ATTRIBUTE, ThunderSlashAbility.class)
            .setDescription("Slash the enemy with electricity.\nRendering them stunned for a few seconds.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .setDependencies(ThunderGodGlovesAbility.INSTANCE)
            .build();
    private static final ParticleEffect PARTICLES = new BluntStrikeParticleEffect();

    public ThunderSlashAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(7);
        this.setExperiencePoint(20);
        this.setmanaCost(5);
        this.onStartContinuityEvent = this::onStartEvent;
        this.onHitEntityEvent = this::onHitEntityEvent;
    }

    private boolean onStartEvent(PlayerEntity player)
    {
        return true;
    }

    private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
    {
        if (!player.level.isClientSide)
            PARTICLES.spawn(player.level, target.getX(), target.getY(), target.getZ(), 0, 0, 0);
        IEntityStats stats = EntityStatsCapability.get(player);
        stats.alterMana(-15);
        target.addEffect(new EffectInstance(ModEffects.ELECTROCUTED.get(), 60,0));
        return 5;
    }
}
