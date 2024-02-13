package com.yuanno.block_clover.spells.antimagic;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IMultiTargetAbility;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.init.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class BullThrustAbility extends Ability implements IMultiTargetAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Bull Thrust", AbilityCategories.AbilityCategory.ATTRIBUTE, BullThrustAbility.class)
            .setDescription("The user thrusts forward with their anti magic sword")
            .setDamageKind(AbilityDamageKind.PIERCING)
            .build();

    public BullThrustAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(3);
        this.onUseEvent = this::onUseEvent;
        this.duringCooldownEvent = this::duringCooldown;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        if (!(player.getMainHandItem().getItem().equals(ModItems.DEMON_SLAYER.get())))
        {
            player.sendMessage(new StringTextComponent("Need to hold an anti-magic sword!"), Util.NIL_UUID);
            return false;
        }



        this.clearTargets();

        Vector3d speed = Beapi.propulsion(player, 3, 3);
        player.setDeltaMovement(speed.x, 0.2, speed.z);
        player.hurtMarked = true;
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));

        return true;
    }

    private void duringCooldown(PlayerEntity player, int cooldownTimer)
    {
        if (this.canDealDamage())
        {
            List<LivingEntity> list = Beapi.getEntitiesNear(player.blockPosition(), player.level, 1.5, LivingEntity.class);
            list.remove(player);

            list.forEach(entity ->
            {
                if(this.isTarget(entity) && player.canSee(entity))
                    entity.hurt(ModDamageSource.causeAbilityDamage(player, this, "player"), 12);
            });
        }
    }

    public boolean canDealDamage()
    {
        return this.cooldown > this.getMaxCooldown() * 0.9;
    }
}
