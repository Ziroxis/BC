package com.yuanno.block_clover.items.weapons;

import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModItemGroup;
import com.yuanno.block_clover.init.ModTiers;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.items.MagicWeapon;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.spells.antimagic.DemonDestroyerAbility;
import com.yuanno.block_clover.spells.antimagic.DemonDwellerAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DemonDestroyerRustyItem extends MagicWeapon {

    public DemonDestroyerRustyItem() {
        super(ModTiers.WEAPON, 7, 1f, new Properties().tab(ModItemGroup.BLOCK_CLOVER_WEAPONS).stacksTo(1));
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack itemStack, ItemUseContext context)
    {
        World world = context.getLevel();
        if (!world.isClientSide)
        {
            PlayerEntity player = context.getPlayer();
            IEntityStats entityStats = EntityStatsCapability.get(player);
            if (!entityStats.getAttribute().equals(ModValues.ANTIMAGIC))
                return super.onItemUseFirst(itemStack, context);
            IAbilityData abilityData = AbilityDataCapability.get(player);
            if (abilityData.hasUnlockedAbility(DemonDestroyerAbility.INSTANCE))
                return super.onItemUseFirst(itemStack, context);
            abilityData.addUnlockedAbility(player, DemonDwellerAbility.INSTANCE);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
            Hand hand = player.getUsedItemHand();
            ItemStack deleteItem = player.getItemInHand(hand);
            deleteItem.shrink(1);
        }
        return super.onItemUseFirst(itemStack, context);
    }
}
