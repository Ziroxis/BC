package com.yuanno.block_clover.quests.crank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.KillEntityObjective;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

public class KillZombieQuest extends Quest {

    public static final QuestId INSTANCE = new QuestId.Builder("Like the movies", KillZombieQuest::new)
            .build();
    private Objective killEntityObjective = new KillEntityObjective("Kill 20 zombies", 20, EntityType.ZOMBIE);

    public KillZombieQuest(QuestId questId)
    {
        super(questId);
        this.addObjectives(killEntityObjective);
        this.setDescription("Kill a few zombies");
        this.setRank(ModValues.RANK_QUEST_C);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.addYule(200);

        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        return true;
    }
}
