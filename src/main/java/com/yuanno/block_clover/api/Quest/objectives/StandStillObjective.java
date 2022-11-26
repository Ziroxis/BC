package com.yuanno.block_clover.api.Quest.objectives;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.interfaces.ISurviveObjective;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class StandStillObjective extends Objective implements ISurviveObjective {
    private int timeToSurvive;
    private BlockPos blockPos;
    public StandStillObjective(String title, int seconds)
    {
        super(title);
        this.timeToSurvive = seconds;
        this.setMaxProgress(this.timeToSurvive);

        this.onStartEvent = this::onStartEvent;
    }

    private boolean onStartEvent(PlayerEntity player)
    {
        this.blockPos = player.getEntity().blockPosition();

        return true;
    }

    @Override
    public boolean checkTime(PlayerEntity player)
    {
        if (!player.getEntity().blockPosition().equals(this.blockPos))
        {
            this.setProgress(0);
            this.blockPos = player.getEntity().blockPosition();
            return false;
        }
        return true;
    }
}
