package com.yuanno.block_clover.quests;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.objectives.ReachLevelObjective;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import com.yuanno.block_clover.spells.devil.CrowAbility;
import com.yuanno.block_clover.spells.devil.DarkFireBallAbility;
import com.yuanno.block_clover.spells.devil.DarkIceAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;

public class GrimoireQuest extends Quest {

    private Objective levelObjective = new ReachLevelObjective("Obtaining a grimoire (reach level 5)", 5);

    public GrimoireQuest()
    {
        super("grimoirequest", "Obtaining your grimoire");
        this.setDescription("Reach level 5 to obtain your grimoire");
        this.setRank("");
        this.addObjective(this.levelObjective);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.setGrimoire(true);
        if (entityStats.getInnateDevil()) {
            player.sendMessage(new StringTextComponent("You feel an evil aura from your grimoire"), Util.NIL_UUID);
            IAbilityData abilityDataBase = AbilityDataCapability.get(player);
            switch (entityStats.getDevil())
            {
                case (ModValues.LILITH):
                    abilityDataBase.addUnlockedAbility(player, DarkIceAbility.INSTANCE);
                    break;
                case (ModValues.NAHAMAN):
                    abilityDataBase.addUnlockedAbility(player, DarkFireBallAbility.INSTANCE);
                    break;
                case (ModValues.WALGNER):
                    abilityDataBase.addUnlockedAbility(player, CrowAbility.INSTANCE);
                    break;
            }
            PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityDataBase));
        }
        PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
        return true;
    }
}
