package com.yuanno.block_clover.spells;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;
import com.yuanno.block_clover.data.ability.AbilityDataBase;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SUpdateEquippedAbilityPacket;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassiveDataAbility extends PassiveAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Passive Data", AbilityCategories.AbilityCategory.ATTRIBUTE, PassiveDataAbility.class)
            .setDescription("")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    Map<Ability, Integer> experienceMap = new HashMap<>();

    public PassiveDataAbility()
    {
        super(INSTANCE);
        //this.hideInGUI(true);
        this.duringPassiveEvent = this::duringPassiveEvent;
    }

    public void duringPassiveEvent(PlayerEntity player)
    {
        if (!player.level.isClientSide)
        {
            IAbilityData abilityData = AbilityDataCapability.get(player);
            List<Ability> abilityListUnlocked = abilityData.getUnlockedAbilities(AbilityCategories.AbilityCategory.ATTRIBUTE);
            Ability[] abilityListEquipped = abilityData.getEquippedAbilities();
            boolean containsMyAbility = false;
            for (Ability ability : abilityListUnlocked) {
                if (abilityListEquipped != null) {
                    for (Ability abilityEquip : abilityListEquipped) {
                        if (abilityEquip != null && abilityEquip.equals(ability)) {
                            containsMyAbility = true;
                            break;
                        }
                    }
                }

                // The else statement works, but it's giving the xp back when reequiped that is simply not happening
                if (abilityListEquipped != null && containsMyAbility) {
                    System.out.println(ability.getName());
                    if (experienceMap.get(ability) != null && !ability.hasBeenAssignedExperience()) // does not happen
                    {
                        ability.setExperience(experienceMap.get(ability));
                        //PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, ability), player);
                        System.out.println(experienceMap.get(ability) + " retrieved from hashmap");
                        ability.assignExperience(true);
                    } else {
                        experienceMap.put(ability, ability.getExperience()); // does happen
                        System.out.println(experienceMap.get(ability) + " put in hashmap");

                    }
                }
            }
        }
    }
}
