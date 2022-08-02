package com.yuanno.block_clover.spells.darkness;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.entities.projectiles.darkness.AvidyaSlashProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class AvidyaSlashAbility extends Ability {

    public static final AvidyaSlashAbility INSTANCE = new AvidyaSlashAbility();

    public AvidyaSlashAbility()
    {
        super("Avidya Slash", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoot a slash of darkness");
        this.setMaxCooldown(5);
        this.setmanaCost(15);
        this.setExperiencePoint(20);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        IAbilityData abilityProps = AbilityDataCapability.get(player);
        DarkCloakedBladeAbility darkCloakedBladeAbility = abilityProps.getEquippedAbility(DarkCloakedBladeAbility.INSTANCE);
        if (darkCloakedBladeAbility.isContinuous())
        {
            AvidyaSlashProjectile projectile = new AvidyaSlashProjectile(player.level, player);
            player.level.addFreshEntity(projectile);
            ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1);
            return true;
        }
        else
        {
            player.sendMessage(new StringTextComponent("Need to cloak your sword with darkness!"), Util.NIL_UUID);
            return false;
        }
    }
}
