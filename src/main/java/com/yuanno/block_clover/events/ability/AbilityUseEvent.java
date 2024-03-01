package com.yuanno.block_clover.events.ability;

import com.yuanno.block_clover.api.ability.Ability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * Server side event, handles all the menial tasks when an ability/spell is used
 */
@Cancelable
public class AbilityUseEvent extends AbilityEvent {
    public AbilityUseEvent(PlayerEntity player, Ability ability)
    {
        super(player, ability);
    }

    /**
     * Handles the mana alteration of the player
     * Called before a spell is used
     * @see com.yuanno.block_clover.events.ability.AbilityUseEvents#onUsePre
     */
    @Cancelable
    public static class Pre extends AbilityUseEvent
    {
        public Pre(PlayerEntity player, Ability ability)
        {
            super(player, ability);
        }
    }

    /**
     * Handles the charge time for chargeable abilities
     * @see com.yuanno.block_clover.api.ability.sorts.ChargeableAbility#charging
     * Handles the continual time and mana cost for abilities
     * @see com.yuanno.block_clover.api.ability.sorts.ContinuousAbility#tick
     * All handled at one place
     * @see com.yuanno.block_clover.events.ability.AbilityUseEvents#onUsePer
     * It gives multiple options to handle the mana/experience/spell, so that it can be handled differently for different abilities
     */
    @Cancelable
    public static class Per extends AbilityUseEvent
    {
        private boolean mana = true;
        private boolean experience = true;
        private boolean spell = true;
        public Per(PlayerEntity player, Ability ability)
        {
            super(player, ability);
        }
        public Per(PlayerEntity player, Ability ability, boolean mana)
        {
            super(player, ability);
            this.mana = mana;
        }
        public Per(PlayerEntity player, Ability ability, boolean mana, boolean experience)
        {
            super(player, ability);
            this.mana = mana;
            this.experience = experience;
        }
        public Per(PlayerEntity player, Ability ability, boolean mana, boolean experience, boolean spell)
        {
            super(player, ability);
            this.mana = mana;
            this.experience = experience;
            this.spell = spell;
        }
        public boolean getMana()
        {
            return this.mana;
        }
        public boolean getExperience()
        {
            return this.experience;
        }
        public boolean getSpell()
        {
            return this.spell;
        }
    }
    /**
     * Handles the player experience given to the player for using ability/spell
     * Handles the specific experience for the ability/spell itself, replacing it with a new spell if unlocked due to evolution
     * Called after a spell is used
     * @see com.yuanno.block_clover.events.ability.AbilityUseEvents#onUsePost
     */
    public static class Post extends AbilityUseEvent
    {
        public Post(PlayerEntity player, Ability ability)
        {
            super(player, ability);
        }
    }
}
