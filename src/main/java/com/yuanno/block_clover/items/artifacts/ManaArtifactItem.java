package com.yuanno.block_clover.items.artifacts;

import com.yuanno.block_clover.api.curios.SlotContext;
import com.yuanno.block_clover.api.curios.type.capability.ICurioItem;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.items.ArtifactItem;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class ManaArtifactItem extends ArtifactItem implements ICurioItem {

    public ManaArtifactItem()
    {
        this.artifactInformation = "Feels like an extra pool of mana in your pocket";
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack previousStack, ItemStack currentStack)
    {
        LivingEntity livingEntity = slotContext.getWearer();
        if (livingEntity instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) livingEntity;
            if (!player.level.isClientSide)
            {
                IEntityStats stats = EntityStatsCapability.get(player);
                stats.alterMaxMana(200);
                PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), stats), player);
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack oldStack)
    {
        LivingEntity livingEntity = slotContext.getWearer();
        if (livingEntity instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) livingEntity;
            if (!player.level.isClientSide)
            {
                IEntityStats stats = EntityStatsCapability.get(player);
                stats.setMana(0);
                stats.alterMaxMana(-200);
                PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), stats), player);
            }
        }
    }
}
