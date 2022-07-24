package com.example.block_clover.spells.earth;

import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.example.block_clover.api.ability.sorts.ContinuousPunchAbility;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.example.block_clover.init.ModAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class EarthGlovesAbility extends ContinuousPunchAbility implements IParallelContinuousAbility {

    public static final EarthGlovesAbility INSTANCE = new EarthGlovesAbility();

    private static final AttributeModifier EARTH_GLOVES = new AttributeModifier(UUID.fromString("60fbf220-0222-11ed-b939-0242ac120002"),
            "Earth Gloves", 5, AttributeModifier.Operation.ADDITION);

    public EarthGlovesAbility()
    {
        super("Earth Gloves", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Makes gloves out of earth");
        this.setMaxCooldown(3);
        this.setmanaCost(3);
        this.setExperiencePoint(10);
        this.setExperienceGainLevelCap(20);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onHitEntityEvent = this::onHitEntityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        stats.alterMana(-15);
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(EARTH_GLOVES);
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).addTransientModifier(EARTH_GLOVES);
        return true;
    }

    private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
    {
        target.push((player.getX() + target.getX()) * 0.3, (player.getY() + target.getY() + 1) * 0.05, (player.getZ() + target.getZ()) * 0.3);
        return 4;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(EARTH_GLOVES);
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).removeModifier(EARTH_GLOVES);
        return true;
    }


}
