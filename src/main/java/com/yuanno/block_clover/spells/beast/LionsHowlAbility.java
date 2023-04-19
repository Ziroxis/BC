package com.yuanno.block_clover.spells.beast;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;

public class LionsHowlAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Lions howl", AbilityCategories.AbilityCategory.ATTRIBUTE, LionsHowlAbility.class)
            .setDescription("Howl as a unchained beast of a lion, giving nearby enemies negative effects")
            .setDamageKind(AbilityDamageKind.DEBUFF)
            .build();
    int minimumDiameter = 10;
    int durationEffects = 120;
    int effectStrenght = 0;
    boolean doDamage = false;
    public LionsHowlAbility()
    {
        super(INSTANCE);
        this.setmanaCost(30);
        this.setEvolutionCost(50);
        this.setMaxCooldown(25);
        this.setExperiencePoint(25);
    }

    public boolean onUseEvent(PlayerEntity player)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        List<Entity> entities = new ArrayList<>();
        if (this.isEvolved()) {
            minimumDiameter = 20;
            durationEffects = 180;
            effectStrenght = 1;
            doDamage = true;
        }
        entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, minimumDiameter + (float) stats.getLevel() / 2);

        //PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
        if (entities.contains(player))
        {
            entities.remove(player);

            entities.forEach(entity -> {

                if (entity instanceof LivingEntity)
                {
                    ((LivingEntity) entity).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, durationEffects, effectStrenght));
                    ((LivingEntity) entity).addEffect(new EffectInstance(Effects.BLINDNESS, durationEffects, effectStrenght));
                    ((LivingEntity) entity).addEffect(new EffectInstance(Effects.CONFUSION, durationEffects, effectStrenght));
                    ((LivingEntity) entity).addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, durationEffects, effectStrenght));

                    if (doDamage)
                        entity.hurt(ModDamageSource.causeAbilityDamage(player, this), 5);
                    ((ServerWorld) player.level).sendParticles(ParticleTypes.END_ROD, entity.getX(), entity.getY(),
                            entity.getZ(), (int) 10, 3, 3, 3, 0.1);
                }            });
        }
        return true;
    }
}
