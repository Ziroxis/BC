package com.yuanno.block_clover.items.artifacts;

import com.yuanno.block_clover.items.ArtifactItem;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SUpdateHealthPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.UUID;

public class HealthArtifactItem extends ArtifactItem {
    public static UUID healthUUIDAttribute = UUID.fromString("135d510d-26c6-403e-8615-899862332e86");

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (!world.isClientSide)
        {
            float health = player.getMaxHealth();
            if (health < 40)
            {
                UUID healthUUID = healthUUIDAttribute;
                AttributeModifier attributeModifier = new AttributeModifier(healthUUID, "healthStatIncrase", health - 18, AttributeModifier.Operation.ADDITION);
                player.getAttribute(Attributes.MAX_HEALTH).removeModifier(healthUUID);
                player.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(attributeModifier);
            }
            ((ServerPlayerEntity) player).connection.send(new SUpdateHealthPacket(player.getHealth(), player.getFoodData().getFoodLevel(), player.getFoodData().getSaturationLevel()));
        }
        stack.shrink(1);
        return ActionResult.sidedSuccess(stack, world.isClientSide());
    }

    @Override
    public boolean isFoil(@Nonnull ItemStack stack) {
        return false;
    }
}
