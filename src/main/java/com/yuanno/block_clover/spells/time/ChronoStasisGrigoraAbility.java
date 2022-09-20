package com.yuanno.block_clover.spells.time;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.List;

public class ChronoStasisGrigoraAbility extends Ability {

    public static final Ability INSTANCE = new ChronoStasisGrigoraAbility();

    public ChronoStasisGrigoraAbility()
    {
        super("Chrono stasis grigora", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Consumes time, freezing the time of every entity around you");
        this.setMaxCooldown(10);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (entityStats.getTime() > 50)
        {
            entityStats.alterTime(-50);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
            List<Entity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, 16);
            if (entities.contains(player))
            {
                entities.remove(player);
                entities.forEach(entity -> {
                    entity.setDeltaMovement(0, 0, 0);
                    if (entity instanceof AbilityProjectileEntity)
                    {
                        entity.move(MoverType.SELF, new Vector3d(0, 0, 0));
                    }
                    else if (entity instanceof LivingEntity)
                    {
                        LivingEntity livingEntity = (LivingEntity) entity;
                        if (!livingEntity.hasEffect(ModEffects.MOVEMENT_BLOCKED.get()))
                            livingEntity.addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), 100, 0));
                    }
                });
            }

            return true;
        }
        return false;
    }
}
