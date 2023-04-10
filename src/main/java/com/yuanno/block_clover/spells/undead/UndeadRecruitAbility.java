package com.yuanno.block_clover.spells.undead;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.PunchAbility;
import com.yuanno.block_clover.spells.copy.CopyAbility;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;

public class UndeadRecruitAbility extends PunchAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Undead recruit", AbilityCategories.AbilityCategory.ATTRIBUTE, UndeadRecruitAbility.class)
            .setDescription("Touch an undead entity, recruiting it into your army of corpses")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();

    public List<LivingEntity> undeadArmy = new ArrayList<>();

    public UndeadRecruitAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(10);
        this.setmanaCost(0);
        this.setExperiencePoint(0);
        this.onHitEntityEvent = this::onHitEntityEvent;
    }

    private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
    {
        if (!(target instanceof MobEntity && ((MobEntity) target).getMobType() == CreatureAttribute.UNDEAD)) {
            player.sendMessage(new StringTextComponent("Need to touch a corpse!"), Util.NIL_UUID);
            this.stopContinuity(player);
        }
        MobEntity undeadEntity = (MobEntity) target;
        MobEntity copy = (MobEntity) undeadEntity.getType().create(player.level);
        copy.copyPosition(undeadEntity);
        copy.finalizeSpawn((ServerWorld) player.level, player.level.getCurrentDifficultyAt(copy.blockPosition()), SpawnReason.MOB_SUMMONED, null, null);
        undeadArmy.add(copy);
        player.sendMessage(new StringTextComponent("You added the corpse to your repertoire!"), Util.NIL_UUID);
        target.remove();
        return 0;
    }
}
