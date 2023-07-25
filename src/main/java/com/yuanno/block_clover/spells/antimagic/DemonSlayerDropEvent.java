package com.yuanno.block_clover.spells.antimagic;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.items.weapons.DemonDestroyerItem;
import com.yuanno.block_clover.items.weapons.DemonDwellerItem;
import com.yuanno.block_clover.items.weapons.DemonSlayerItem;
import com.yuanno.block_clover.spells.sword.OriginalDemonSlayerAbility;
import com.yuanno.block_clover.spells.sword.OriginalMagicDestroyerAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DemonSlayerDropEvent {
    @SubscribeEvent
    public static void onDemonSlayerDropEvent(ItemTossEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IAbilityData abilityData = AbilityDataCapability.get(player);
        for (Ability ability : abilityData.getEquippedAbilities(AbilityCategories.AbilityCategory.ALL))
        {
            if (ability == null)
                continue;

            try {
                if (ability.isContinuous())
                {
                    if (event.getEntityItem().getItem().getItem() instanceof DemonSlayerItem
                            || event.getEntityItem().getItem().getItem() instanceof DemonDestroyerItem
                            || event.getEntityItem().getItem().getItem() instanceof DemonDwellerItem)
                    {
                        event.getEntityItem().remove();
                        if (ability instanceof DemonSlayerAbility)
                            ((DemonSlayerAbility) ability).stopContinuity(player);
                        else if (ability instanceof OriginalDemonSlayerAbility)
                            ((OriginalDemonSlayerAbility) ability).stopContinuity(player);
                        else if (ability instanceof OriginalMagicDestroyerAbility)
                            ((OriginalMagicDestroyerAbility) ability).stopContinuity(player);
                        else if (ability instanceof DemonDwellerAbility)
                            ((DemonDwellerAbility) ability).stopContinuity(player);
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
