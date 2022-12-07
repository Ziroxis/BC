package com.yuanno.block_clover.quests;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.objectives.ReachLevelObjective;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;

public class GrimoireQuest extends Quest {

    private Objective levelObjective = new ReachLevelObjective("Obtaining a grimoire (reach level 5)", 5);

    public GrimoireQuest()
    {
        super("grimoirequest", "Obtaining your grimoire");
        this.setDescription("Reach level 5 to obtain your grimoire");
        this.setRank("");
        this.addObjective(this.levelObjective);
    }

    public boolean giveReward(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.setGrimoire(true);
        System.out.println("GOT IT");
        PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
        return true;
    }
}
