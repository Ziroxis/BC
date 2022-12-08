package com.yuanno.block_clover.events;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.spells.misc.ManaSkinAbility;
import com.yuanno.block_clover.world.biome.ModBiomes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class BiomeEvents {
    @Mod.EventBusSubscriber(modid = Main.MODID)
    public static class biomeStuff
    {
        @SubscribeEvent
        public static void enteringManaZone(LivingEvent.LivingUpdateEvent event)
        {
            if (!(event.getEntityLiving() instanceof PlayerEntity))
                return;

            PlayerEntity player = (PlayerEntity) event.getEntity();
            World world = player.level;

            if (world.isClientSide)
                return;

            IEntityStats entityStats = EntityStatsCapability.get(player);
            IAbilityData abilityData = AbilityDataCapability.get(player);
            ManaSkinAbility manaSkinAbility = abilityData.getEquippedAbility(ManaSkinAbility.INSTANCE);
            ResourceLocation biome = world.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getKey(world.getBiome(player.blockPosition()));

            if (biome.equals(ModBiomes.GRAND_MAGIC_ZONE_VOLCANO.getId()))
            {
                if (manaSkinAbility == null || !manaSkinAbility.isContinuous())
                {
                    if (player.getHealth() > 1.0F)
                        player.hurt(DamageSource.MAGIC, 1.0F);
                }
            }
        }
    }
}
