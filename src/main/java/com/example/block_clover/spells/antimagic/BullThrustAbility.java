package com.example.block_clover.spells.antimagic;

import com.example.block_clover.api.Beapi;
import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.interfaces.IMultiTargetAbility;
import com.example.block_clover.init.ModDamageSource;
import com.example.block_clover.init.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class BullThrustAbility extends Ability implements IMultiTargetAbility {

    public static final BullThrustAbility INSTANCE = new BullThrustAbility();

    public BullThrustAbility()
    {
        super("Bull Thrust", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("The user thrusts forward with their anti magic sword");
        this.setMaxCooldown(3);
        this.setmanaCost(0);
        this.onUseEvent = this::onUseEvent;
        this.duringCooldownEvent = this::duringCooldown;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        if (!(player.getMainHandItem().getItem().equals(ModItems.DEMON_DWELLER.get())))
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
