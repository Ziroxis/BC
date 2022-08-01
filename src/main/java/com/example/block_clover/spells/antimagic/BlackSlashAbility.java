package com.example.block_clover.spells.antimagic;

import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.data.ability.AbilityDataCapability;
import com.example.block_clover.data.ability.IAbilityData;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.example.block_clover.entities.projectiles.anti_magic.BlackSlashProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class BlackSlashAbility extends Ability {
    public static final BlackSlashAbility INSTANCE = new BlackSlashAbility();

    public BlackSlashAbility()
    {
        super("Black Slash", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a slash of anti-magic which shoots the entity backwards\n if hit on another player, deactivates his ongoing magic and sucks out mana");
        this.setmanaCost(0);
        this.setMaxCooldown(3);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player) {
        IEntityStats stats = EntityStatsCapability.get(player);
        if (stats.getState() == 1)
        {
            BlackSlashProjectile projectile = new BlackSlashProjectile(player.level, player);
            player.level.addFreshEntity(projectile);
            ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1);
            return true;
        }
        else
        {
            player.sendMessage(new StringTextComponent("Need to be in demon state!"), Util.NIL_UUID);
            return false;

        }
    }
}
