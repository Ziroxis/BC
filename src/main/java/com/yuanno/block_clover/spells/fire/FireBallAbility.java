package com.yuanno.block_clover.spells.fire;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.data.IExtraUpdateData;
import com.yuanno.block_clover.entities.projectiles.fire.FireBallProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class FireBallAbility extends Ability {
    public static final Ability INSTANCE = new FireBallAbility();

    public FireBallAbility()
    {
        super("Fire Ball", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a ball of flame");
        this.setMaxCooldown(5);
        this.setmanaCost(15);
        this.setExperiencePoint(25);
        this.setExperienceGainLevelCap(10);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        FireBallProjectile projectile = new FireBallProjectile(player.level, player);
        this.alterExperience(1);
        System.out.println(this.getExperience());
        player.level.addFreshEntity(projectile);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 0.5f, 1);

        return true;
    }


}
