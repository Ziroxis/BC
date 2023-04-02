package com.yuanno.block_clover.spells.sword;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IMultiTargetAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.items.weapons.DemonDwellerItem;
import com.yuanno.block_clover.spells.slash.ForwardThrustAbility;
import com.yuanno.block_clover.spells.slash.SlashBladesAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class AirDashAbility extends Ability implements IMultiTargetAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Air dash", AbilityCategories.AbilityCategory.ATTRIBUTE, AirDashAbility.class)
            .setDescription("Thrusts forwards if you have your sword, dealing damage.")
            .setDamageKind(AbilityDamageKind.SLASH)
            .build();
    boolean hasSword = false;
    public AirDashAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(10);
        this.onUseEvent = this::onUseEvent;
        this.duringCooldownEvent = this::duringCooldown;

    }
    private boolean onUseEvent(PlayerEntity player)
    {
        hasSword = player.getMainHandItem().getItem().asItem() instanceof DemonDwellerItem || player.getOffhandItem().getItem().asItem() instanceof DemonDwellerItem;
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
                if (hasSword)
                    ((ServerWorld) player.level).sendParticles(ParticleTypes.SWEEP_ATTACK, entity.getX(), entity.getY(),
                        entity.getZ(), (int) 10, 3, 3, 3, 0.1);

                if(this.isTarget(entity) && player.canSee(entity)) {
                    if (hasSword)
                        entity.hurt(ModDamageSource.causeAbilityDamage(player, this, "player"), 10);
                    Vector3d speed = Beapi.propulsion(player, 5, 5);
                    entity.setDeltaMovement(speed.x, 0.3, speed.z);
                }
            });
        }
    }

    public boolean canDealDamage()
    {
        return this.cooldown > this.getMaxCooldown() * 0.9;
    }
}
