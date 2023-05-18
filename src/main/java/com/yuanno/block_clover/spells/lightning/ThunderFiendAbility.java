package com.yuanno.block_clover.spells.lightning;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IMultiTargetAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.darkness.BluntStrikeParticleEffect;
import com.yuanno.block_clover.particles.lightning.ThunderFiendParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.List;


public class ThunderFiendAbility extends Ability implements IMultiTargetAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Thunder Fiend", AbilityCategories.AbilityCategory.ATTRIBUTE, ThunderFiendAbility.class)
            .setDescription("Shoots yourself forward with your boots.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .setDependencies(ThunderGodBootsAbility.INSTANCE)
            .build();
    private static final ParticleEffect PARTICLES = new ThunderFiendParticleEffect();
    double radius = 1.5;
    int damage = 10;
    public ThunderFiendAbility()
    {
        super(INSTANCE);
        this.setmanaCost(20);
        this.setMaxCooldown(10);
        this.setExperiencePoint(10);
        this.setEvolutionCost(50);
        this.onUseEvent = this::onUseEvent;
        this.duringCooldownEvent = this::duringCooldown;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        this.clearTargets();


        Vector3d speed = Beapi.propulsion(player, 9, 9);
        player.setDeltaMovement(speed.x, 0.2, speed.z);
        player.hurtMarked = true;
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        return true;

    }
    private void duringCooldown(PlayerEntity player, int cooldownTimer)
    {
        if (this.isEvolved()) {
            radius = 3;
            damage = 14;
        }
        if (this.canDealDamage())
        {
            List<LivingEntity> list = Beapi.getEntitiesNear(player.blockPosition(), player.level, radius, LivingEntity.class);
            list.remove(player);

            list.forEach(entity ->
            {
                if (!player.level.isClientSide)
                    PARTICLES.spawn(player.level, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
                if(this.isTarget(entity) && player.canSee(entity))
                    entity.hurt(ModDamageSource.causeAbilityDamage(player, this, "player"), damage);
            });
        }
    }

    public boolean canDealDamage()
    {
        return this.cooldown > this.getMaxCooldown() * 0.9;
    }
}
