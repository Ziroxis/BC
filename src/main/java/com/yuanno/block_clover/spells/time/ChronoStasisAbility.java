package com.yuanno.block_clover.spells.time;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public class ChronoStasisAbility extends PunchAbility {


    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Chrono stasis", AbilityCategories.AbilityCategory.ATTRIBUTE, ChronoStasisAbility.class)
            .setDescription("Touches an entity, freezing it's time.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public ChronoStasisAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(3);
        this.onHitEntityEvent = this::onHitEvent;
    }

    private float onHitEvent(PlayerEntity player, LivingEntity target)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        if (stats.getMana() > 20 && stats.getTime() > 20)
        {
            stats.alterMana(-15);
            stats.alterExperience(15);
            stats.alterTime(-20);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), stats), player);
            if (target instanceof PlayerEntity)
            {
                PlayerEntity targetPlayer = (PlayerEntity) target;
                if (!targetPlayer.hasEffect(ModEffects.CHRONO_STASIS.get()))
                    targetPlayer.addEffect(new EffectInstance(ModEffects.CHRONO_STASIS.get(), 100, 0));
                return 1;
            }
            else
            {
                if (!target.hasEffect(ModEffects.CHRONO_STASIS.get()))
                    target.addEffect(new EffectInstance(ModEffects.CHRONO_STASIS.get(), 100, 0));
                return 1;
            }
        }
        return 0;

    }
}
