package com.yuanno.block_clover.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class MagicWeapon extends SwordItem {
    public MagicWeapon(IItemTier itemTier, int damage, float attackSpeed, Properties properties) {
        super(itemTier, damage, attackSpeed, properties);
    }

    /*
    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker)
    {
        if (target instanceof CurseEntity && attacker instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) attacker;
            System.out.println(player);
            System.out.println(target);
            target.invulnerableTime = 0;
            float damage = this.getDamage();
            ModDamageSource source = ModDamageSource.causeAbilityDamage(player, new Ability("Cursed weapon use", AbilityCategories.AbilityCategory.ALL)).getSource();
            target.hurt(source, damage);
        }
        return super.hurtEnemy(itemStack, target, attacker);
    }
    */

    @Override
    public int getEnchantmentValue()
    {
        return 0;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

}
