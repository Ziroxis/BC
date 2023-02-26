package com.yuanno.block_clover.spells.lightning;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.RepeaterAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.entities.projectiles.lightning.LightningOrbProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class ThunderCrumblingOrbMultiAbility extends RepeaterAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Thunder Crumbling orb multi", AbilityCategories.AbilityCategory.ATTRIBUTE, ThunderCrumblingOrbMultiAbility.class)
            .setDescription("Shoots a big amount of lightning orbs.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .setDependencies(ThunderGodGlovesAbility.INSTANCE)
            .build();
    public ThunderCrumblingOrbMultiAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(12);
        this.setmanaCost(25);
        this.setExperiencePoint(25);
        this.setMaxRepeaterCount(7, 5);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        IAbilityData abilityProps = AbilityDataCapability.get(player);

            LightningOrbProjectile projectile = new LightningOrbProjectile(player.level, player);
            player.level.addFreshEntity(projectile);
            ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1.5f, 1);
            return true;
    }
}
