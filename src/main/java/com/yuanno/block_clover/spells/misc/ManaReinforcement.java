package com.yuanno.block_clover.spells.misc;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.init.ModAttributes;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SUpdateEquippedAbilityPacket;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class ManaReinforcement extends ContinuousAbility implements IParallelContinuousAbility {

    public static final ManaReinforcement INSTANCE = new ManaReinforcement();
    private static final AttributeModifier MANA_REINFORCEMENT = new AttributeModifier(UUID.fromString("e6499c7c-724b-11ed-a1eb-0242ac120002"),
            "Mana Reinforcement", 5, AttributeModifier.Operation.ADDITION);

    public ManaReinforcement()
    {
        super("Mana Reinforcement", AbilityCategories.AbilityCategory.MISCELLANEOUS);
        this.setDescription("Envelops your arm with mana.\nGiving you damage");
        this.setmanaCost(8);
        this.setMaxCooldown(10);
        this.setExperiencePoint(0);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(MANA_REINFORCEMENT);
        PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
        return true;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(MANA_REINFORCEMENT);
        return true;
    }
}
