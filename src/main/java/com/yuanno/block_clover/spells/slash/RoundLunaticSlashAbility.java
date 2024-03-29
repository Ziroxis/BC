package com.yuanno.block_clover.spells.slash;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class RoundLunaticSlashAbility extends Ability {

    public static final RoundLunaticSlashAbility INSTANCE = new RoundLunaticSlashAbility();

    public RoundLunaticSlashAbility()
    {
        super("Round Lunatic Slash", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Slashes around you");
        this.setmanaCost(30);
        this.setMaxCooldown(20);
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
                    List<Entity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, 10 + (float) stats.getLevel() / 2);
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