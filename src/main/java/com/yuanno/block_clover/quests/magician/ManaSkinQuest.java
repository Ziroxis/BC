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

public class ManaSkinQuest extends Quest {

    public static final QuestId INSTANCE = new QuestId.Builder("Mana skin", ManaSkinQuest::new)
            .build();

    private Objective useAbility = new UseContinuousAbilityObjective("Use mana reinforcement for 2 minutes while standing still", 1200, ManaReinforcementAbility.INSTANCE, true);

    public ManaSkinQuest(QuestId id)
    {
        super(id);
        this.addObjective(this.useAbility);
        this.setCategory(Category.MAGICIAN);
        this.onCompleteEvent = this::giveReward;
    }


    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(player, ManaSkinAbility.INSTANCE);

        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
