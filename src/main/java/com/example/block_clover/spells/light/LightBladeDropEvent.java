package com.example.block_clover.spells.light;

import com.example.block_clover.Main;
import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.data.ability.AbilityDataCapability;
import com.example.block_clover.data.ability.IAbilityData;
import com.example.block_clover.init.ModItems;
import com.example.block_clover.spells.antimagic.DemonSlayerAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LightBladeDropEvent {

    @SubscribeEvent
    public static void onLightSwordDropEvent(ItemTossEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IAbilityData abilityData = AbilityDataCapability.get(player);
        for (Ability ability : abilityData.getEquippedAbilities(AbilityCategories.AbilityCategory.ALL))
        {
            if (ability == null)
                continue;

            try {
                if (ability instanceof LightSwordAbility && ability.isContinuous())
                {
                    if (event.getEntityItem().getItem().getItem().equals(ModItems.LIGHT_SWORD.get()))
                    {
                        event.getEntityItem().remove();
                        ((LightSwordAbility) ability).stopContinuity(player);
                    }
                    else
                        return;
                }
                else
                    return;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
