package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.interfaces.IEffectInstanceContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.init.ModEffects;
import net.minecraft.potion.EffectInstance;

import java.util.ArrayList;

public class WindUpAbility extends ContinuousAbility implements IEffectInstanceContinuousAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Wind Up", AbilityCategories.AbilityCategory.ATTRIBUTE, WindUpAbility.class)
            .setDescription("Give yourself speed, attack speed, dig speed, slow falling and step height")
            .build();

    public WindUpAbility()
    {
        super(INSTANCE);
        this.setExperiencePoint(5);
        this.setMaxCooldown(10);
        this.setmanaCost(5);
    }

    @Override
    public ArrayList<EffectInstance> getEffectInstances() {
        ArrayList<EffectInstance> effectInstances = new ArrayList<>();
        effectInstances.add(new EffectInstance(ModEffects.WIND_UP.get(), 40, 0, false, false));
        return effectInstances;
    }
}
