package com.yuanno.block_clover.spells.darkness;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.entities.summons.darkness.BlackHoleEntity;
import net.minecraft.entity.player.PlayerEntity;

public class BlackHoleAbility extends ContinuousAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Black hole", AbilityCategories.AbilityCategory.ATTRIBUTE, BlackHoleAbility.class)
            .setDescription("Spawns in a black hole following you.\nThe black hole sucks all the enemy spells near it.")
            .setDamageKind(AbilityDamageKind.GUARD)
            .build();
    private BlackHoleEntity blackHoleEntity = null;
    public BlackHoleAbility()
    {
        super(INSTANCE);
        this.setmanaCost(10);
        this.setExperiencePoint(10);
        this.setExperienceGainLevelCap(30);
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
