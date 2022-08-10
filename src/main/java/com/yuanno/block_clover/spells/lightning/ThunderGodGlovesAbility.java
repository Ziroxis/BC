package com.yuanno.block_clover.spells.lightning;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousPunchAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.lightning.GlovesParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

import java.util.UUID;

public class ThunderGodGlovesAbility extends ContinuousPunchAbility implements IParallelContinuousAbility {

    public static final ThunderGodGlovesAbility INSTANCE = new ThunderGodGlovesAbility();
    private ParticleEffect PARTICLES = new GlovesParticleEffect();

    private static final AttributeModifier LIGHTNING_ATTACK = new AttributeModifier(UUID.fromString("e63c1084-fec9-11ec-b939-0242ac120002"),
            "Lightning Attack", 2, AttributeModifier.Operation.ADDITION);
    private static final AttributeModifier LIGHTNING_ATTACK_SPEED = new AttributeModifier(UUID.fromString("ead502b8-fec9-11ec-b939-0242ac120002"),
            "Lightning Attack speed", 1, AttributeModifier.Operation.ADDITION);

    public ThunderGodGlovesAbility()
    {
        super("Thunder God Gloves", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("The user gets speed by enveloping himself with lightning gloves");
        this.setmanaCost(5);
        this.setMaxCooldown(5);
        this.setExperiencePoint(10);
        this.setExperienceGainLevelCap(50);

        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onHitEntityEvent = this::onHit;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        IEntityStats propsEntity = EntityStatsCapability.get(player);
        propsEntity.alterMana(-15);
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(LIGHTNING_ATTACK);
        player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(LIGHTNING_ATTACK_SPEED);
        return true;
    }
    private float onHit(PlayerEntity player, LivingEntity target)
    {
        double knockback = 0.6;
        //target.push((player.getX() + target.getX()) * 0.0015, (player.getY() + target.getY() + 1) * 0.0003, (player.getZ() + target.getZ()) * 0.0015);
        //target.knockback((float) (player.getX() - target.getX() + 1), 0.1, (float) (player.getZ() - target.getZ() + 1));
        ((LivingEntity)target).knockback((float)knockback * 0.5F, (double) MathHelper.sin(player.yRot * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(player.yRot * ((float)Math.PI / 180F))));
        PARTICLES.spawn(target.level, target.getX(), target.getY(), target.getZ(), 0, 0, 0);
        return 1;
    }
    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(LIGHTNING_ATTACK);
        player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(LIGHTNING_ATTACK_SPEED);
        return true;
    }
}
