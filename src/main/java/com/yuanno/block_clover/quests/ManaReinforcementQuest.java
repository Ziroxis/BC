package com.yuanno.block_clover.quests;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.objectives.ReachLevelObjective;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import com.yuanno.block_clover.spells.misc.ManaReinforcement;
import com.yuanno.block_clover.spells.misc.ManaSkinAbility;
import net.minecraft.entity.player.PlayerEntity;

public class ManaReinforcementQuest extends Quest {

    private Objective levelObjective = new ReachLevelObjective("Become stronger", 10);

    public ManaReinforcementQuest()
    {
        super("manareinforcementquest", "Developping your magic");
        this.setDescription("Reach level 10 to obtain mana reinforcement");
        this.setRank("");
        this.addObjective(this.levelObjective);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(ManaReinforcement.INSTANCE);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
