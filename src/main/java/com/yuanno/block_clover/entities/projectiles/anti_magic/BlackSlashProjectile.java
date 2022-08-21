package com.yuanno.block_clover.entities.projectiles.anti_magic;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class BlackSlashProjectile extends AbilityProjectileEntity {
    int knockback;
    public BlackSlashProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public BlackSlashProjectile(World world, LivingEntity player)
    {
        super(AntiMagicProjectiles.BLACK_SLASH.get(), world, player);
        this.setDamage(5);
        this.setMaxLife(32);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;
    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        knockback = 2;
        Vector3d vector3d = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale((double)this.knockback);
        entity.push(vector3d.x, 0.1, vector3d.z);
        if (entity instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) entity;
            IAbilityData abilityData = AbilityDataCapability.get(player);
            IEntityStats entityStats = EntityStatsCapability.get(player);
            if (entityStats.getMana() > 25)
                entityStats.alterMana(-25);

            for (Ability ability : abilityData.getEquippedAbilities(AbilityCategories.AbilityCategory.ALL))
            {
                if (ability == null)
                    continue;

                try
                {
                    if (ability.isContinuous())
                    {
                        ability.startCooldown(player);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
