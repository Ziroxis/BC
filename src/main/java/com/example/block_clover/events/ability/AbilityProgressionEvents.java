package com.example.block_clover.events.ability;

import com.example.block_clover.Main;
import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.data.ability.AbilityDataCapability;
import com.example.block_clover.data.ability.IAbilityData;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import com.example.block_clover.events.levelEvents.ExperienceUpEvent;
import com.example.block_clover.init.ModValues;
import com.example.block_clover.networking.PacketHandler;
import com.example.block_clover.networking.server.SSyncAbilityDataPacket;
import com.example.block_clover.spells.wind.WindCrescentAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityProgressionEvents {

    @SubscribeEvent
    public static void onLevelGained(ExperienceUpEvent event)
    {
        IEntityStats statsProps = EntityStatsCapability.get(event.getPlayer());

        if (statsProps.getAttribute().equals(ModValues.WIND))
        {
            gainAbility(event.getPlayer(), 3, WindCrescentAbility.INSTANCE);
        }
    }

    private static void gainAbility(PlayerEntity player, int level, Ability ability)
    {
        IEntityStats props = EntityStatsCapability.get(player);
        IAbilityData abilityProps = AbilityDataCapability.get(player);

        if (props.getLevel() >= level && !abilityProps.hasUnlockedAbility(ability) )
        {
            player.sendMessage(new StringTextComponent("You unlocked a new spell!."), player.getUUID());
            abilityProps.addUnlockedAbility(ability);
        }
        if ((props.getLevel() < level && abilityProps.hasUnlockedAbility(ability)))
            abilityProps.removeUnlockedAbility(ability);

        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityProps), player);
    }
}
