package com.yuanno.block_clover.items.artifacts;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.items.ArtifactItem;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class RareCandyArtifactItem extends ArtifactItem {

    public RareCandyArtifactItem()
    {
        this.artifactInformation = "A candy that gives you sudden strength";
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (!world.isClientSide)
        {
            IEntityStats stats = EntityStatsCapability.get(player);
            stats.alterLevel(1);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), stats), player);
        }
        stack.shrink(1);
        return ActionResult.sidedSuccess(stack, world.isClientSide());
    }
}
