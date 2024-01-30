package com.yuanno.block_clover.entities.humanoid;

import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.entities.api.BCentity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class StrongBanditEntity extends BCentity {
    List<AbilityCore> abilityList = new ArrayList<>();

    public StrongBanditEntity(EntityType type, World world)
    {
        super(type, world);
        this.xpDrop = 200;
        this.canUseMagic = true;
        abilityList = new ArrayList<>();
        int random = BeJavapi.RNG(4);
        switch (random)
        {
            case 0:
                //earth attribute
                break;
            case 1:
                //fire attribute
                break;
            case 2:
                //wind attribute
                break;
            case 3:
                //water attribute
                break;
        }
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();

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
