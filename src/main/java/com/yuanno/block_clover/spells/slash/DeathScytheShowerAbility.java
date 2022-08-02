package com.yuanno.block_clover.spells.slash;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.sorts.RepeaterAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.entities.projectiles.slash.DeathScytheProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class DeathScytheShowerAbility extends RepeaterAbility {
    public static final DeathScytheShowerAbility INSTANCE = new DeathScytheShowerAbility();

    public DeathScytheShowerAbility()
    {
        super("Death Scythe Shower", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a big amount of death scythes");
        this.setMaxCooldown(10);
        this.setmanaCost(20);
        this.setExperiencePoint(15);
        this.setExperienceGainLevelCap(20);
        this.setMaxRepeaterCount(7, 5);
        this.onUseEvent = this::onUseEvent;

    }

    private boolean onUseEvent(PlayerEntity player)
    {
        IAbilityData abilityProps = AbilityDataCapability.get(player);
        SlashBladesAbility slashBladesAbility = abilityProps.getEquippedAbility(SlashBladesAbility.INSTANCE);
        if (slashBladesAbility.isContinuous())
        {
            DeathScytheProjectile projectile = new DeathScytheProjectile(player.level, player);
            player.level.addFreshEntity(projectile);
            ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1.5f);
            return true;
        }
        else
        {
            player.sendMessage(new StringTextComponent("Need to put on your slash blades!"), Util.NIL_UUID);
            return false;
        }
    }
}
