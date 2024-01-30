package com.yuanno.block_clover.items.artifacts;

import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.events.levelEvents.ExperienceUpEvent;
import com.yuanno.block_clover.events.levelEvents.LevelUpEvent;
import com.yuanno.block_clover.init.ModItems;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.items.ArtifactItem;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import com.yuanno.block_clover.spells.antimagic.BullThrustAbility;
import com.yuanno.block_clover.spells.antimagic.DemonSlayerAbility;
import com.yuanno.block_clover.spells.beast.BearClawAbility;
import com.yuanno.block_clover.spells.copy.CopyAbility;
import com.yuanno.block_clover.spells.darkness.DarkCloakedBladeAbility;
import com.yuanno.block_clover.spells.earth.EarthChunkAbility;
import com.yuanno.block_clover.spells.fire.FireBallAbility;
import com.yuanno.block_clover.spells.light.LightBladeAbility;
import com.yuanno.block_clover.spells.lightning.ThunderGodBootsAbility;
import com.yuanno.block_clover.spells.mercury.MercuryBulletAbility;
import com.yuanno.block_clover.spells.sealing.SealingProjectileAbility;
import com.yuanno.block_clover.spells.slash.SlashBladesAbility;
import com.yuanno.block_clover.spells.sword.AirDashAbility;
import com.yuanno.block_clover.spells.time.TimeStealAbility;
import com.yuanno.block_clover.spells.water.WaterBallAbility;
import com.yuanno.block_clover.spells.wind.WindBladeAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;
import java.util.Map;

import static com.yuanno.block_clover.api.Beapi.randomAttributeString;


public class MagicChangeArtifactItem extends ArtifactItem {

    public MagicChangeArtifactItem()
    {
        this.artifactInformation = "This artifact makes you able to change your magic";
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (!world.isClientSide)
        {
            IEntityStats stats = EntityStatsCapability.get(player);
            IAbilityData abilityData = AbilityDataCapability.get(player);
            abilityData.getUnlockedAbilities(AbilityCategories.AbilityCategory.ATTRIBUTE);
            int idSlayer = Beapi.getIndexOfItemStack(ModItems.DEMON_SLAYER_CLEAN.get(), player.inventory);
            int idDweller = Beapi.getIndexOfItemStack(ModItems.DEMON_DWELLER.get(), player.inventory);
            int idDestroyer = Beapi.getIndexOfItemStack(ModItems.DEMON_DESTROYER.get(), player.inventory);
            if (idSlayer >= 0)
                player.inventory.getItem(idSlayer).shrink(1);
            if (idDweller >= 0)
                player.inventory.getItem(idDweller).shrink(1);
            if (idDestroyer >= 0)
                player.inventory.getItem(idDestroyer).shrink(1);
            for (Ability ability : abilityData.getUnlockedAbilities(AbilityCategories.AbilityCategory.ATTRIBUTE))
            {
                if (ability instanceof ContinuousAbility && ability.isContinuous()) {
                    ContinuousAbility continuousAbility = (ContinuousAbility) ability;
                    continuousAbility.endContinuity(player);
                }
            }
            abilityData.clearUnlockedAbilities(AbilityCategories.AbilityCategory.ATTRIBUTE);


            // Create a map to associate attributes with their corresponding abilities
            String attributeRandom = randomAttributeString();
            stats.setAttribute(attributeRandom);

            Map<String, AbilityCore> attributeAbilities = new HashMap<>();
            attributeAbilities.put(ModValues.WIND, WindBladeAbility.INSTANCE);
            attributeAbilities.put(ModValues.FIRE, FireBallAbility.INSTANCE);
            attributeAbilities.put(ModValues.LIGHT, LightBladeAbility.INSTANCE);
            attributeAbilities.put(ModValues.LIGHTNING, ThunderGodBootsAbility.INSTANCE);
            attributeAbilities.put(ModValues.DARKNESS, DarkCloakedBladeAbility.INSTANCE);
            attributeAbilities.put(ModValues.EARTH, EarthChunkAbility.INSTANCE);
            attributeAbilities.put(ModValues.SLASH, SlashBladesAbility.INSTANCE);
            attributeAbilities.put(ModValues.SEALING, SealingProjectileAbility.INSTANCE);
            attributeAbilities.put(ModValues.TIME, TimeStealAbility.INSTANCE);
            attributeAbilities.put(ModValues.WATER, WaterBallAbility.INSTANCE);
            attributeAbilities.put(ModValues.ANTIMAGIC, DemonSlayerAbility.INSTANCE);
            attributeAbilities.put(ModValues.BEAST, BearClawAbility.INSTANCE);
            attributeAbilities.put(ModValues.SWORD, AirDashAbility.INSTANCE);
            attributeAbilities.put(ModValues.COPY, CopyAbility.INSTANCE);
            attributeAbilities.put(ModValues.MERCURY, MercuryBulletAbility.INSTANCE);


// Unlock abilities based on the first attribute
            AbilityCore firstAbility = attributeAbilities.get(attributeRandom);
            if (firstAbility != null) {
                abilityData.addUnlockedAbility(player, firstAbility);
            }

// Handle Anti_Magic attribute separately
            if (attributeRandom.equals(ModValues.ANTIMAGIC)) {
                abilityData.addUnlockedAbility(player, BullThrustAbility.INSTANCE);
                stats.setRace(ModValues.HUMAN);
                stats.setMana(0);
                stats.setMaxMana(0);
            } else {
                int baseMaxMana = 50;
                float baseManaRegen = 1.0f;

                if (stats.getRace().equals(ModValues.ELF)) {
                    baseMaxMana = 60;
                    baseManaRegen = 2.0f;
                }

                int level = stats.getLevel();
                stats.setMaxMana(baseMaxMana + (level > 1 ? 5 * level : 0));
                stats.setManaRegeneration(baseManaRegen + (level > 1 ? 0.5f * level : 0));
            }

// Unlock abilities based on the second attribute
            if (stats.hasSecondAttribute()) {
                do {
                    stats.setSecondAttribute(BeJavapi.randomizer(ModValues.attributes_no_antimagic));
                } while (stats.getSecondAttribute().equals(stats.getAttribute()));

                String secondAttribute = stats.getSecondAttribute();
                AbilityCore secondAbility = attributeAbilities.get(secondAttribute);
                if (secondAbility != null) {
                    abilityData.addUnlockedAbility(player, secondAbility);
                }
            }

            ExperienceUpEvent eventExperienceUp = new ExperienceUpEvent(player, stats.getExperience());
            if (MinecraftForge.EVENT_BUS.post(eventExperienceUp))
                ActionResult.fail(stack);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), stats), player);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        }
        stack.shrink(1);
        /*
        int id = Beapi.getIndexOfItemStack(ModItems.CHANGE_MAGIC_ITEM.get().asItem(), player.inventory);
        System.out.println("item found");
        player.inventory.getItem(id).shrink(1);

         */

        return ActionResult.sidedSuccess(stack, world.isClientSide());
    }

}
