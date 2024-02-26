package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.entities.summons.earth.EarthMinionEntity;
import com.yuanno.block_clover.entities.summons.water.WaterSoldierEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class WaterSoldierAbility extends ContinuousAbility implements IParallelContinuousAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Water Soldier", AbilityCategories.AbilityCategory.ATTRIBUTE, WaterSoldierAbility.class)
            .setDescription("Creates a water soldier, attacking entities close")
            .setDamageKind(AbilityDamageKind.MINION)
            .build();

    private WaterSoldierEntity waterSoldier = null;
    private WaterSoldierEntity waterSoldier1 = null;
    private WaterSoldierEntity waterSoldier2 = null;

    public WaterSoldierAbility()
    {
        super(INSTANCE);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }
    
    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        this.waterSoldier = new WaterSoldierEntity(player.level);
        this.waterSoldier.moveTo(player.getX() + 1, player.getY(), player.getZ(), player.yRot, player.xRot);
        this.waterSoldier.setOwner(player.getUUID());
        this.waterSoldier1 = new WaterSoldierEntity(player.level);
        this.waterSoldier1.moveTo(player.getX() + 1, player.getY(), player.getZ() + 1, player.yRot, player.xRot);
        this.waterSoldier1.setOwner(player.getUUID());
        this.waterSoldier2 = new WaterSoldierEntity(player.level);
        this.waterSoldier2.moveTo(player.getX(), player.getY(), player.getZ() + 1, player.yRot, player.xRot);
        this.waterSoldier2.setOwner(player.getUUID());

        player.level.addFreshEntity(this.waterSoldier);
        player.level.addFreshEntity(this.waterSoldier1);
        player.level.addFreshEntity(this.waterSoldier2);


        return true;
    }
    
    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        this.waterSoldier.remove();
        this.waterSoldier1.remove();
        this.waterSoldier2.remove();
        int cooldown = (int) Math.round(this.continueTime / 50.0);
        this.setMaxCooldown(cooldown);

        return true;

    }
}
