package com.yuanno.block_clover.entities;

import com.yuanno.block_clover.api.Beapi;
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
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;


public class BanditEntity extends BCentity {

    public BanditEntity(EntityType type, World world)
    {
        super(type, world);
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        int random = Beapi.RNG(4);
        boolean randomStrength = Beapi.RNGboolean();
        switch (random)
        {
            case 0:
                this.goalSelector.addGoal(5, new EarthChunkGoal(this));
                if (randomStrength)
                    this.goalSelector.addGoal(4, new EarthChargeGoal(this));
                break;
            case 1:
                this.goalSelector.addGoal(5, new FireBallGoal(this));
                if (randomStrength)
                    this.goalSelector.addGoal(4, new FlameRoarGoal(this));
                break;
            case 2:
                this.goalSelector.addGoal(5, new LightBladeGoal(this));
                if (randomStrength)
                    this.goalSelector.addGoal(4, new LightBladeShowerGoal(this));
                break;
            case 3:
                this.goalSelector.addGoal(5, new WindBladeGoal(this));
                if (randomStrength)
                    this.goalSelector.addGoal(4, new WindBladeShowerGoal(this));
                break;
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

    @Override
    public void die(DamageSource cause) {
        super.die(cause);
/*
        if (cause.getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) cause.getEntity();
            IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));

            Beapi.experienceMultiplier(player, this.magicXPDrop);

            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketMagicExpSync(playercap.returnMagicExp(), player.getId()));

        }
        */
        if (cause.getEntity() instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) cause.getEntity();
            IEntityStats props = EntityStatsCapability.get(player);

            props.alterExperience(15);
            ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, props.getExperience());
            if (MinecraftForge.EVENT_BUS.post(eventExperience))
                return;
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), props), player);
        }
    }

    @Override
    public boolean removeWhenFarAway(double d)
    {
        return false;
    }
}
