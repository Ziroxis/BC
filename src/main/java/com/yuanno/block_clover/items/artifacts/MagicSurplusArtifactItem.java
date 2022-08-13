package com.yuanno.block_clover.items.artifacts;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.events.ability.AbilityProgressionEvents;
import com.yuanno.block_clover.events.levelEvents.ExperienceUpEvent;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.items.ArtifactItem;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import com.yuanno.block_clover.spells.antimagic.BullThrustAbility;
import com.yuanno.block_clover.spells.antimagic.DemonSlayerAbility;
import com.yuanno.block_clover.spells.darkness.DarkCloakedBladeAbility;
import com.yuanno.block_clover.spells.earth.EarthChunkAbility;
import com.yuanno.block_clover.spells.fire.FireBallAbility;
import com.yuanno.block_clover.spells.light.LightBladeAbility;
import com.yuanno.block_clover.spells.lightning.ThunderGodBootsAbility;
import com.yuanno.block_clover.spells.slash.SlashBladesAbility;
import com.yuanno.block_clover.spells.wind.WindBladeAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class MagicSurplusArtifactItem extends ArtifactItem {

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!world.isClientSide)
        {
            IEntityStats stats = EntityStatsCapability.get(player);
            IAbilityData abilityData = AbilityDataCapability.get(player);

            if (stats.hasSecondAttribute() || stats.getAttribute().equals(ModValues.ANTIMAGIC))
            {
                stats.alterExperience(3000);
                ExperienceUpEvent eventExperienceUp = new ExperienceUpEvent(player, stats.getExperience());
                if (MinecraftForge.EVENT_BUS.post(eventExperienceUp))
                    ActionResult.fail(itemStack);
                AbilityProgressionEvents.onLevelGained(eventExperienceUp); //TODO give abilities with level up
            }
            else
            {
                do
                {
                    stats.setSecondAttribute(Beapi.randomizer(ModValues.attributes_no_antimagic));
                }   while (stats.getSecondAttribute().equals(stats.getAttribute()));
                String secondAttribute = stats.getSecondAttribute();
                String firstAttribute = stats.getAttribute();
                abilityData.clearUnlockedAbilities(AbilityCategories.AbilityCategory.ATTRIBUTE);
                switch (secondAttribute) {
                    case "Wind":
                        abilityData.addUnlockedAbility(WindBladeAbility.INSTANCE);
                        break;
                    case "Fire":
                        abilityData.addUnlockedAbility(FireBallAbility.INSTANCE);
                        break;
                    case "Light":
                        abilityData.addUnlockedAbility(LightBladeAbility.INSTANCE);
                        break;
                    case "Lightning":
                        abilityData.addUnlockedAbility(ThunderGodBootsAbility.INSTANCE);
                        break;
                    case "Darkness":
                        abilityData.addUnlockedAbility(DarkCloakedBladeAbility.INSTANCE);
                        break;
                    case "Earth":
                        abilityData.addUnlockedAbility(EarthChunkAbility.INSTANCE);
                        break;
                    case "Slash":
                        abilityData.addUnlockedAbility(SlashBladesAbility.INSTANCE);
                        break;
                }
                switch (firstAttribute) {
                    case "Wind":
                        abilityData.addUnlockedAbility(WindBladeAbility.INSTANCE);
                        break;
                    case "Fire":
                        abilityData.addUnlockedAbility(FireBallAbility.INSTANCE);
                        break;
                    case "Light":
                        abilityData.addUnlockedAbility(LightBladeAbility.INSTANCE);
                        break;
                    case "Lightning":
                        abilityData.addUnlockedAbility(ThunderGodBootsAbility.INSTANCE);
                        break;
                    case "Darkness":
                        abilityData.addUnlockedAbility(DarkCloakedBladeAbility.INSTANCE);
                        break;
                    case "Earth":
                        abilityData.addUnlockedAbility(EarthChunkAbility.INSTANCE);
                        break;
                    case "Slash":
                        abilityData.addUnlockedAbility(SlashBladesAbility.INSTANCE);
                        break;
                }
                stats.setLevel(1);
                stats.setMaxExperience(50);
                stats.setExperience(0);
                stats.setMana(50);
                stats.setMaxMana(50);
                ExperienceUpEvent eventExperienceUp = new ExperienceUpEvent(player, stats.getExperience());
                if (MinecraftForge.EVENT_BUS.post(eventExperienceUp))
                    ActionResult.fail(itemStack);
            }
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), stats), player);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        }
        itemStack.shrink(1);
        return ActionResult.sidedSuccess(itemStack, world.isClientSide());
    }
}