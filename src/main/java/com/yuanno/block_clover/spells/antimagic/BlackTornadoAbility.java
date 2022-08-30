package com.yuanno.block_clover.spells.antimagic;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.interfaces.IMultiTargetAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.anti_magic.BlackTornadoParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

public class BlackTornadoAbility extends Ability implements IMultiTargetAbility {
    public static final BlackTornadoAbility INSTANCE = new BlackTornadoAbility();
    public static final ParticleEffect PARTICLES = new BlackTornadoParticleEffect();

    public BlackTornadoAbility() {
        super("Black Tornado", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setMaxCooldown(5);
        this.setmanaCost(0);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player) {
        IEntityStats stats = EntityStatsCapability.get(player);
        if (stats.getState() == 1) {
            this.clearTargets();
            List<LivingEntity> list = Beapi.getEntitiesNear(player.blockPosition(), player.level, 12, LivingEntity.class);
            list.remove(player);
            list.stream().forEach(entity ->
            {
                if (this.isTarget(entity)) {
                    entity.hurt(ModDamageSource.causeAbilityDamage(player, this, "player").markDamageAsSlash(), 20);
                    Vector3d speed = Beapi.propulsion(player, 3, 3);
                    entity.setDeltaMovement(speed.x, 1.5, speed.z);
                    entity.hurtMarked = true;
                }
            });
            PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
            return true;
        } else {
            player.sendMessage(new StringTextComponent("Need to be in demon state!"), Util.NIL_UUID);
            return false;
        }
    }
}
        /*
        for (Ability ability : abilityProps.getEquippedAbilities(AbilityCategories.AbilityCategory.ALL))
        {
            if (ability == null)
                continue;

            try {
                if (ability instanceof DemonStateAbility && ability.isContinuous())
                {
                    List<LivingEntity> list = Beapi.getEntitiesNear(player.blockPosition(), player.level, 2.5, LivingEntity.class);
                    list.remove(player);
                    list.stream().forEach(entity ->
                    {
                        if(this.isTarget(entity))
                        {
                            entity.hurt(ModDamageSource.causeAbilityDamage(player, this, "player").markDamageAsSlash(), 20);
                            Vector3d speed = Beapi.propulsion(player, 3, 3);
                            entity.setDeltaMovement(speed.x, 1.5, speed.z);
                            entity.hurtMarked = true;
                        }
                    });
                    PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
                    return true;
                }
                else
                {
                    if (ability instanceof DemonStateAbility)
                    {
                        System.out.println("test");
                    }
                    player.sendMessage(new StringTextComponent("Need to be in demon state!"), Util.NIL_UUID);
                    return false;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

         */


