package com.yuanno.block_clover.spells.fire;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.data.IExtraUpdateData;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.entities.projectiles.fire.FireBallProjectile;
import com.yuanno.block_clover.spells.PassiveDataAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;

import java.util.Optional;

public class FireBallAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Fire Ball", AbilityCategories.AbilityCategory.ATTRIBUTE, FireBallAbility.class)
            .setDescription("Shoots a ball of flame")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public int testExperience;
    //private Optional<PassiveDataAbility> passiveDataAbility = Optional.empty();
    private PassiveDataAbility passiveDataAbility;
    public FireBallAbility()
    {
        super(INSTANCE);
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
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 0.5f, 1);
        if (!player.level.isClientSide)
        {
            passiveDataAbility = AbilityDataCapability.get(player).getUnlockedAbility(PassiveDataAbility.INSTANCE);
            /*
            if (!this.passiveDataAbility.isPresent()) {
                this.passiveDataAbility = Optional.of(AbilityDataCapability.get(player).getUnlockedAbility(PassiveDataAbility.INSTANCE));
            }

             */
            this.passiveDataAbility.alterExperience(this.getName());
            /*
            this.alterExperience(1);
            this.testExperience += 1;
            this.setDisplayName("test");
            PassiveDataAbility.INSTANCE.createAbility().alterExperience(1);
            this.create().setDescription("test");

             */
            //System.out.println(PassiveDataAbility.INSTANCE.createAbility().getEnergy());
        }
        return true;
    }

}
