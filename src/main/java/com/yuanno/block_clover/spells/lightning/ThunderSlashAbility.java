package com.yuanno.block_clover.spells.lightning;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;

public class ThunderSlashAbility extends PunchAbility implements IParallelContinuousAbility {
    public static final ThunderSlashAbility INSTANCE = new ThunderSlashAbility();

    public ThunderSlashAbility()
    {
        super("Thunder Slash", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Slash the enemy with electricity.\nRendering them stunned for a few seconds.");
        this.setMaxCooldown(7);
        this.setExperiencePoint(20);
        this.setmanaCost(5);
        this.onStartContinuityEvent = this::onStartEvent;
        this.onHitEntityEvent = this::onHitEntityEvent;
    }

    private boolean onStartEvent(PlayerEntity player)
    {
        IAbilityData abilityProps = AbilityDataCapability.get(player);

        ThunderGodGlovesAbility godGlovesAbility = abilityProps.getEquippedAbility(ThunderGodGlovesAbility.INSTANCE);
        if (godGlovesAbility != null && godGlovesAbility.isContinuous())
        {
            return true;
        }
        player.sendMessage(new StringTextComponent("Need to put on your thunder god gloves!"), Util.NIL_UUID);
        return false;
    }

    private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        stats.alterMana(-15);
        target.addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), 60,0));
        return 5;
    }
}
