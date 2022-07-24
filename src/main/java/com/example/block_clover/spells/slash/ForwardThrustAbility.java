package com.example.block_clover.spells.slash;

import com.example.block_clover.api.Beapi;
import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.interfaces.IMultiTargetAbility;
import com.example.block_clover.data.ability.AbilityDataCapability;
import com.example.block_clover.data.ability.IAbilityData;
import com.example.block_clover.init.ModDamageSource;
import com.example.block_clover.spells.lightning.ThunderGodBootsAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class ForwardThrustAbility extends Ability implements IMultiTargetAbility {
    public static final ForwardThrustAbility INSTANCE = new ForwardThrustAbility();

    public ForwardThrustAbility()
    {
        super("Forward Thrust", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Thrusts forward dealing damage");
        this.setmanaCost(20);
        this.setMaxCooldown(10);
        this.setExperiencePoint(10);
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
                if (ability instanceof SlashBladesAbility && ability.isContinuous())
                {
                    this.clearTargets();

                    Vector3d speed = Beapi.propulsion(player, 5, 5);
                    player.setDeltaMovement(speed.x, 0.3, speed.z);
                    player.hurtMarked = true;
                    ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
                    return true;
                }
                else
                {
                    player.sendMessage(new StringTextComponent("Need to put on your slash blades!"), Util.NIL_UUID);
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
