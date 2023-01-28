package com.yuanno.block_clover.events;

import com.yuanno.block_clover.guild.Guild;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

public class GuildEvents extends PlayerEvent {
    private Guild guild;

    public GuildEvents(PlayerEntity player, Guild guild)
    {
        super(player);
        this.guild = guild;
    }

    public Guild getGuild()
    {
        return this.guild;
    }

    @Cancelable
    public static class Join extends GuildEvents
    {
        public Join(PlayerEntity player, Guild guild)
        {
            super(player, guild);
        }
    }

    @Cancelable
    public static class Leave extends GuildEvents
    {
        public Leave(PlayerEntity player, Guild guild)
        {
            super(player, guild);
        }
    }
}
