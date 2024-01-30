package com.yuanno.block_clover.spells.antimagic;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ObtainDemonStateEvent {

    @SubscribeEvent
    public static void unlockDemonState(LivingHurtEvent event)
    {
        Entity entity = event.getEntity();

        if (entity.level.isClientSide)
            return;
        if (!(entity instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) entity;
        IEntityStats statsProps = EntityStatsCapability.get(player);
        if (!statsProps.getAttribute().equals(ModValues.ANTIMAGIC))
            return;
        if (player.getHealth() > 3)
            return;
        DamageSource damageSource = event.getSource();
        if (damageSource.equals(DamageSource.CACTUS) || damageSource.equals(DamageSource.FALL) || damageSource.equals(DamageSource.DROWN) || damageSource.equals(DamageSource.SWEET_BERRY_BUSH))
            return;
        IAbilityData abilityProps = AbilityDataCapability.get(player);
        if (abilityProps.hasUnlockedAbility(DemonStateAbility.INSTANCE))
            return;

        // IF it's a player, almost death and it's not damage because of cheese (cactus, fall, drowning, bery bush) got a chance to trigger demon state and they don't have it already
        int random = BeJavapi.RNG(3);
        if (random == 0)
        {
            abilityProps.addUnlockedAbility(player, DemonStateAbility.INSTANCE);
            abilityProps.addUnlockedAbility(player,BlackSlashAbility.INSTANCE);
            abilityProps.addUnlockedAbility(player,BlackTornadoAbility.INSTANCE);
            abilityProps.setEquippedAbility(3, DemonStateAbility.INSTANCE.createAbility());
            abilityProps.setEquippedAbility(4, BlackSlashAbility.INSTANCE.createAbility());
            abilityProps.setEquippedAbility(5, BlackTornadoAbility.INSTANCE.createAbility());
            player.addEffect(new EffectInstance(Effects.ABSORPTION, 80, 6));
            player.sendMessage(new StringTextComponent("You unlocked your demon form!"), player.getUUID());
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityProps), player);

        }
    }
}
