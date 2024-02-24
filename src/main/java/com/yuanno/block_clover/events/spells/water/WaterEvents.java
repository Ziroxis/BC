package com.yuanno.block_clover.events.spells.water;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.spells.water.CurrentOfTheFortuneRiverAbility;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class WaterEvents {

    @SubscribeEvent
    public static void onWaterWalking(LivingEvent.LivingUpdateEvent event)
    {
        if (event.getEntityLiving() instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            IAbilityData abilityData = AbilityDataCapability.get(player);
            ArrayList<Ability> unlockedAbilities = (ArrayList<Ability>) abilityData.getUnlockedAbilities();
            if (!unlockedAbilities.contains(CurrentOfTheFortuneRiverAbility.INSTANCE.createAbility()))
                return;
            if (abilityData.getUnlockedAbility(CurrentOfTheFortuneRiverAbility.INSTANCE).isPassiveEnabled())
                return;
            World world = player.level;
            BlockPos blockPos = player.blockPosition();
            if (world.getBlockState(blockPos.below()).getBlock() == Blocks.WATER && player.getY() - blockPos.below().getY() < 1.1) {
                player.setDeltaMovement(player.getDeltaMovement().x, 0, player.getDeltaMovement().z);
                player.fallDistance = 0;
                player.setOnGround(true);
            }
            /*
            if (currentOfTheFortuneRiverAbility != null && currentOfTheFortuneRiverAbility.isPassiveEnabled())
            {
                System.out.println("CALLED");
                World world = player.level;
                BlockPos blockPos = player.blockPosition();

                if (world.getBlockState(blockPos.below()).getBlock() == Blocks.WATER) {
                    player.setDeltaMovement(player.getDeltaMovement().x, 0.1, player.getDeltaMovement().z);
                }
            }

             */
        }
    }
}
