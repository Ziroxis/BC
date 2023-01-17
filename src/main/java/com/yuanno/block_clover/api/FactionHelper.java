package com.yuanno.block_clover.api;

import com.google.common.base.Predicates;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.guild.Guild;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncWorldDataPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.UUID;
import java.util.function.Predicate;

public class FactionHelper {

    public static Predicate<Entity> getSameGroupPredicate(LivingEntity entity)
    {
        IEntityStats props = EntityStatsCapability.get(entity);


            ExtendedWorldData worldData = ExtendedWorldData.get(entity.level);
            Guild crew = worldData.getGuildWithMember(entity.getUUID());

            return (target) ->
            {
                if(target.equals(entity))
                    return true;

                Guild targetCrew = worldData.getGuildWithMember(target.getUUID());
                return crew != null && targetCrew != null && crew.equals(targetCrew);
            };
    }

    public static void sendUpdateMessageToCrew(World world, Guild crew)
    {
        ExtendedWorldData worldData = ExtendedWorldData.get(world);
        for (Guild.Member member : crew.getMembers())
        {
            PlayerEntity crewPlayer = world.getPlayerByUUID(member.getUUID());
            if (crewPlayer != null)
                PacketHandler.sendTo(new SSyncWorldDataPacket(worldData), crewPlayer);
        }
    }

    public static void sendMessageToCrew(World world, Guild crew, ITextComponent message)
    {
        for (Guild.Member member : crew.getMembers())
        {
            UUID uuid = member.getUUID();
            PlayerEntity memberPlayer = world.getPlayerByUUID(uuid);
            if (memberPlayer != null && memberPlayer.isAlive())
            {
                memberPlayer.sendMessage(message, Util.NIL_UUID);
            }
        }
    }
}
