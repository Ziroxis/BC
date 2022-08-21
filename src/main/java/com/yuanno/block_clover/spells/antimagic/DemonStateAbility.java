package com.yuanno.block_clover.spells.antimagic;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModAttributes;
import com.yuanno.block_clover.init.ModItems;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.anti_magic.DemonStateParticleEffect;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;

import java.util.UUID;

public class DemonStateAbility extends ContinuousAbility implements IParallelContinuousAbility {

    private static final ParticleEffect PARTICLES = new DemonStateParticleEffect();

    public static final DemonStateAbility INSTANCE = new DemonStateAbility();
    public int secondsActivated = 0;
    public static final AttributeModifier ANTISTRENGTH = new AttributeModifier(UUID.fromString("416c3548-0e0a-11ed-861d-0242ac120002"),
            "Anti Strength", 1.5, AttributeModifier.Operation.ADDITION);
    public static final AttributeModifier ANTISPEED = new AttributeModifier(UUID.fromString("7df7cd42-0e0a-11ed-861d-0242ac120002"),
            "Anti Speed", 0.2, AttributeModifier.Operation.ADDITION);
    public static final AttributeModifier ANTIJUMP = new AttributeModifier(UUID.fromString("90036bb8-0e0a-11ed-861d-0242ac120002"),
            "Anti Jump", 0.5, AttributeModifier.Operation.ADDITION);
    public DemonStateAbility()
    {
        super("Demon State", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Puts the user into a state of demon for for an amount of time.\n The amount of time scales of experience.");
        this.setMaxCooldown(120);
        this.setmanaCost(0);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.duringContinuityEvent = this::duringContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        if (!(player.getMainHandItem().getItem().equals(ModItems.DEMON_SLAYER.get())))
        {
            player.sendMessage(new StringTextComponent("Need to hold an anti-magic sword!"), Util.NIL_UUID);
            return false;
        }
        secondsActivated = 0;
        IEntityStats propsEntity = EntityStatsCapability.get(player);
        propsEntity.setState(1);
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(ANTISTRENGTH);
        player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(ANTISPEED);
        player.getAttribute(ModAttributes.JUMP_HEIGHT.get()).addTransientModifier(ANTIJUMP);
        player.abilities.mayfly = true;
        if(player instanceof ServerPlayerEntity)
            ((ServerPlayerEntity)player).connection.send(new SPlayerAbilitiesPacket(player.abilities));

        return true;
    }
    private void duringContinuityEvent(PlayerEntity player, int timer)
    {
        if (player.tickCount % 20 == 0)
            secondsActivated += 1;
        if (secondsActivated >= 120)
        {
            this.stopContinuity(player);
            return;
        }
        PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
        if (!(player.getMainHandItem().getItem().equals(ModItems.DEMON_SLAYER.get())))
        {
            player.sendMessage(new StringTextComponent("Need to hold an anti-magic sword!"), Util.NIL_UUID);
            this.stopContinuity(player);
        }
    }
    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        System.out.println("END");
        IEntityStats propsEntity = EntityStatsCapability.get(player);
        propsEntity.setState(0);
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ANTISTRENGTH);
        player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(ANTISPEED);
        player.getAttribute(ModAttributes.JUMP_HEIGHT.get()).removeModifier(ANTIJUMP);
        if (!player.isCreative())
        {
            player.abilities.mayfly = false;
            player.abilities.flying = false;
            player.fallDistance = 0;
        }
        if(player instanceof ServerPlayerEntity)
            ((ServerPlayerEntity)player).connection.send(new SPlayerAbilitiesPacket(player.abilities));

        return true;
    }
}
