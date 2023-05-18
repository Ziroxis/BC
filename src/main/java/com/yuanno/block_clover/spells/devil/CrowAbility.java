package com.yuanno.block_clover.spells.devil;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.spells.wind.ToweringTornadoAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class CrowAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Crow", AbilityCategories.AbilityCategory.DEVIL, CrowAbility.class)
            .setDescription("Damages and locks into place the entities around you")
            .setDamageKind(AbilityDamageKind.DEBUFF)
            .build();
    public CrowAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(10);
        //this.setmanaCost(15);
        this.setExperiencePoint(15);
        this.setExperienceGainLevelCap(30);

        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        List<LivingEntity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, 12F, LivingEntity.class);
        entities.remove(player);

        entities.forEach(entityi ->
        {
            entityi.addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), 80, 0));
            entityi.hurt(ModDamageSource.causeAbilityDamage(player, this), 10);
        });

        if (player.level instanceof ServerWorld)
        {
            // TODO implement particle effect
            // ((ServerWorld) player.level).sendParticles(ParticleTypes.SPIT, player.getX(), player.getY(), player.getZ(), (int) 100, 3, 2, 3, 1);
        }

        return true;
    }
}
