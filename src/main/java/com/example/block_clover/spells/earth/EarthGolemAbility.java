package com.example.block_clover.spells.earth;

import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.example.block_clover.api.ability.sorts.ContinuousAbility;
import com.example.block_clover.entities.summons.earth.EarthGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class EarthGolemAbility extends ContinuousAbility implements IParallelContinuousAbility {
    public static final EarthGolemAbility INSTANCE = new EarthGolemAbility();
    
    private EarthGolemEntity earthGolem = null;
    
    public EarthGolemAbility()
    {
        super("Earth Golem", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Creates a living earth golem that serves you");
        this.setmanaCost(5);
        this.setExperiencePoint(4);
        this.setMaxCooldown(20);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        this.earthGolem = new EarthGolemEntity(player.level);
        this.earthGolem.moveTo(player.getX() + 1, player.getY(), player.getZ(), player.yRot, player.xRot);
        this.earthGolem.setOwner(player);

        player.level.addFreshEntity(this.earthGolem);


        return true;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        if(this.earthGolem != null)
        {
            if(player.isOnGround() && player.isCrouching())
            {
                this.earthGolem.isAggressive = !this.earthGolem.isAggressive;

                String abilityName = new TranslationTextComponent(this.getI18nKey()).getString();
                //String puppetState = new TranslationTextComponent(this.earthGolem.isAggressive ? ModI18n.ABILITY_PUPPET_STATE_AGGRESSIVE : ModI18n.ABILITY_PUPPET_STATE_DEFENSIVE).getString();
                //player.sendMessage(new TranslationTextComponent(ModI18n.ABILITY_PUPPET_STATE, abilityName, puppetState), Util.NIL_UUID);

                return false;
            }

            this.earthGolem.remove();
        }

        int cooldown = (int) Math.round(this.continueTime / 50.0);
        this.setMaxCooldown(cooldown);

        return true;

    }
}
