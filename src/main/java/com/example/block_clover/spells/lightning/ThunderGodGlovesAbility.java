package com.example.block_clover.spells.lightning;

import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.example.block_clover.api.ability.sorts.ContinuousAbility;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class ThunderGodGlovesAbility extends ContinuousAbility implements IParallelContinuousAbility {

    public static final ThunderGodGlovesAbility INSTANCE = new ThunderGodGlovesAbility();
    private static final AttributeModifier LIGHTNING_ATTACK = new AttributeModifier(UUID.fromString("e63c1084-fec9-11ec-b939-0242ac120002"),
            "Lightning Attack", 2, AttributeModifier.Operation.ADDITION);
    private static final AttributeModifier LIGHTNING_ATTACK_SPEED = new AttributeModifier(UUID.fromString("ead502b8-fec9-11ec-b939-0242ac120002"),
            "Lightning Attack speed", 1, AttributeModifier.Operation.ADDITION);

    public ThunderGodGlovesAbility()
    {
        super("Thunder God Gloves", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("The user gets speed by enveloping himself with lightning gloves");
        this.setMaxCooldown(30);
        this.setmanaCost(3);
        this.setExperiencePoint(10);
        this.setExperienceGainLevelCap(50);

        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        IEntityStats propsEntity = EntityStatsCapability.get(player);
        propsEntity.alterMana(-15);
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(LIGHTNING_ATTACK);
        player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(LIGHTNING_ATTACK_SPEED);
        return true;
    }
    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(LIGHTNING_ATTACK);
        player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(LIGHTNING_ATTACK_SPEED);
        return true;
    }
}
