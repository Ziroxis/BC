package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.entities.projectiles.fire.FireBallProjectile;
import com.yuanno.block_clover.entities.summons.darkness.BlackHoleEntity;
import com.yuanno.block_clover.entities.summons.water.WaterShieldEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class WaterShieldAbility extends Ability {
    public static final WaterShieldAbility INSTANCE = new WaterShieldAbility();
    private WaterShieldEntity waterShieldEntity = null;

    public WaterShieldAbility()
    {
        super("Water Shield", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Spawns a water shield, lingering for a few seconds blocking incoming spells");
        this.setMaxCooldown(10);
        this.setmanaCost(20);
        this.setExperiencePoint(20);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        this.waterShieldEntity = new WaterShieldEntity(player.level, player);
        waterShieldEntity.setPos(player.getX(), player.getY(), player.getZ());
        player.level.addFreshEntity(waterShieldEntity);

        return true;
    }
}
