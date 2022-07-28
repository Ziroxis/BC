package com.example.block_clover.items;

import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.example.block_clover.init.ModTiers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class DemonDwellerItem extends MagicWeapon {

    public DemonDwellerItem(Properties properties, int damage, float speed)
    {
        super(ModTiers.WEAPON, damage, speed, properties);
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
