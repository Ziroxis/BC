package com.yuanno.block_clover.items.artifacts;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModItems;
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
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class MagicChangeArtifactItem extends ArtifactItem {

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        if (!world.isClientSide)
        {
            IEntityStats stats = EntityStatsCapability.get(player);
            IAbilityData abilityData = AbilityDataCapability.get(player);
            abilityData.clearUnlockedAbilities(AbilityCategories.AbilityCategory.ATTRIBUTE);
            stats.setLevel(1);
            stats.setMaxExperience(50);
            stats.setExperience(0);
            stats.setAttribute(Beapi.randomizer(ModValues.attributes));
            String attribute = stats.getAttribute();
            switch (attribute) {
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
                case "Anti-magic":
                    abilityData.addUnlockedAbility(DemonSlayerAbility.INSTANCE);
                    abilityData.addUnlockedAbility(BullThrustAbility.INSTANCE);
                    stats.setRace(ModValues.HUMAN);
                    break;
            }
            if (!attribute.equals(ModValues.ANTIMAGIC)) {
                stats.setMana(50);
                stats.setMaxMana(50);
            } else {
                stats.setMana(0);
                stats.setMaxMana(0);
            }
            if (stats.hasSecondAttribute()) {
                do
                {
                    stats.setSecondAttribute(Beapi.randomizer(ModValues.attributes_no_antimagic));
                }   while (stats.getSecondAttribute().equals(stats.getAttribute()));
                String secondAttribute = stats.getSecondAttribute();
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
            }
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), stats), player);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
        }
        //TODO make the item finally delete itself
        int id = Beapi.getIndexOfItemStack(ModItems.CHANGE_MAGIC_ITEM.get().asItem(), player.inventory);
        player.inventory.getItem(id).shrink(1);

        return ActionResult.sidedSuccess(new ItemStack(this), world.isClientSide());
    }

}
