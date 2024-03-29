package com.yuanno.block_clover.spells.sealing;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SUpdateHealthPacket;

public class SelfHealSealingAbility extends Ability {
    public static final Ability INSTANCE = new SelfHealSealingAbility();

    public SelfHealSealingAbility()
    {
        super("Self Healing Seal", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Seals your wounds, healing yourself.\nThe amount healed depends on your level.");
        this.setMaxCooldown(10);
        this.setmanaCost(25);
        this.setExperiencePoint(35);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        IEntityStats stats = EntityStatsCapability.get(player);
        player.heal(5 + ((float) stats.getLevel()/5));
        ((ServerPlayerEntity) player).connection.send(new SUpdateHealthPacket(player.getHealth(), player.getFoodData().getFoodLevel(), player.getFoodData().getSaturationLevel()));

        return true;
    }
}
