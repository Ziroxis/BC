package com.yuanno.block_clover.quests.magician;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.ReachLevelObjective;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.devil.DevilCapability;
import com.yuanno.block_clover.data.devil.IDevil;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.init.ModQuests;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.server.SSyncDevilPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import com.yuanno.block_clover.networking.server.SSyncQuestDataPacket;
import com.yuanno.block_clover.spells.antimagic.BullThrustAbility;
import com.yuanno.block_clover.spells.antimagic.DemonSlayerAbility;
import com.yuanno.block_clover.spells.devil.CrowAbility;
import com.yuanno.block_clover.spells.devil.DarkFireBallAbility;
import com.yuanno.block_clover.spells.devil.DarkIceAbility;
import com.yuanno.block_clover.spells.sword.OriginalDemonSlayerAbility;
import com.yuanno.block_clover.spells.sword.OriginalMagicDestroyerAbility;
import com.yuanno.block_clover.spells.sword.OriginalMagicDwellerAbility;
import com.yuanno.block_clover.spells.sword.OriginalSlashesAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;

public class GrimoireQuest extends Quest {
    public static final QuestId INSTANCE = new QuestId.Builder("Obtaining your grimoire", GrimoireQuest::new)
            .build();


    private Objective levelObjective = new ReachLevelObjective("Go to the grimoire tower and reach level 5", 5);

    public GrimoireQuest(QuestId questId)
    {
        super(questId);
        this.addObjective(this.levelObjective);
        this.setCategory(Category.MAGICIAN);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.setGrimoire(true);
        if (entityStats.getAttribute().equals(ModValues.ANTIMAGIC))
        {
            IDevil devil = DevilCapability.get(player);
            devil.alterMaxDevilMana(200);
            devil.alterDevilMana(200);
            IAbilityData abilityDataBase = AbilityDataCapability.get(player);
            abilityDataBase.addUnlockedAbility(player, DemonSlayerAbility.INSTANCE);
            abilityDataBase.addUnlockedAbility(player, BullThrustAbility.INSTANCE);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityDataBase), player);
            PacketHandler.sendTo(new SSyncDevilPacket(player.getId(), devil), player);

        }
        if (entityStats.getAttribute().equals(ModValues.SWORD))
        {
            IAbilityData abilityDataBase = AbilityDataCapability.get(player);
            abilityDataBase.addUnlockedAbility(player, OriginalDemonSlayerAbility.INSTANCE);
            abilityDataBase.addUnlockedAbility(player, OriginalMagicDestroyerAbility.INSTANCE);
            abilityDataBase.addUnlockedAbility(player, OriginalMagicDwellerAbility.INSTANCE);
            abilityDataBase.addUnlockedAbility(player, OriginalSlashesAbility.INSTANCE);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityDataBase), player);
        }
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        return true;
    }
}
