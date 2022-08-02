package com.yuanno.block_clover.spells.earth;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.entities.summons.earth.EarthMinionEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class EarthMinionAbility extends ContinuousAbility implements IParallelContinuousAbility {
    public static final EarthMinionAbility INSTANCE = new EarthMinionAbility();
    
    private EarthMinionEntity earthMinion = null;
    private EarthMinionEntity earthMinion1 = null;
    private EarthMinionEntity earthMinion2 = null;

    public EarthMinionAbility()
    {
        super("Earth Minion", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Creates a living earth minion that serves you");
        this.setmanaCost(4);
        this.setExperiencePoint(3);
        this.setMaxCooldown(15);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }
    
    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        this.earthMinion = new EarthMinionEntity(player.level);
        this.earthMinion.moveTo(player.getX() + 1, player.getY(), player.getZ(), player.yRot, player.xRot);
        this.earthMinion.setOwner(player);
        this.earthMinion1 = new EarthMinionEntity(player.level);
        this.earthMinion1.moveTo(player.getX() + 1, player.getY(), player.getZ() + 1, player.yRot, player.xRot);
        this.earthMinion1.setOwner(player);
        this.earthMinion2 = new EarthMinionEntity(player.level);
        this.earthMinion2.moveTo(player.getX(), player.getY(), player.getZ() + 1, player.yRot, player.xRot);
        this.earthMinion2.setOwner(player);

        player.level.addFreshEntity(this.earthMinion);
        player.level.addFreshEntity(this.earthMinion1);
        player.level.addFreshEntity(this.earthMinion2);


        return true;
    }
    
    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        if(this.earthMinion != null)
        {
            if(player.isOnGround() && player.isCrouching())
            {
                this.earthMinion.isAggressive = !this.earthMinion.isAggressive;

                String abilityName = new TranslationTextComponent(this.getI18nKey()).getString();
                //String puppetState = new TranslationTextComponent(this.earthMinion.isAggressive ? ModI18n.ABILITY_PUPPET_STATE_AGGRESSIVE : ModI18n.ABILITY_PUPPET_STATE_DEFENSIVE).getString();
                //player.sendMessage(new TranslationTextComponent(ModI18n.ABILITY_PUPPET_STATE, abilityName, puppetState), Util.NIL_UUID);

                return false;
            }

            this.earthMinion.remove();
            this.earthMinion1.remove();
            this.earthMinion2.remove();
        }

        int cooldown = (int) Math.round(this.continueTime / 50.0);
        this.setMaxCooldown(cooldown);

        return true;

    }
}
