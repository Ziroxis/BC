package com.example.block_clover.spells.lightning;

import com.example.block_clover.api.Beapi;
import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.interfaces.IMultiTargetAbility;
import com.example.block_clover.api.ability.sorts.RepeaterAbility;
import com.example.block_clover.data.ability.AbilityDataCapability;
import com.example.block_clover.data.ability.IAbilityData;
import com.example.block_clover.init.ModDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class ThunderFiendMultiAbility extends RepeaterAbility implements IMultiTargetAbility {
    public static final ThunderFiendMultiAbility INSTANCE = new ThunderFiendMultiAbility();

    public ThunderFiendMultiAbility()
    {
        super("Thunder Fiend Multi", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots yourself multiple times forward with your boots");
        this.setmanaCost(20);
        this.setMaxCooldown(10);
        this.setExperiencePoint(10);
        this.setMaxRepeaterCount(3, 6);
        this.onUseEvent = this::onUseEvent;
        this.duringCooldownEvent = this::duringCooldown;
    }

    private boolean onUseEvent(PlayerEntity player)
    {

        IAbilityData abilityProps = AbilityDataCapability.get(player);
        for (Ability ability : abilityProps.getEquippedAbilities(AbilityCategories.AbilityCategory.ALL))
        {
            if (ability == null)
                continue;

            try
            {
                if (ability instanceof ThunderGodBootsAbility && ability.isContinuous())
                {
                    this.clearTargets();

                    Vector3d speed = Beapi.propulsion(player, 9, 9);
                    player.setDeltaMovement(speed.x, 0.2, speed.z);
                    player.hurtMarked = true;
                    ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
                    return true;
                }
                else
                {
                    player.sendMessage(new StringTextComponent("Need to put on your electric boots!"), Util.NIL_UUID);
                    return false;
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return false;
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
                    entity.hurt(ModDamageSource.causeAbilityDamage(player, this, "player"), 10);
            });
        }
    }

    public boolean canDealDamage()
    {
        return this.cooldown > this.getMaxCooldown() * 0.9;
    }
}
