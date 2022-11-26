package com.yuanno.block_clover.quests;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.objectives.UseAbilityObjective;
import com.yuanno.block_clover.api.Quest.objectives.UseContinuousAbilityObjective;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncAbilityDataPacket;
import com.yuanno.block_clover.spells.misc.ManaSkinAbility;
import net.minecraft.entity.player.PlayerEntity;

public class ManaZoneQuest extends Quest {

    private Objective useAbility = new UseContinuousAbilityObjective("Use mana skin for 2 minutes while standing still", 1200, ManaSkinAbility.INSTANCE, true);

    public ManaZoneQuest()
    {
        super("manazonequest", "learning mana zone");
        this.setDescription("Use mana skin for 2 minutes");
        this.setRank("");
        this.addObjective(this.useAbility);
    }


    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        //abilityData.addUnlockedAbility(EnAbility.INSTANCE);
        System.out.println("GOT IT");
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
