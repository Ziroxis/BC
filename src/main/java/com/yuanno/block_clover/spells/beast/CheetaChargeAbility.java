package com.yuanno.block_clover.spells.beast;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IMultiTargetAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.init.ModDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class CheetaChargeAbility extends Ability implements IMultiTargetAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Cheeta Charge", AbilityCategories.AbilityCategory.ATTRIBUTE, CheetaChargeAbility.class)
            .setDescription("Lunges forward as a cheeta, dealing damage and extra damage with bear claw")
            .setDamageKind(AbilityDamageKind.SLASH)
            .build();
    private int damage;
    private double radius;
    public CheetaChargeAbility()
    {
        super(INSTANCE);
        this.setmanaCost(20);
        this.setMaxCooldown(10);
        this.setExperiencePoint(10);
        this.setEvolutionCost(30);
        this.onUseEvent = this::onUseEvent;
        this.duringCooldownEvent = this::duringCooldown;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        this.clearTargets();
        IAbilityData abilityData = AbilityDataCapability.get(player);
        BearClawAbility bearClawAbility = (BearClawAbility) abilityData.getEquippedAbility(BearClawAbility.INSTANCE);
        if (this.isEvolved())
            this.damage = 10;
        else
            this.damage = 5;
        if (bearClawAbility != null && bearClawAbility.isContinuous())
            this.damage += 5;
        Vector3d speed = Beapi.propulsion(player, 5, 5);
        player.setDeltaMovement(speed.x, 0.3, speed.z);
        player.hurtMarked = true;
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));

        return true;


    }
    private void duringCooldown(PlayerEntity player, int cooldownTimer)
    {
        if (this.isEvolved())
            this.radius = 3;
        else
            this.radius = 1.5;
        if (this.canDealDamage())
        {

            List<LivingEntity> list = Beapi.getEntitiesNear(player.blockPosition(), player.level, radius, LivingEntity.class);
            list.remove(player);

            list.forEach(entity ->
            {
                ((ServerWorld) player.level).sendParticles(ParticleTypes.SWEEP_ATTACK, entity.getX(), entity.getY(),
                        entity.getZ(), (int) 10, 3, 3, 3, 0.1);

                if(this.isTarget(entity) && player.canSee(entity))
                    entity.hurt(ModDamageSource.causeAbilityDamage(player, this, "player"), this.damage);
            });
        }
    }

    public boolean canDealDamage()
    {
        return this.cooldown > this.getMaxCooldown() * 0.9;
    }

}
