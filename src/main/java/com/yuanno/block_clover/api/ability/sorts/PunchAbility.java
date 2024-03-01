package com.yuanno.block_clover.api.ability.sorts;

import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
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

public class PunchAbility extends ContinuousAbility{
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
        IEntityStats propsEntity = EntityStatsCapability.get(player);

        int experiencePoint = getExperiencePoint();
        int manaCost = (int) getmanaCost();
        int currentLevel = propsEntity.getLevel();
        int experienceGainLevelCap = getExperienceGainLevelCap();

        if (currentLevel < experienceGainLevelCap) {
            propsEntity.alterExperience(experiencePoint);
            ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, experiencePoint);
            MinecraftForge.EVENT_BUS.post(eventExperience);
        }

        // individual spell experience
        if (propsEntity.hasExperienceSpell(this.getName())) {
            int experience = propsEntity.getExperienceSpell(this.getName());
            propsEntity.setExperienceSpells(this.getName(), experience + 1);
        }
        else
            propsEntity.setExperienceSpells(this.getName(), 1);
        int currentMana = (int) propsEntity.getMana();
        if (currentMana > manaCost) {
            propsEntity.alterMana(-manaCost);
        }
        int currentNewMana = (int) propsEntity.getMana();
        PacketHandler.sendTo(new ManaSync(currentNewMana), player);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), propsEntity), player);

        if(this.isStoppingAfterHit)
            this.endContinuity(player);
        return result;
    }

    @Override
    public void tick(PlayerEntity player) {
        IEntityStats propsEntity = EntityStatsCapability.get(player);
        if (!canUse(player)) {
            endContinuity(player);
            return;
        }

        String resourceName = BeJavapi.getResourceName(getName());
        player.level.getProfiler().push(resourceName);

        boolean isContinuous = isContinuous();
        boolean isServer = !player.level.isClientSide;
        boolean isClientOrServer = isClientSide() || isServer;

        if (isContinuous) {
            continueTime++;

            boolean shouldEndContinuity =
                    (propsEntity.getMana() < getmanaCost() + 5 && getmanaCost() != 0); // checks if the ability should continue

            if (isClientOrServer && !isStateForced()) {
                duringContinuityEvent.duringContinuity(player, continueTime);
            }

            if (shouldEndContinuity) {
                endContinuity(player);
            }

            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), propsEntity), player);
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
