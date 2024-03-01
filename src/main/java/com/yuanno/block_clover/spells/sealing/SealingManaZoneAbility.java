package com.yuanno.block_clover.spells.sealing;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

import java.util.List;

public class SealingManaZoneAbility extends ContinuousAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Sealing mana zone", AbilityCategories.AbilityCategory.ATTRIBUTE, SealingManaZoneAbility.class)
            .setDescription("Seals everyone in place rendering them unable to move.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public SealingManaZoneAbility()
    {
        super(INSTANCE);
        this.setmanaCost(0);
        this.setMaxCooldown(180);

        this.duringContinuityEvent = this::duringContinuityEvent;
    }

    private void duringContinuityEvent(PlayerEntity player, int timer)
    {
        List<LivingEntity> entities = Beapi.getEntitiesAround(player.getEntity().blockPosition(), player.level, 32);
        if (entities.contains(player))
            entities.remove(player);
        entities.forEach(livingEntity -> {
            if (!livingEntity.hasEffect(ModEffects.MOVEMENT_BLOCKED.get()))
                livingEntity.addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), 80, 0));
        });

        if (timer >= 20 * 20)
            this.endContinuity(player);
    }
}
