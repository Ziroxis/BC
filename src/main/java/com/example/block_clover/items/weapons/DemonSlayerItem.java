package com.example.block_clover.items.weapons;

import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.example.block_clover.init.ModItemGroup;
import com.example.block_clover.init.ModTiers;
import com.example.block_clover.items.MagicWeapon;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DemonSlayerItem extends MagicWeapon {

    public DemonSlayerItem() {
        super(ModTiers.WEAPON, 7, 0.1f, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_WEAPONS).stacksTo(1));
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker)
    {
        if (target instanceof PlayerEntity)
        {
            IEntityStats statsTarget = EntityStatsCapability.get(target);
            IEntityStats statsAttacker = EntityStatsCapability.get(attacker);
            float mana = statsTarget.getMana();
            if (mana >= 5 + ((float) statsAttacker.getLevel()/2))
            {
                statsTarget.alterMana(-(5 + ((float) statsAttacker.getLevel()/2)));
            }
        }
        return super.hurtEnemy(itemStack, target, attacker);
    }
}
