package com.yuanno.block_clover.spells.fire;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.data.IExtraUpdateData;
import com.yuanno.block_clover.entities.projectiles.fire.FireBallProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

public class FireBallAbility extends Ability implements IExtraUpdateData {
    public static final Ability INSTANCE = new FireBallAbility();
    private double experience = 0;

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
        player.level.addFreshEntity(projectile);
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 0.5f, 1);

        return true;
    }

    @Override
    public CompoundNBT getExtraData()
    {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putDouble("experience", this.experience);
        return nbt;
    }

    @Override
    public void setExtraData(CompoundNBT nbt)
    {
        this.experience = nbt.getDouble("experience");
    }
}
