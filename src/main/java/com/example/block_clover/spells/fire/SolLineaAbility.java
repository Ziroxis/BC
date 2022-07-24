package com.example.block_clover.spells.fire;

import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.entities.projectiles.fire.SolLineaProjectile;
import com.example.block_clover.entities.projectiles.wind.WindBladeProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class SolLineaAbility extends Ability {
    public static final Ability INSTANCE = new SolLineaAbility();

    public SolLineaAbility()
    {
        super("Sol Linea", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a concentrated amount of fire at one point");
        this.setMaxCooldown(20);
        this.setmanaCost(30);
        this.setExperiencePoint(30);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        SolLineaProjectile projectile = new SolLineaProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 5f, 1);

        return true;
    }
}
