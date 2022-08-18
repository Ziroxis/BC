package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.entities.projectiles.wind.PiercingTornadoProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class TornadoPiercingAbility extends Ability {

    public static final Ability INSTANCE = new TornadoPiercingAbility();

    public TornadoPiercingAbility()
    {
        super("Tornado Bullet", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a tornado bullet that pushes away enemies");
        this.setMaxCooldown(5);
        this.setmanaCost(15);
        this.setExperiencePoint(20);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        PiercingTornadoProjectile projectile = new PiercingTornadoProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1);

        return true;
    }
}
