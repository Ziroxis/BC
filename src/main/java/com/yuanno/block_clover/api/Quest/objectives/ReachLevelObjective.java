package com.yuanno.block_clover.api.Quest.objectives;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.interfaces.IReachLevelObjective;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;


public class ReachLevelObjective extends Objective implements IReachLevelObjective
{
	private int level;
	
	public ReachLevelObjective(String title, int level)
	{
		super(title);
		this.setMaxProgress(1);
		this.level = level;
	}

	@Override
	public boolean checkLevel(PlayerEntity player)
	{
		IEntityStats props = EntityStatsCapability.get(player);
		return props.getLevel() >= this.level;
	}

	@Override
	public String getLocalizedTitle() 
	{
		String objectiveKey = new TranslationTextComponent(String.format("quest.objective." + Main.MODID + ".%s", this.getId())).getKey();
		return new TranslationTextComponent(objectiveKey, this.level).getString();
	}


}
