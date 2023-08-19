package com.yuanno.block_clover.quests.crank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.ObtainItemObjective;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class ObtainIronQuest extends Quest {

    public static final QuestId INSTANCE = new QuestId.Builder("Going for iron", ObtainIronQuest::new)
            .build();
    private Objective objective01 = new ObtainItemObjective("Obtain 16 iron", 16, Items.IRON_INGOT.delegate);

    public ObtainIronQuest(QuestId id)
    {
        super(id);
        this.addObjectives(this.objective01);
        this.setDescription("Obtain a few iron ingots");
        this.setRank(ModValues.RANK_QUEST_C);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        if (!this.removeQuestItem(player, Items.IRON_INGOT, 16))
            return false;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.addYule(200);

        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        return true;
    }
}
