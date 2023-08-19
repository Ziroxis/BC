package com.yuanno.block_clover.quests.arank;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.BrewPotionObjective;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;

public class HealingPotionQuest extends Quest {

    public static final QuestId INSTANCE = new QuestId.Builder("We need a medic", HealingPotionQuest::new)
            .build();
    public Objective objective = new BrewPotionObjective("Brew and deliver 6 healing potions", 6, new Item[] {Items.SPLASH_POTION}, new Effect[] {Effects.HEAL});

    public HealingPotionQuest(QuestId id)
    {
        super(id);
        this.addObjective(objective);
        this.setDescription("Brew healing potions");
        this.setRank(ModValues.RANK_QUEST_A);
    }

    public boolean giveReward(PlayerEntity player)
    {
        if (!this.removeQuestItem(player, Items.POTION, 6))
            return false;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.addYule(700);

        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        return true;
    }
}
