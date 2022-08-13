package com.yuanno.block_clover.items.artifacts;

import com.yuanno.block_clover.items.ArtifactItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SUpdateHealthPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class RegenArtifactItem extends ArtifactItem {

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (!world.isClientSide)
        {
            float health = player.getMaxHealth();
            player.setHealth(health);
            ((ServerPlayerEntity) player).connection.send(new SUpdateHealthPacket(player.getHealth(), player.getFoodData().getFoodLevel(), player.getFoodData().getSaturationLevel()));
        }
        stack.shrink(1);
        return ActionResult.sidedSuccess(stack, world.isClientSide());
    }
}
