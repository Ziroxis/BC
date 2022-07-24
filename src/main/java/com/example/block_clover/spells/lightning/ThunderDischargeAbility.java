package com.example.block_clover.spells.lightning;

import com.example.block_clover.api.Beapi;
import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.example.block_clover.init.ModDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class ThunderDischargeAbility extends Ability {
    
    public static final ThunderDischargeAbility INSTANCE = new ThunderDischargeAbility();
    
    public ThunderDischargeAbility()
    {
        super("Thunder Discharge", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Discharges all your electricity!");
        this.setmanaCost(50);
        this.setMaxCooldown(25);
        this.setExperiencePoint(25);
        this.onUseEvent = this::onUseEvent;
    }
    
    public boolean onUseEvent(PlayerEntity player)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        List<Entity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, 10 + (float) stats.getLevel() / 2);
        if (entities.contains(player))
        {
            entities.remove(player);

            entities.forEach(entity -> {

                entity.hurt(ModDamageSource.causeAbilityDamage(player, this), 15);
                ((ServerWorld) player.level).sendParticles(ParticleTypes.END_ROD, entity.getX(), entity.getY(),
                        entity.getZ(), (int) 10, 3, 3, 3, 0.1);
            });
        }
        return true;
    }
}
