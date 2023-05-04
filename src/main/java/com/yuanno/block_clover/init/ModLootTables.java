package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModLootTables {

    @SubscribeEvent
    public static void onVanillaLootLoading(LootTableLoadEvent event)
    {
        if (event.getName().toString().equals("minecraft:entities/witch"))
        {
            TableLootEntry.Builder lootEntry = TableLootEntry.lootTableReference(new ResourceLocation(Main.MODID, "entities/inject/witch"));
            event.getTable().removePool("main");
            event.getTable().addPool(LootPool.lootPool().add(lootEntry).build());
        }
        if (event.getName().equals(LootTables.DESERT_PYRAMID) || event.getName().equals(LootTables.PIGLIN_BARTERING) || event.getName().equals(LootTables.BURIED_TREASURE))
        {
            LootPool artifacts = constructLootPool("artifacts", -2F, 1F,
                    ItemLootEntry.lootTableItem(ModItems.NIGHT_CROWN.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.LIGHT_WEIGHT_RING.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.CHANGE_MAGIC_ITEM.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.SURPLUS_MAGIC_ITEM.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.FIRE_BELT_ITEM.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.IMPATIENCE_GLOVES.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.RABBIT_FOOT.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.LUCKY_RING.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.MITTENS_REGENERATION.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.BRACELET_BRAVERY.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.CHARM_PROTECTION.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.HEALTH_ARTIFACT.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.REGEN_ARTIFACT.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.NECKLACE_AGITATION.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.CLOAK_INVISIBILITY.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.BELT_AIR.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.BRACELET_PUSH.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.RARE_CANDY.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.MANA_ARTIFACT.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.MANA_REGENERATION.get()).setWeight(1),
            ItemLootEntry.lootTableItem(ModItems.GREEN_THUMB.get()).setWeight(1));

            event.getTable().addPool(artifacts);
        }
        else if (event.getName().equals(LootTables.JUNGLE_TEMPLE) || event.getName().equals(LootTables.IGLOO_CHEST) || event.getName().equals(LootTables.BASTION_TREASURE) || event.getName().equals(LootTables.END_CITY_TREASURE))
        {
            LootPool artifacts = constructLootPool("artifacts", 1F, 2F,
                    ItemLootEntry.lootTableItem(ModItems.NIGHT_CROWN.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.LIGHT_WEIGHT_RING.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.CHANGE_MAGIC_ITEM.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.SURPLUS_MAGIC_ITEM.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.FIRE_BELT_ITEM.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.IMPATIENCE_GLOVES.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.RABBIT_FOOT.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.LUCKY_RING.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.MITTENS_REGENERATION.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.BRACELET_BRAVERY.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.CHARM_PROTECTION.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.HEALTH_ARTIFACT.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.REGEN_ARTIFACT.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.NECKLACE_AGITATION.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.CLOAK_INVISIBILITY.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.BELT_AIR.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.BRACELET_PUSH.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.RARE_CANDY.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.MANA_ARTIFACT.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.MANA_REGENERATION.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.GREEN_THUMB.get()).setWeight(1));

            event.getTable().addPool(artifacts);

        }
        else if (event.getName().equals(LootTables.ABANDONED_MINESHAFT) || event.getName().equals(LootTables.RUINED_PORTAL))
        {
            LootPool artifacts = constructLootPool("artifacts", -5F, 1F,
                    ItemLootEntry.lootTableItem(ModItems.NIGHT_CROWN.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.LIGHT_WEIGHT_RING.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.CHANGE_MAGIC_ITEM.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.SURPLUS_MAGIC_ITEM.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.FIRE_BELT_ITEM.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.IMPATIENCE_GLOVES.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.RABBIT_FOOT.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.LUCKY_RING.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.MITTENS_REGENERATION.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.BRACELET_BRAVERY.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.CHARM_PROTECTION.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.HEALTH_ARTIFACT.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.REGEN_ARTIFACT.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.NECKLACE_AGITATION.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.CLOAK_INVISIBILITY.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.BELT_AIR.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.BRACELET_PUSH.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.RARE_CANDY.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.MANA_ARTIFACT.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.MANA_REGENERATION.get()).setWeight(1),
                    ItemLootEntry.lootTableItem(ModItems.GREEN_THUMB.get()).setWeight(1));

            event.getTable().addPool(artifacts);

        }
    }

    public static LootPool constructLootPool(String name, float minRolls, float maxRolls, LootEntry.Builder<?>... lootEntries)
    {
        LootPool.Builder poolBuilder = LootPool.lootPool().name(name).setRolls(RandomValueRange.between(minRolls, maxRolls));
        if (lootEntries != null)
        {
            for (LootEntry.Builder<?> e : lootEntries)
            {
                if (e != null)
                    poolBuilder.add(e);
            }
        }
        return poolBuilder.build();
    }
}
