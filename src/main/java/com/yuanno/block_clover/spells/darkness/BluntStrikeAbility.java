package com.yuanno.block_clover.spells.darkness;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IMultiTargetAbility;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class BluntStrikeAbility extends Ability implements IMultiTargetAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Blunt strike", AbilityCategories.AbilityCategory.ATTRIBUTE, BluntStrikeAbility.class)
            .setDescription("Dashes forward striking your enemies with the blunt side of your sword.\n The enemies hit will be unconscious for a few seconds")
            .setDamageKind(AbilityDamageKind.PHYSICAL)
            .build();
    double MOVEMENT_SPEED;
    double RADIUS;
    float DAMAGE;
    int STUN_COOLDOWN;
    public BluntStrikeAbility()
    {
        super(INSTANCE);
        this.setmanaCost(15);
        this.setMaxCooldown(10);
        this.setExperiencePoint(15);
        this.setEvolutionCost(30);
        this.onUseEvent = this::onUseEvent;
        this.duringCooldownEvent = this::duringCooldown;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        if (!(player.getMainHandItem().getItem() instanceof SwordItem))
        {
            player.sendMessage(new StringTextComponent("Need to hold a sword!"), Util.NIL_UUID);
            return false;
        }
        this.clearTargets();
        if (!this.isEvolved())
            MOVEMENT_SPEED = 0.3;
        else
            MOVEMENT_SPEED = 0.6;
        Vector3d speed = Beapi.propulsion(player, 5, 5);
        player.setDeltaMovement(speed.x, MOVEMENT_SPEED, speed.z);
        player.hurtMarked = true;
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        return true;
    }

    private void duringCooldown(PlayerEntity player, int cooldownTimer)
    {
        if (!this.isEvolved())
        {
            RADIUS = 1.5;
            DAMAGE = 5;
            STUN_COOLDOWN = 80;
        }
        else
        {
            RADIUS = 3;
            DAMAGE = 10;
            STUN_COOLDOWN = 120;
        }
        if (this.canDealDamage())
        {
            List<LivingEntity> list = Beapi.getEntitiesNear(player.blockPosition(), player.level, RADIUS, LivingEntity.class);
            list.remove(player);

            list.forEach(entity ->
            {
                entity.addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), STUN_COOLDOWN, 0));
                if(this.isTarget(entity) && player.canSee(entity))
                    entity.hurt(ModDamageSource.causeAbilityDamage(player, this, "player"), DAMAGE);
            });
        }
    }

    public boolean canDealDamage()
    {
        return this.cooldown > this.getMaxCooldown() * 0.9;
    }
}
