package com.yuanno.block_clover.spells.mercury;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.entities.summons.mercury.MercuryBubbleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class MercuryBubbleAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Mercury Bubble", AbilityCategories.AbilityCategory.ATTRIBUTE, MercuryBubbleAbility.class)
            .setDescription("Envelops you with a bubble of mercury protecting you from incoming damage")
            .setDamageKind(AbilityDamageKind.BUFF)
            .build();
    private MercuryBubbleEntity mercuryBubbleEntity = null;

    public MercuryBubbleAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(10);
        this.setmanaCost(30);
        this.setExperiencePoint(20);
        this.setExperienceGainLevelCap(30);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        this.mercuryBubbleEntity = new MercuryBubbleEntity(player.level, player);
        Vector3d lookAngle = player.getLookAngle();
        BlockPos pos = player.blockPosition();
        double goToX = pos.getX();
        double goToY = pos.getY();
        double goToZ = pos.getZ();
        mercuryBubbleEntity.setPos(goToX, goToY, goToZ);
        player.level.addFreshEntity(mercuryBubbleEntity);
        return true;
    }
}
