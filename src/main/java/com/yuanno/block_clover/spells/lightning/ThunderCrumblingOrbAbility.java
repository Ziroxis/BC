package com.yuanno.block_clover.spells.lightning;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.entities.projectiles.lightning.LightningOrbProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class ThunderCrumblingOrbAbility extends Ability {


    public static final ThunderCrumblingOrbAbility INSTANCE = new ThunderCrumblingOrbAbility();

    public ThunderCrumblingOrbAbility()
    {
        super("Thunder Crumbling Orb", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoot a ball of electricity");
        this.setMaxCooldown(5);
        this.setmanaCost(15);
        this.setExperiencePoint(20);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player) {
        IAbilityData abilityProps = AbilityDataCapability.get(player);
        ThunderGodGlovesAbility godGlovesAbility = abilityProps.getEquippedAbility(ThunderGodGlovesAbility.INSTANCE);
        if (godGlovesAbility != null && godGlovesAbility.isContinuous()) {
            LightningOrbProjectile projectile = new LightningOrbProjectile(player.level, player);
            player.level.addFreshEntity(projectile);
            ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1.5f, 1);
            return true;
        }
        player.sendMessage(new StringTextComponent("Need to put on your god gloves!"), Util.NIL_UUID);
        return false;
    }
}
