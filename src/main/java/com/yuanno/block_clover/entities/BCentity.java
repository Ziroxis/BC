package com.yuanno.block_clover.entities;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.events.levelEvents.ExperienceUpEvent;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class BCentity extends CreatureEntity {
    protected int xpDrop = 0;
    public boolean canUseMagic = true;
    protected BCentity(EntityType<? extends CreatureEntity> p_i48575_1_, World p_i48575_2_) {
        super(p_i48575_1_, p_i48575_2_);
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

            props.alterExperience(this.getExperience());
            ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, props.getExperience());
            if (MinecraftForge.EVENT_BUS.post(eventExperience))
                return;
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), props), player);
        }
    }

    public int getExperience()
    {
        return xpDrop;
    }
    public boolean canUseMagic()
    {
        return canUseMagic;
    }
}
