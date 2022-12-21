package com.yuanno.block_clover.spells.earth;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.spells.gravity.PresenceOfTheDemonKingAbility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.vector.Vector3d;

import java.util.List;

public class EarthManaZoneAbility extends ContinuousAbility {
    public static final EarthManaZoneAbility INSTANCE = new EarthManaZoneAbility();

    public EarthManaZoneAbility()
    {
        super("Earth mana zone", AbilityCategories.AbilityCategory.DEVIL);
        this.setDescription("Increases dramatically the gravity around you.\nAll entities and players won't be able to jump.\nProjectiles will fall to the ground.");
        this.setMaxCooldown(0);
        this.setmanaCost(0);


        this.duringContinuityEvent = this::duringContinuityEvent;
    }

    private void duringContinuityEvent(PlayerEntity player, int timer)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        List<Entity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, 16 + (float) stats.getLevel() / 2);
        if (entities.contains(player)) {
            entities.remove(player);

            entities.forEach(entity -> {

                if (entity instanceof LivingEntity)
                {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    if (!livingEntity.hasEffect(ModEffects.GRAVITY.get()))
                        livingEntity.addEffect(new EffectInstance(ModEffects.GRAVITY.get(), 40, 0));
                }
            });
        }
        List<ProjectileEntity> projectiles = Beapi.getEntitiesAround(player.blockPosition(), player.level, 16 + (float) stats.getLevel()/2, ProjectileEntity.class);
        projectiles.forEach(projectileEntity -> {
            projectileEntity.move(MoverType.SELF, new Vector3d(0, -50, 0));
        });


    }

}
