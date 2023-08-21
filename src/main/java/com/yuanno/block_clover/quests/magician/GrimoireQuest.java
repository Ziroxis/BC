package com.yuanno.block_clover.quests.magician;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.ReachLevelObjective;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
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
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.setGrimoire(true);
        if (entityStats.getAttribute().equals(ModValues.ANTIMAGIC))
        {
            IAbilityData abilityDataBase = AbilityDataCapability.get(player);
            abilityDataBase.addUnlockedAbility(player, DemonSlayerAbility.INSTANCE);
            abilityDataBase.addUnlockedAbility(player, BullThrustAbility.INSTANCE);
            PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityDataBase));

        }
        if (entityStats.getAttribute().equals(ModValues.SWORD))
        {
            IAbilityData abilityDataBase = AbilityDataCapability.get(player);
            abilityDataBase.addUnlockedAbility(player, OriginalDemonSlayerAbility.INSTANCE);
            abilityDataBase.addUnlockedAbility(player, OriginalMagicDestroyerAbility.INSTANCE);
            abilityDataBase.addUnlockedAbility(player, OriginalMagicDwellerAbility.INSTANCE);
            abilityDataBase.addUnlockedAbility(player, OriginalSlashesAbility.INSTANCE);
            PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityDataBase));
        }
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
