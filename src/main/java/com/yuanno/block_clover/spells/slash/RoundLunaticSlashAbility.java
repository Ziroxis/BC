package com.yuanno.block_clover.spells.slash;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class RoundLunaticSlashAbility extends Ability {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Round Lunatic Slash", AbilityCategories.AbilityCategory.ATTRIBUTE, RoundLunaticSlashAbility.class)
            .setDescription("Slashes around you")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    int radius = 10;
    public RoundLunaticSlashAbility()
    {
        super(INSTANCE);
        this.setmanaCost(50);
        this.setMaxCooldown(20);
        this.setEvolutionCost(100);
        this.setExperiencePoint(25);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        IAbilityData abilityProps = AbilityDataCapability.get(player);
        for (Ability ability : abilityProps.getEquippedAbilities(AbilityCategories.AbilityCategory.ALL))
        {
            if (ability == null)
                continue;

            try
            {
                if (ability instanceof SlashBladesAbility && ability.isContinuous())
                {
                    IEntityStats stats = EntityStatsCapability.get(player);
                    List<Entity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, radius + (float) stats.getLevel() / 2);
                    if (entities.contains(player)) {
                        entities.remove(player);

                        entities.forEach(entity -> {

                            entity.hurt(ModDamageSource.causeAbilityDamage(player, this), 15);
                            ((ServerWorld) player.level).sendParticles(ParticleTypes.SWEEP_ATTACK, entity.getX(), entity.getY(),
                                    entity.getZ(), (int) 10, 3, 3, 3, 0.1);
                        });
                    }
                    return true;
                }
                else
                {
                    player.sendMessage(new StringTextComponent("Need to put on your slash blades!"), Util.NIL_UUID);
                    return false;
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return false;
    }
}