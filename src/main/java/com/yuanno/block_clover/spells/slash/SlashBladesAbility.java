package com.yuanno.block_clover.spells.slash;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;

import java.util.UUID;

public class SlashBladesAbility extends PunchAbility implements IParallelContinuousAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Slash Blades", AbilityCategories.AbilityCategory.ATTRIBUTE, SlashBladesAbility.class)
            .setDescription("Makes slash blades out of magic.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    private static final AttributeModifier SLASH_BLADES = new AttributeModifier(UUID.fromString("114e0640-feea-11ec-b939-0242ac120002"),
            "Slash Blades", 2, AttributeModifier.Operation.ADDITION);


    public SlashBladesAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(3);
        this.setmanaCost(6);
        this.setExperiencePoint(7);
        this.setExperienceGainLevelCap(10);
        this.setStoppingAfterHit(false);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onHitEntityEvent = this::onHitEntityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(SLASH_BLADES);
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).addTransientModifier(SLASH_BLADES);


        return true;
    }

    private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
    {
        player.getPersistentData().putBoolean("slash_damage", false);
        for (LivingEntity livingentity : player.level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(1.0D, 0.25D, 1.0D))) {
            if (livingentity != player && livingentity != target && !player.isAlliedTo(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity) livingentity).isMarker()) && player.distanceToSqr(livingentity) < 9.0D)
            {
                livingentity.knockback(0.8F, (double) MathHelper.sin(player.yRot * ((float) Math.PI / 180F)), (double) (-MathHelper.cos(player.yRot * ((float) Math.PI / 180F))));

            }
        }
        player.level.playSound((PlayerEntity) null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, player.getSoundSource(), 1.0F, 1.0F);
        if(player instanceof PlayerEntity)
            ((PlayerEntity)player).sweepAttack();
        else
            this.sweepAttack(player);

        return 4;
    }
    
    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(SLASH_BLADES);
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).removeModifier(SLASH_BLADES);

        return true;
    }

    public void sweepAttack(LivingEntity entity) {
        double d0 = (double)(-MathHelper.sin(entity.yRot * ((float)Math.PI / 180F)));
        double d1 = (double)MathHelper.cos(entity.yRot * ((float)Math.PI / 180F));
        if (entity.level instanceof ServerWorld) {
            ((ServerWorld)entity.level).sendParticles(ParticleTypes.SWEEP_ATTACK, entity.getX() + d0, entity.getY(0.5D), entity.getZ() + d1, 0, d0, 0.0D, d1, 0.0D);
        }

    }
}
