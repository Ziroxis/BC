package com.example.block_clover.entities.projectiles.anti_magic;

import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.AbilityProjectileEntity;
import com.example.block_clover.data.ability.AbilityDataCapability;
import com.example.block_clover.data.ability.IAbilityData;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.example.block_clover.spells.slash.SlashBladesAbility;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlackSlashProjectile extends AbilityProjectileEntity {

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
        double knockback = 1.5;
        (entity).knockback((float)knockback * 0.5F, (double) MathHelper.sin(this.yRot * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(this.yRot * ((float)Math.PI / 180F))));

        if (entity instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) entity;
            IAbilityData abilityData = AbilityDataCapability.get(player);
            IEntityStats entityStats = EntityStatsCapability.get(player);
            entityStats.alterMana(-25);
            (player).knockback((float)knockback * 0.5F, (double) MathHelper.sin(this.yRot * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(this.yRot * ((float)Math.PI / 180F))));

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
