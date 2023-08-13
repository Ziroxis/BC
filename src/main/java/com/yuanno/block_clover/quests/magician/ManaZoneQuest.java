package com.yuanno.block_clover.quests.magician;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.UseContinuousAbilityObjective;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncAbilityDataPacket;
import com.yuanno.block_clover.spells.darkness.BlackMoonAbility;
import com.yuanno.block_clover.spells.earth.EarthManaZoneAbility;
import com.yuanno.block_clover.spells.fire.CalidusBrachiumBarrageAbility;
import com.yuanno.block_clover.spells.light.ArrowsOfJudgement;
import com.yuanno.block_clover.spells.lightning.ThunderManaZone;
import com.yuanno.block_clover.spells.misc.ManaReinforcementAbility;
import com.yuanno.block_clover.spells.misc.ManaSkinAbility;
import com.yuanno.block_clover.spells.sealing.SealingManaZoneAbility;
import com.yuanno.block_clover.spells.slash.SlashManaZone;
import com.yuanno.block_clover.spells.time.TimeMagicManaZoneAbility;
import com.yuanno.block_clover.spells.water.WaterManaZoneAbility;
import net.minecraft.entity.player.PlayerEntity;

public class ManaZoneQuest extends Quest {

    public static final QuestId INSTANCE = new QuestId.Builder("Mana zone", ManaZoneQuest::new)
            .build();

    private Objective useAbility = new UseContinuousAbilityObjective("Use mana skin for 2 minutes while standing still", 1200, ManaSkinAbility.INSTANCE, true);

    public ManaZoneQuest(QuestId id)
    {
        super(id);
        this.addObjective(this.useAbility);
        this.onCompleteEvent = this::giveReward;
    }


    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        IEntityStats entityStats = EntityStatsCapability.get(player);
        switch (entityStats.getAttribute())
        {
            case (ModValues.ANTIMAGIC):

            case (ModValues.WIND):
                abilityData.addUnlockedAbility(player, ManaReinforcementAbility.INSTANCE);
                break;
            case (ModValues.DARKNESS):
                abilityData.addUnlockedAbility(player, BlackMoonAbility.INSTANCE);
                break;
            case (ModValues.EARTH):
                abilityData.addUnlockedAbility(player, EarthManaZoneAbility.INSTANCE);
                break;
            case (ModValues.FIRE):
                abilityData.addUnlockedAbility(player, CalidusBrachiumBarrageAbility.INSTANCE);
                break;
            case (ModValues.LIGHT):
                abilityData.addUnlockedAbility(player, ArrowsOfJudgement.INSTANCE);
                break;
            case (ModValues.LIGHTNING):
                abilityData.addUnlockedAbility(player, ThunderManaZone.INSTANCE);
                break;
            case (ModValues.SLASH):
                abilityData.addUnlockedAbility(player, SlashManaZone.INSTANCE);
                break;
            case (ModValues.SEALING):
                abilityData.addUnlockedAbility(player, SealingManaZoneAbility.INSTANCE);
                break;
            case (ModValues.WATER):
                abilityData.addUnlockedAbility(player, WaterManaZoneAbility.INSTANCE);
                break;
            case (ModValues.TIME):
                abilityData.addUnlockedAbility(player, TimeMagicManaZoneAbility.INSTANCE);
                break;
        }
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
