package com.example.block_clover.spells.light;

import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.sorts.RepeaterAbility;
import com.example.block_clover.entities.projectiles.light.LightBladeProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class LightBladeShowerAbility extends RepeaterAbility {
    public static final LightBladeShowerAbility INSTANCE = new LightBladeShowerAbility();

    public LightBladeShowerAbility()
    {
        super("Light Blade Shower", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a big amount of light blades");
        this.setMaxCooldown(12);
        this.setmanaCost(25);
        this.setExperiencePoint(25);
        this.setMaxRepeaterCount(10, 4);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        LightBladeProjectile projectile = new LightBladeProjectile(player.level, player);
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 3f, 0.5f);

        return true;
    }
}
