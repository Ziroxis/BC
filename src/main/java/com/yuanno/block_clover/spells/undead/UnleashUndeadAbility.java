package com.yuanno.block_clover.spells.undead;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class UnleashUndeadAbility extends ContinuousAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Unleash undead", AbilityCategories.AbilityCategory.ATTRIBUTE, UnleashUndeadAbility.class)
            .setDescription("Unleashes your undead army to attack enemies")
            .setDamageKind(AbilityDamageKind.MINION)
            .build();

    public UnleashUndeadAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(3);
        this.setmanaCost(0);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.duringContinuityEvent = this::duringContinuityEvent;
    }
    //TODO at the end of the ability restore the mobs -> options to only spawn one undead; add a way to add dead mobs; make em target stuff but not guild
    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        World world = player.level;
        IAbilityData abilityData = AbilityDataCapability.get(player);
        UndeadRecruitAbility undeadRecruitAbility = (UndeadRecruitAbility) abilityData.getEquippedAbility(UndeadRecruitAbility.INSTANCE);
        if (undeadRecruitAbility == null || undeadRecruitAbility.undeadArmy.isEmpty())
            return false;
        for (LivingEntity undeadEntity : undeadRecruitAbility.undeadArmy)
        {
            MobEntity mobEntity = (MobEntity) undeadEntity;
            double x = player.getX() + (world.getRandom().nextDouble() - 0.5D) * 5.0D;
            double y = player.getY();
            double z = player.getZ() + (world.getRandom().nextDouble() - 0.5D) * 5.0D;
            mobEntity.moveTo(x, y, z, player.yRot, 0.0F);
            world.addFreshEntity(mobEntity);
            mobEntity.setTarget(null);
        }
        return true;
    }

    private void duringContinuityEvent(PlayerEntity player, int i)
    {
    }
}
