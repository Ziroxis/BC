package com.yuanno.block_clover.quests.magician;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.api.Quest.objectives.ReachLevelObjective;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.devil.DevilCapability;
import com.yuanno.block_clover.data.devil.IDevil;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.init.ModAbilities;
import com.yuanno.block_clover.init.ModQuests;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.client.CSyncentityStatsPacket;
import com.yuanno.block_clover.networking.server.*;
import com.yuanno.block_clover.spells.antimagic.BullThrustAbility;
import com.yuanno.block_clover.spells.antimagic.DemonSlayerAbility;
import com.yuanno.block_clover.spells.beast.BeastRegenerationPassiveAbility;
import com.yuanno.block_clover.spells.darkness.AvidyaSlashAbility;
import com.yuanno.block_clover.spells.darkness.DarkCloakedBladeAbility;
import com.yuanno.block_clover.spells.devil.CrowAbility;
import com.yuanno.block_clover.spells.devil.DarkFireBallAbility;
import com.yuanno.block_clover.spells.devil.DarkIceAbility;
import com.yuanno.block_clover.spells.earth.EarthChargeAbility;
import com.yuanno.block_clover.spells.fire.FireBallAbility;
import com.yuanno.block_clover.spells.fire.FlameRoarAbility;
import com.yuanno.block_clover.spells.light.LightHealingAbility;
import com.yuanno.block_clover.spells.light.LightSwordAbility;
import com.yuanno.block_clover.spells.lightning.ThunderFiendAbility;
import com.yuanno.block_clover.spells.mercury.MercurySpearAbility;
import com.yuanno.block_clover.spells.sealing.SelfHealSealingAbility;
import com.yuanno.block_clover.spells.slash.DeathScytheAbility;
import com.yuanno.block_clover.spells.sword.OriginalDemonSlayerAbility;
import com.yuanno.block_clover.spells.sword.OriginalMagicDestroyerAbility;
import com.yuanno.block_clover.spells.sword.OriginalMagicDwellerAbility;
import com.yuanno.block_clover.spells.sword.OriginalSlashesAbility;
import com.yuanno.block_clover.spells.time.ChronoStasisAbility;
import com.yuanno.block_clover.spells.water.CurrentOfTheFortuneRiverAbility;
import com.yuanno.block_clover.spells.water.HolyFistOfLoveAbility;
import com.yuanno.block_clover.spells.water.WaterShieldAbility;
import com.yuanno.block_clover.spells.water.WaterSubstituteSpell;
import com.yuanno.block_clover.spells.water.waterball.WaterBallAbility;
import com.yuanno.block_clover.spells.wind.WindCrescentAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;

public class GrimoireQuest extends Quest {
    public static final QuestId INSTANCE = new QuestId.Builder("Obtaining your grimoire", GrimoireQuest::new)
            .build();


    private Objective levelObjective = new ReachLevelObjective("Go to the grimoire tower and reach level 5", 5);
    private static final ArrayList<Ability> waterSpells = new ArrayList<>();

    static {
        waterSpells.add(WaterSubstituteSpell.INSTANCE.createAbility());
        waterSpells.add(CurrentOfTheFortuneRiverAbility.INSTANCE.createAbility());
        waterSpells.add(HolyFistOfLoveAbility.INSTANCE.createAbility());
    }
    public GrimoireQuest(QuestId questId)
    {
        super(questId);
        this.addObjective(this.levelObjective);
        this.setCategory(Category.MAGICIAN);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.setGrimoire(true);
        IAbilityData abilityDataBase = AbilityDataCapability.get(player);
        switch (entityStats.getAttribute())
        {
            case (ModValues.ANTIMAGIC):
                IDevil devil = DevilCapability.get(player);
                devil.alterMaxDevilMana(200);
                devil.alterDevilMana(200);
                abilityDataBase.addUnlockedAbility(player, DemonSlayerAbility.INSTANCE);
                abilityDataBase.addUnlockedAbility(player, BullThrustAbility.INSTANCE);
                PacketHandler.sendTo(new SSyncDevilPacket(player.getId(), devil), player);
                break;
            case (ModValues.SWORD):
                abilityDataBase.addUnlockedAbility(player, OriginalDemonSlayerAbility.INSTANCE);
                abilityDataBase.addUnlockedAbility(player, OriginalMagicDestroyerAbility.INSTANCE);
                abilityDataBase.addUnlockedAbility(player, OriginalMagicDwellerAbility.INSTANCE);
                abilityDataBase.addUnlockedAbility(player, OriginalSlashesAbility.INSTANCE);
                break;
            case (ModValues.WATER):
                PacketHandler.sendTo(new SOpenSpellChoiceScreenPacket(waterSpells), player);
                break;
            case (ModValues.FIRE):
                abilityDataBase.addUnlockedAbility(player, FlameRoarAbility.INSTANCE);
                break;
            case (ModValues.LIGHTNING):
                abilityDataBase.addUnlockedAbility(player, ThunderFiendAbility.INSTANCE);
                break;
            case (ModValues.DARKNESS):
                abilityDataBase.addUnlockedAbility(player, AvidyaSlashAbility.INSTANCE);
                break;
            case (ModValues.EARTH):
                abilityDataBase.addUnlockedAbility(player, EarthChargeAbility.INSTANCE);
                break;
            case (ModValues.LIGHT):
                abilityDataBase.addUnlockedAbility(player, LightSwordAbility.INSTANCE);
                break;
            case (ModValues.WIND):
                abilityDataBase.addUnlockedAbility(player, WindCrescentAbility.INSTANCE);
                break;
            case (ModValues.SEALING):
                abilityDataBase.addUnlockedAbility(player, SelfHealSealingAbility.INSTANCE);
                break;
            case (ModValues.TIME):
                abilityDataBase.addUnlockedAbility(player, ChronoStasisAbility.INSTANCE);
                break;
            case (ModValues.BEAST):
                abilityDataBase.addUnlockedAbility(player, BeastRegenerationPassiveAbility.INSTANCE);
                break;
            case (ModValues.MERCURY):
                abilityDataBase.addUnlockedAbility(player, MercurySpearAbility.INSTANCE);
                break;
            case (ModValues.SLASH):
                abilityDataBase.addUnlockedAbility(player, DeathScytheAbility.INSTANCE);
                break;
        }
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityDataBase), player);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        return true;
    }
}
