package com.yuanno.block_clover.items;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModItemGroup;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class MagicStaffItem extends Item {

    public MagicStaffItem() {
        super(new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_MISC).rarity(Rarity.UNCOMMON).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
                    tooltip.add(new TranslationTextComponent("tooltip.block_clover.magic_staff_witch"));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            IEntityStats stats = EntityStatsCapability.get(player);
            if (stats.getRace().equals(ModValues.WITCH)) {
                    if (player.getOffhandItem().getItem().equals(this.getItem())) {
                        if (!stats.getStaffBoost()) {
                        stats.alterManaRegeneration(0.2f);
                        stats.alterMana(20f);
                        stats.setStaffBoost(true);
                            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), stats), player);
                        }
                } else {
                    if (stats.getStaffBoost()) {
                        stats.alterManaRegeneration(-0.2f);
                        stats.alterMana(-20f);
                        stats.setStaffBoost(false);
                        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), stats), player);
                    }
                }
            }
        }
    }
}

