package com.yuanno.block_clover.spells.light;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.init.ModItems;
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
                        ((LightSwordAbility) ability).endContinuity(player);
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
