package com.yuanno.block_clover.api.ability.sorts;

import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.events.ability.AbilityUseEvent;
import com.yuanno.block_clover.events.experience.ExperienceUpEvent;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.networking.ManaSync;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;

import java.io.Serializable;

/**
 * Ability for when you punch something, the ability has different behavior depending on {@link #isStoppingAfterHit}
 * This ability has 2 components:
 * 1. {@link #hitEffect(PlayerEntity, LivingEntity)} hitting of the actual entity, what happens there etc.
 * 2. {@link #tick(PlayerEntity)} what happens every tick of the ability
 */
public class PunchAbility extends ContinuousAbility {
    private boolean isStoppingAfterHit = true;
    protected IOnHitEntity onHitEntityEvent = (player, target) -> { return 0; };
    protected IOnHitEffect onHitEffectEvent = (player, target) -> {};

    public PunchAbility(AbilityCore core)
    {
        super(core);
    }

    public void hitEffect(PlayerEntity player, LivingEntity target)
    {
        this.onHitEffectEvent.hitEffect(player, target);

        if(this.isStoppingAfterHit)
            this.endContinuity(player);
    }
    public float hitEntity(PlayerEntity player, LivingEntity target)
    {
        float result = this.onHitEntityEvent.onHitEntity(player, target);

        AbilityUseEvent post = new AbilityUseEvent.Post(player, this);
        MinecraftForge.EVENT_BUS.post(post);
        this.endContinuity(player);

        return result;
    }

    @Override
    public void tick(PlayerEntity player) {
        IEntityStats propsEntity = EntityStatsCapability.get(player);

        if (player.level.isClientSide)
            return;

        if (!canUse(player)) {
            endContinuity(player);
            return;
        }

        String resourceName = BeJavapi.getResourceName(getName());
        player.level.getProfiler().push(resourceName);


        if (isContinuous()) {
            continueTime++;

            boolean shouldEndContinuity =
                    (propsEntity.getMana() < getmanaCost() + 5 && getmanaCost() != 0); // checks if the ability should continue

            if (!isStateForced()) {
                duringContinuityEvent.duringContinuity(player, continueTime);
                AbilityUseEvent per = new AbilityUseEvent.Per(player, this, true, false, false);
                MinecraftForge.EVENT_BUS.post(per);
            }

            if (shouldEndContinuity) {
                endContinuity(player);
            }

        }

        player.level.getProfiler().pop();
    }

    public DamageSource getPunchDamageSource(PlayerEntity player)
    {
        return ModDamageSource.causeAbilityDamage(player, this);
    }
    public interface IOnHitEntity extends Serializable
    {
        float onHitEntity(PlayerEntity player, LivingEntity target);
    }

    public interface IOnHitEffect extends Serializable
    {
        void hitEffect(PlayerEntity player, LivingEntity target);
    }

    public void setStoppingAfterHit(boolean flag)
    {
        this.isStoppingAfterHit = flag;
    }
}
