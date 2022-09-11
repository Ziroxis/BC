package com.yuanno.block_clover.items.artifacts;

import com.yuanno.block_clover.api.curios.type.capability.ICurioItem;
import com.yuanno.block_clover.items.ArtifactItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nonnull;

public class JumpyArtifactItem extends ArtifactItem implements ICurioItem {

    boolean hasJumped = false;
    int tickCount;
    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack)
    {
        PlayerEntity player = (PlayerEntity) livingEntity;

        if (!livingEntity.getEntity().level.isClientSide)
        {
            if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_SPACE) && !player.isOnGround() && !hasJumped && player.getDeltaMovement().y < 0.07) {

                player.jumpFromGround();
                player.fallDistance = 0;
                hasJumped = true;

            } else if (player.isOnGround()) {
                hasJumped = false;
            }
        }
    }



    @Override
    public boolean isFoil(@Nonnull ItemStack stack) {
        return true;
    }
}
