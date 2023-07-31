package com.yuanno.block_clover.spells.devil;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.entities.devils.LilithDevilEntity;
import com.yuanno.block_clover.entities.devils.NahamanDevilEntity;
import com.yuanno.block_clover.entities.devils.WalgnerDevilEntity;
import com.yuanno.block_clover.init.ModEntities;
import com.yuanno.block_clover.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class DevilSummoningAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Devil Summoning", AbilityCategories.AbilityCategory.DEVIL, DevilSummoningAbility.class)
            .setDescription("You summon your innate devil trying to make him submit to you.\nIf you die during this fight you will loose everything including spells and levels.\nYou can summon your devil only once.")
            .setDamageKind(AbilityDamageKind.SUMMONING)
            .build();
    public DevilSummoningAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(10);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (!entityStats.getInnateDevil())
            return false;
        String devilname = entityStats.getDevil();
        World world = player.level;
        IAbilityData abilityData = AbilityDataCapability.get(player);

        switch (devilname)
        {
            case (ModValues.WALGNER):
                world.addFreshEntity(new WalgnerDevilEntity(ModEntities.WALGNER_DEVIL.get(), world));
                abilityData.removeUnlockedAbility(CrowAbility.INSTANCE);
                break;
            case (ModValues.NAHAMAN):
                world.addFreshEntity(new NahamanDevilEntity(ModEntities.NAHAMAN_DEVIL.get(), world));
                abilityData.removeUnlockedAbility(DarkFireBallAbility.INSTANCE);
                break;
            case (ModValues.LILITH):
                world.addFreshEntity(new LilithDevilEntity(ModEntities.LILITH_DEVIL.get(), world));
                abilityData.removeUnlockedAbility(DarkIceAbility.INSTANCE);
                break;
        }
        abilityData.removeUnlockedAbility(DevilSummoningAbility.INSTANCE);
        return true;
    }
}
