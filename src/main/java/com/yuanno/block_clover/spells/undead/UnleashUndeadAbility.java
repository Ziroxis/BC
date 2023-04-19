package com.yuanno.block_clover.spells.undead;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnleashUndeadAbility extends ContinuousAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Unleash undead", AbilityCategories.AbilityCategory.ATTRIBUTE, UnleashUndeadAbility.class)
            .setDescription("Unleashes your undead army to attack enemies")
            .setDamageKind(AbilityDamageKind.MINION)
            .build();
    List<MobEntity> undeadArmy = new ArrayList<>();
    public UnleashUndeadAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(3);
        this.setmanaCost(0);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.duringContinuityEvent = this::duringContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player) // spawn all entities in the undeadArmy list in the undead recruit ability -> add every mob to this list undead -> empty the original list
    {
        World world = player.level;
        IAbilityData abilityData = AbilityDataCapability.get(player);
        UndeadRecruitAbility undeadRecruitAbility = (UndeadRecruitAbility) abilityData.getEquippedAbility(UndeadRecruitAbility.INSTANCE);
        if (undeadRecruitAbility == null || undeadRecruitAbility.undeadArmy.isEmpty())
            return false;
        for (LivingEntity undeadEntity : undeadRecruitAbility.undeadArmy)
        {
            MobEntity mobEntity = (MobEntity) undeadEntity;
            double x = player.getX() + (world.getRandom().nextDouble() - 0.5D) * 5.0D;
            double y = player.getY();
            double z = player.getZ() + (world.getRandom().nextDouble() - 0.5D) * 5.0D;
            mobEntity.moveTo(x, y, z, player.yRot, 0.0F);
            world.addFreshEntity(mobEntity);
            this.undeadArmy.add(mobEntity);
            List<LivingEntity> entitiesTarget = Beapi.getEntitiesAround(mobEntity.blockPosition(), mobEntity.level, 32);
            entitiesTarget.remove(player);
            for (LivingEntity element : undeadRecruitAbility.undeadArmy)
            {
                entitiesTarget.remove(element);
            }
            if (!entitiesTarget.isEmpty())
                mobEntity.setTarget(entitiesTarget.get(0));
            else
                mobEntity.setTarget(null);
        }
        undeadRecruitAbility.undeadArmy.clear();
        return true;
    }

    private void duringContinuityEvent(PlayerEntity player, int i) // changes target if the target is empty // todo add a check for guilds
    {
        for (MobEntity livingEntity : this.undeadArmy)
        {
            if (livingEntity.getTarget() == null || !livingEntity.getTarget().isAlive())
            {
                List<LivingEntity> entitiesTarget = Beapi.getEntitiesAround(livingEntity.blockPosition(), livingEntity.level, 32);
                entitiesTarget.remove(player);
                for (LivingEntity element : this.undeadArmy)
                {
                    entitiesTarget.remove(element);
                }
                if (!entitiesTarget.isEmpty()) {
                    Random rand = new Random();
                    LivingEntity targetEntity = entitiesTarget.get(rand.nextInt(entitiesTarget.size()));
                    livingEntity.setTarget(targetEntity);
                }
                else
                    livingEntity.setTarget(null);
            }
        }
    }

    private boolean onEndContinuityEvent(PlayerEntity player) // adds all living undeads to the undead recruit list -> empties this current list
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        for (LivingEntity livingEntity : this.undeadArmy)
        {
            UndeadRecruitAbility undeadRecruitAbility = (UndeadRecruitAbility) abilityData.getEquippedAbility(UndeadRecruitAbility.INSTANCE);
            if (undeadRecruitAbility == null)
                return false;
            if (livingEntity.isAlive()) {
                MobEntity copy = (MobEntity) livingEntity;
                copy.copyPosition(livingEntity);
                copy.finalizeSpawn((ServerWorld) player.level, player.level.getCurrentDifficultyAt(copy.blockPosition()), SpawnReason.MOB_SUMMONED, null, null);
                undeadRecruitAbility.undeadArmy.add(copy);
            }
            livingEntity.remove();
        }
        this.undeadArmy.clear();
        return true;
    }
}
