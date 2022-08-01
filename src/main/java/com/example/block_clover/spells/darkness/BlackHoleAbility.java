package com.example.block_clover.spells.darkness;

import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.sorts.ContinuousAbility;
import com.example.block_clover.entities.summons.darkness.BlackHoleEntity;
import net.minecraft.entity.player.PlayerEntity;

public class BlackHoleAbility extends ContinuousAbility {
    public static final BlackHoleAbility INSTANCE = new BlackHoleAbility();
    private BlackHoleEntity blackHoleEntity = null;
    public BlackHoleAbility()
    {
        super("Black Hole", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Spawns in a black hole following you.\nThe black hole sucks all the enemy spells near it.");
        this.setmanaCost(10);
        this.setExperiencePoint(10);
        this.setExperienceGainLevelCap(50);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }
    
    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        this.blackHoleEntity = new BlackHoleEntity(player.level, player);
        this.blackHoleEntity.setPos(player.getX() + 1, player.getY() + 1, player.getZ() + 1);
        player.level.addFreshEntity(this.blackHoleEntity);
        return true;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        this.blackHoleEntity.remove();
        return true;
    }
}
