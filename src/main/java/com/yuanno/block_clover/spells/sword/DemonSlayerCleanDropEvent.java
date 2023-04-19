package com.yuanno.block_clover.spells.sword;

import com.yuanno.block_clover.Main;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DemonSlayerCleanDropEvent {
    /*
    @SubscribeEvent
    public static void onDemonSlayerCleanDropEvent(ItemTossEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IAbilityData abilityData = AbilityDataCapability.get(player);
        for (Ability ability : abilityData.getEquippedAbilities(AbilityCategories.AbilityCategory.ALL))
        {
            if (ability == null)
                continue;

            try {
                if (ability instanceof DemonSlayerCleanAbility && ability.isContinuous())
                {
                    if (event.getEntityItem().getItem().getItem().equals(ModItems.DEMON_SLAYER_CLEAN.get()))
                    {
                        event.getEntityItem().remove();
                        ((DemonSlayerCleanAbility) ability).stopContinuity(player);
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

     */
}
