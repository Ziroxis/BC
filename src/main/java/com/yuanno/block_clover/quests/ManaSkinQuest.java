package com.yuanno.block_clover.quests;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.objectives.UseContinuousAbilityObjective;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.init.ModAbilities;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncAbilityDataPacket;
import com.yuanno.block_clover.spells.misc.ManaReinforcement;
import com.yuanno.block_clover.spells.misc.ManaSkinAbility;
import net.minecraft.entity.player.PlayerEntity;

public class ManaSkinQuest extends Quest {

    private Objective useAbility = new UseContinuousAbilityObjective("Use mana reinforcements for 2 minutes while standing still", 1200, ManaReinforcement.INSTANCE, true);

    public ManaSkinQuest()
    {
        super("manaskinquest", "learning mana skin");
        this.setDescription("Use mana reinforcement for 2 minutes");
        this.setRank("");
        this.addObjective(this.useAbility);
        this.onCompleteEvent = this::giveReward;
    }


    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(player, ManaSkinAbility.INSTANCE);
        System.out.println("GOT IT");
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
