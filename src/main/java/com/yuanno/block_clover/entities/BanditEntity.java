package com.yuanno.block_clover.entities;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.entities.goals.attribute.earth.EarthChargeGoal;
import com.yuanno.block_clover.entities.goals.attribute.earth.EarthChunkGoal;
import com.yuanno.block_clover.entities.goals.attribute.fire.FireBallGoal;
import com.yuanno.block_clover.entities.goals.attribute.fire.FlameRoarGoal;
import com.yuanno.block_clover.entities.goals.attribute.light.LightBladeGoal;
import com.yuanno.block_clover.entities.goals.attribute.light.LightBladeShowerGoal;
import com.yuanno.block_clover.entities.goals.attribute.wind.WindBladeGoal;
import com.yuanno.block_clover.entities.goals.attribute.wind.WindBladeShowerGoal;
import com.yuanno.block_clover.events.levelEvents.ExperienceUpEvent;
import com.yuanno.block_clover.init.ModAbilities;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import com.yuanno.block_clover.spells.earth.EarthChargeAbility;
import com.yuanno.block_clover.spells.earth.EarthChunkAbility;
import com.yuanno.block_clover.spells.fire.FireBallAbility;
import com.yuanno.block_clover.spells.fire.FlameRoarAbility;
import com.yuanno.block_clover.spells.light.LightBladeAbility;
import com.yuanno.block_clover.spells.light.LightBladeShowerAbility;
import com.yuanno.block_clover.spells.wind.WindBladeAbility;
import com.yuanno.block_clover.spells.wind.WindBladeShowerAbility;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BanditEntity extends BCentity {

    List<AbilityCore> abilityList;
    public BanditEntity(EntityType type, World world)
    {
        super(type, world);
        this.xpDrop = 20;
        this.canUseMagic = true;
        abilityList = new ArrayList<>();
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        canUseMagic = true;
        //List<AbilityCore> earthList = Arrays.asList(ModAbilities.EARTH);
        //TODO gives an error
        if (canUseMagic)
        {
            int random = Beapi.RNG(4);
            boolean randomStrength = Beapi.RNGboolean();
            switch (random)
            {
                case 0:
                    this.goalSelector.addGoal(5, new EarthChunkGoal(this));
                    abilityList.add(EarthChunkAbility.INSTANCE);
                    if (randomStrength) {
                        this.goalSelector.addGoal(4, new EarthChargeGoal(this));
                        abilityList.add(EarthChargeAbility.INSTANCE);
                    }
                    break;
                case 1:
                    this.goalSelector.addGoal(5, new FireBallGoal(this));
                    abilityList.add(FireBallAbility.INSTANCE);
                    if (randomStrength) {
                        this.goalSelector.addGoal(4, new FlameRoarGoal(this));
                        abilityList.add(FlameRoarAbility.INSTANCE);
                    }
                    break;
                case 2:
                    this.goalSelector.addGoal(5, new LightBladeGoal(this));
                    abilityList.add(LightBladeAbility.INSTANCE);
                    if (randomStrength) {
                        this.goalSelector.addGoal(4, new LightBladeShowerGoal(this));
                        abilityList.add(LightBladeShowerAbility.INSTANCE);
                    }
                    break;
                case 3:
                    this.goalSelector.addGoal(5, new WindBladeGoal(this));
                    abilityList.add(WindBladeAbility.INSTANCE);
                    if (randomStrength) {
                        this.goalSelector.addGoal(4, new WindBladeShowerGoal(this));
                        abilityList.add(WindBladeShowerAbility.INSTANCE);
                    }
                    break;
            }
        }
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1, true));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 4));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));

        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.ARMOR, 5)
                .add(Attributes.MAX_HEALTH, 15)
                .add(Attributes.FOLLOW_RANGE, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.24);

    }


    public List<AbilityCore> getAbilityList()
    {
        return abilityList;
    }
    @Override
    public boolean removeWhenFarAway(double d)
    {
        return false;
    }
}
