package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.entities.projectiles.fire.FireBallProjectile;
import com.yuanno.block_clover.entities.summons.darkness.BlackHoleEntity;
import com.yuanno.block_clover.entities.summons.water.WaterShieldEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

public class WaterShieldAbility extends Ability {
    public static final WaterShieldAbility INSTANCE = new WaterShieldAbility();
    private WaterShieldEntity waterShieldEntity = null;

    public WaterShieldAbility()
    {
        super("Water Shield", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Spawns a water shield, lingering for a few seconds blocking incoming spells");
        this.setMaxCooldown(10);
        this.setmanaCost(30);
        this.setExperiencePoint(20);
        this.setExperienceGainLevelCap(30);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        this.waterShieldEntity = new WaterShieldEntity(player.level, player);
        Vector3d lookAngle = player.getLookAngle();
        BlockPos pos = player.blockPosition();
        double goToX = pos.getX() + lookAngle.x;
        double goToY = pos.getY() + lookAngle.y;
        double goToZ = pos.getZ() + lookAngle.z;
        waterShieldEntity.setPos(goToX, goToY, goToZ);
        /*
        waterShieldEntity.rotate(player.getDirection().getClockWise());
        Vector2f vector2f = player.getRotationVector();
        player.getDirection().getClockWise();
        waterShieldEntity.set()

         */
        /*
        if (player.getYHeadRot() > 0)
            waterShieldEntity.yRot = player.getYHeadRot() - 90;
        else
            waterShieldEntity.yRot = player.getYHeadRot() + 90;

         */

        player.level.addFreshEntity(waterShieldEntity);

        return true;
    }
}
