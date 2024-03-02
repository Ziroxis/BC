package com.yuanno.block_clover.spells.earth;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModDamageSource;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.earth.EarthShakeParticleEffect;
import com.yuanno.block_clover.spells.lightning.ThunderGodBootsAbility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class EarthPassiveEvent {
    private static final ParticleEffect PARTICLES = new EarthShakeParticleEffect();

    @SubscribeEvent
    public static void fallingDamage(LivingFallEvent event)
    {
        if (event.getEntityLiving().level.isClientSide)
            return;
        if (!(event.getEntityLiving() instanceof PlayerEntity))
            return;

        float distance = event.getDistance();

        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        IAbilityData abilityProps = AbilityDataCapability.get(player);
        EarthPassiveAbility earthPassiveAbility = (EarthPassiveAbility) abilityProps.getEquippedAbility(EarthPassiveAbility.INSTANCE);
        if (earthPassiveAbility == null || !((EarthPassiveAbility) earthPassiveAbility).isDisabled())
            return;

        if (distance < 3)
            return;
        PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
        IEntityStats stats = EntityStatsCapability.get(player);
        List<Entity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, 10 + (float) event.getDistance() / 2);
        if (entities.contains(player))
        {
            entities.remove(player);

            entities.forEach(entity -> {

                if (entity.isOnGround())
                {
                    entity.setDeltaMovement(0, 5, 0);
                    entity.hurt(ModDamageSource.causeAbilityDamage(player, EarthPassiveAbility.INSTANCE), 5 + (float) stats.getLevel()/4);
                    ((ServerWorld) player.level).sendParticles(ParticleTypes.WHITE_ASH, entity.getX(), entity.getY(),
                            entity.getZ(), (int) 10, 3, 3, 3, 0.1);
                }
            });
        }
        event.setDistance(0);

    }
}
