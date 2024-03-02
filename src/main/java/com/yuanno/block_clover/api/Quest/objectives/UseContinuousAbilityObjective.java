package com.yuanno.block_clover.api.Quest.objectives;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.interfaces.IContinuousAbilityObjective;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;


public class UseContinuousAbilityObjective extends Objective implements IContinuousAbilityObjective {
    
    private int timer;
    private AbilityCore ability;
    private BlockPos blockPos;
    private boolean meditation;

    public UseContinuousAbilityObjective(String title, int count, AbilityCore ability, boolean meditation)
    {
        super(title);
        this.timer = count;
        this.ability = ability;
        this.meditation = meditation;
        this.setMaxProgress(this.timer);
        this.onStartEvent = this::onStartEvent;
    }

    private boolean onStartEvent(PlayerEntity player)
    {
        //this.timer = 0;
        this.blockPos = player.getEntity().blockPosition();
        return true;
    }

    @Override
    public boolean checkContinuesTimeAbility(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        Ability continuousAbility = abilityData.getEquippedAbility(this.ability);
        if (!(continuousAbility instanceof ContinuousAbility))
            return false;

        if (this.meditation)
        {
            if (continuousAbility == null || !((ContinuousAbility) continuousAbility).isContinuous() || !player.getEntity().blockPosition().equals(this.blockPos)) {
                this.setProgress(0);
                this.blockPos = player.getEntity().blockPosition();
                return false;
            }
        }
        else
        {
            if (continuousAbility == null || !((ContinuousAbility) continuousAbility).isContinuous()) {
                this.setProgress(0);
                return false;
            }
        }
        return true;
    }
}
