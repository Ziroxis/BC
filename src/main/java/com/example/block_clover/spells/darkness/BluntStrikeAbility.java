package com.example.block_clover.spells.darkness;

import com.example.block_clover.api.Beapi;
import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.interfaces.IMultiTargetAbility;
import com.example.block_clover.init.ModDamageSource;
import com.example.block_clover.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import org.lwjgl.system.CallbackI;

import java.util.List;

public class BluntStrikeAbility extends Ability implements IMultiTargetAbility {
    public static final BluntStrikeAbility INSTANCE = new BluntStrikeAbility();

    public BluntStrikeAbility()
    {
        super("Blunt Strike", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Dashes forward striking your enemies with the blunt side of your sword.\n The enemies hit will be unconscious for a few seconds");
        this.setmanaCost(15);
        this.setMaxCooldown(10);
        this.setExperiencePoint(15);
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

        Vector3d speed = Beapi.propulsion(player, 5, 5);
        player.setDeltaMovement(speed.x, 0.3, speed.z);
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
                entity.addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), 80, 0));
                if(this.isTarget(entity) && player.canSee(entity))
                    entity.hurt(ModDamageSource.causeAbilityDamage(player, this, "player"), 10);
            });
        }
    }

    public boolean canDealDamage()
    {
        return this.cooldown > this.getMaxCooldown() * 0.9;
    }
}
