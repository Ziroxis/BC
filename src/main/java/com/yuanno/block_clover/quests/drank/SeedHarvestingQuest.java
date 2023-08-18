package com.yuanno.block_clover.quests.drank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.ObtainItemObjective;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

public class SeedHarvestingQuest extends Quest {

    public static final QuestId INSTANCE = new QuestId.Builder("Starting a farm", SeedHarvestingQuest::new)
            .build();
    private Objective obtainItemObjective = new ObtainItemObjective("Obtain 32 seeds", 32, Items.WHEAT_SEEDS.delegate);

    public SeedHarvestingQuest(QuestId id)
    {
        super(id);
        this.addObjective(this.obtainItemObjective);
        this.setDescription("Obtain seeds");
        this.setRank(ModValues.RANK_QUEST_D);


    }
    public boolean giveReward(PlayerEntity player)
    {
        if (!this.removeQuestItem(player, Items.WHEAT_SEEDS, 32))
            return false;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.addYule(100);

        PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
        return true;
    }
}
