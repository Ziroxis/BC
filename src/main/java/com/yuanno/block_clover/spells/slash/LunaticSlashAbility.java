package com.yuanno.block_clover.spells.slash;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.RepeaterAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class LunaticSlashAbility extends RepeaterAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Lunatic Slash", AbilityCategories.AbilityCategory.ATTRIBUTE, LunaticSlashAbility.class)
            .setDescription("Slashes the enemies in front of you.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .setDependencies(SlashBladesAbility.INSTANCE)
            .build();
    public LunaticSlashAbility()
    {
        super(INSTANCE);
        this.setmanaCost(15);
        this.setMaxCooldown(10);
        this.setExperiencePoint(15);
        this.setMaxRepeaterCount(4, 3);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        IAbilityData abilityProps = AbilityDataCapability.get(player);

            RayTraceResult mop = Beapi.rayTraceBlocksAndEntities(player, 5);
            List<LivingEntity> entities = Beapi.getEntitiesAround(new BlockPos(mop.getLocation()), player.level, 3, LivingEntity.class);
            if(entities.contains(player))
                entities.remove(player);

            entities.forEach(entity -> {
                entity.hurt(ModDamageSource.causeAbilityDamage(player, this), 5);
                ((ServerWorld) player.level).sendParticles(ParticleTypes.SWEEP_ATTACK, entity.getX(), entity.getY(),
                        entity.getZ(), (int) 10, 3, 3, 3, 0.1);
            });
            return true;


    }
}
