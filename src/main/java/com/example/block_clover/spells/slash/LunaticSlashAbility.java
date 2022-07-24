package com.example.block_clover.spells.slash;

import com.example.block_clover.api.Beapi;
import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.sorts.RepeaterAbility;
import com.example.block_clover.data.ability.AbilityDataCapability;
import com.example.block_clover.data.ability.IAbilityData;
import com.example.block_clover.init.ModDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class LunaticSlashAbility extends RepeaterAbility {
    public static final LunaticSlashAbility INSTANCE = new LunaticSlashAbility();

    public LunaticSlashAbility()
    {
        super("Lunatic Slash", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Slashes the enemies in front of you");
        this.setmanaCost(15);
        this.setMaxCooldown(10);
        this.setExperiencePoint(15);
        this.setMaxRepeaterCount(4, 3);
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
                    RayTraceResult mop = Beapi.rayTraceBlocksAndEntities(player, 5);
                    List<LivingEntity> entities = Beapi.getEntitiesAround(new BlockPos(mop.getLocation()), player.level, 7, LivingEntity.class);
                    if(entities.contains(player))
                        entities.remove(player);

                    entities.forEach(entity -> {
                        entity.hurt(ModDamageSource.causeAbilityDamage(player, this), 5);
                        ((ServerWorld) player.level).sendParticles(ParticleTypes.SWEEP_ATTACK, entity.getX(), entity.getY(),
                                entity.getZ(), (int) 10, 3, 3, 3, 0.1);
                    });

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
