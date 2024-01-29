package com.yuanno.block_clover.init;

import com.yuanno.block_clover.BeRegistry;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.spells.antimagic.*;
import com.yuanno.block_clover.spells.beast.*;
import com.yuanno.block_clover.spells.copy.CopyAbility;
import com.yuanno.block_clover.spells.curse.HealingCurseAbility;
import com.yuanno.block_clover.spells.curse.LifeCurseAbility;
import com.yuanno.block_clover.spells.curse.MagicCurseAbility;
import com.yuanno.block_clover.spells.curse.PoisonousCurseAbility;
import com.yuanno.block_clover.spells.darkness.*;
import com.yuanno.block_clover.spells.devil.CrowAbility;
import com.yuanno.block_clover.spells.devil.DarkFireBallAbility;
import com.yuanno.block_clover.spells.earth.*;
import com.yuanno.block_clover.spells.fire.*;
import com.yuanno.block_clover.spells.gravity.GravitySingularityAbility;
import com.yuanno.block_clover.spells.gravity.HeavyInfightingAbility;
import com.yuanno.block_clover.spells.gravity.PresenceOfTheDemonKingAbility;
import com.yuanno.block_clover.spells.devil.DarkIceAbility;
import com.yuanno.block_clover.spells.ice.IceBallAbility;
import com.yuanno.block_clover.spells.light.*;
import com.yuanno.block_clover.spells.lightning.*;
import com.yuanno.block_clover.spells.mercury.*;
import com.yuanno.block_clover.spells.misc.ManaReinforcementAbility;
import com.yuanno.block_clover.spells.misc.ManaSenseAbility;
import com.yuanno.block_clover.spells.misc.ManaSkinAbility;
import com.yuanno.block_clover.spells.sealing.*;
import com.yuanno.block_clover.spells.slash.*;
import com.yuanno.block_clover.spells.sword.*;
import com.yuanno.block_clover.spells.time.*;
import com.yuanno.block_clover.spells.undead.UndeadRecruitAbility;
import com.yuanno.block_clover.spells.undead.UnleashUndeadAbility;
import com.yuanno.block_clover.spells.water.*;
import com.yuanno.block_clover.spells.wind.*;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.Arrays;
import java.util.Objects;

public class ModAbilities {



    public static final AbilityCore[] MISC = new AbilityCore[] {ManaSkinAbility.INSTANCE, ManaReinforcementAbility.INSTANCE, ManaSenseAbility.INSTANCE};
    public static final AbilityCore[] WATER = new AbilityCore[] {WaterBallAbility.INSTANCE, WaterShieldAbility.INSTANCE, WaterDragonAbility.INSTANCE, WaterSpearAbility.INSTANCE, ValkyrieArmorAbility.INSTANCE, PointBlankDragonAbility.INSTANCE, WaterManaZoneAbility.INSTANCE};
    public static final AbilityCore[] SEALING = new AbilityCore[] {SealingProjectileAbility.INSTANCE, SelfHealSealingAbility.INSTANCE, SealingPunchAbility.INSTANCE, OtherHealSealingAbility.INSTANCE, UltimateSealAbility.INSTANCE, SealingManaZoneAbility.INSTANCE};
    public static final AbilityCore[] ANTIMAGIC = new AbilityCore[] {DemonSlayerAbility.INSTANCE, BullThrustAbility.INSTANCE, DemonStateAbility.INSTANCE, BlackSlashAbility.INSTANCE, BlackTornadoAbility.INSTANCE, AntiMagicManaZoneAbility.INSTANCE, DemonDwellerAbility.INSTANCE};
    public static final AbilityCore[] EARTH = new AbilityCore[] {EarthChunkAbility.INSTANCE, EarthChargeAbility.INSTANCE, EarthGlovesAbility.INSTANCE, EarthMinionAbility.INSTANCE, EarthManipulationAbility.INSTANCE, EarthGolemAbility.INSTANCE, EarthQuakeAbility.INSTANCE, EarthManaZoneAbility.INSTANCE};
    public static final AbilityCore[] FIRE = new AbilityCore[] {FireBallAbility.INSTANCE, FlameRoarAbility.INSTANCE, SolLineaAbility.INSTANCE, SpiralFlameAbility.INSTANCE, LeoPalmaAbility.INSTANCE, WildBurstingFlamesAbility.INSTANCE, CalidusBrachiumBarrageAbility.INSTANCE};
    public static final AbilityCore[] SLASH = new AbilityCore[] {SlashBladesAbility.INSTANCE, DeathScytheAbility.INSTANCE, DeathScytheShowerAbility.INSTANCE, ForwardThrustAbility.INSTANCE, LunaticSlashAbility.INSTANCE, RoundLunaticSlashAbility.INSTANCE, SlashManaZone.INSTANCE};
    public static final AbilityCore[] LIGHTNING = new AbilityCore[] {ThunderGodBootsAbility.INSTANCE, ThunderGodGlovesAbility.INSTANCE, ThunderCrumblingOrbAbility.INSTANCE, ThunderCrumblingOrbMultiAbility.INSTANCE, ThunderDischargeAbility.INSTANCE, ThunderFiendAbility.INSTANCE, ThunderFiendMultiAbility.INSTANCE, ThunderSlashAbility.INSTANCE, ThunderChargeAbility.INSTANCE, ThunderManaZone.INSTANCE};
    public static final AbilityCore[] LIGHT = new AbilityCore[] {LightBladeAbility.INSTANCE, LightBladeShowerAbility.INSTANCE, LightHealingAbility.INSTANCE, LightMovementAbility.INSTANCE, LightSwordAbility.INSTANCE, ArrowsOfJudgement.INSTANCE};
    public static final AbilityCore[] DARKNESS = new AbilityCore[] {DarkCloakedBladeAbility.INSTANCE, AvidyaSlashAbility.INSTANCE, AvidyaWildSlashAbility.INSTANCE, BluntStrikeAbility.INSTANCE, BlackHoleAbility.INSTANCE, BlackMoonAbility.INSTANCE};
    public static final AbilityCore[] WIND = new AbilityCore[] {WindBladeAbility.INSTANCE, WindCrescentAbility.INSTANCE, WindBladeShowerAbility.INSTANCE, ToweringTornadoAbility.INSTANCE, WindFlightAbility.INSTANCE, TornadoPiercingAbility.INSTANCE, WindGaleAbility.INSTANCE, WindManaZoneAbility.INSTANCE};
    public static final AbilityCore[] TIME = new AbilityCore[] {TimeStealAbility.INSTANCE, TimeHealAbility.INSTANCE, ChronoStasisAbility.INSTANCE, TimeHopAbility.INSTANCE, ChronoStasisGrigoraAbility.INSTANCE, TimeRessurectionAbility.INSTANCE, TimeMagicManaZoneAbility.INSTANCE};
    public static final AbilityCore[] GRAVITY = new AbilityCore[] {GravitySingularityAbility.INSTANCE, HeavyInfightingAbility.INSTANCE, PresenceOfTheDemonKingAbility.INSTANCE};
    public static final AbilityCore[] MERCURY = new AbilityCore[] {MercuryBulletAbility.INSTANCE, MercurySpearAbility.INSTANCE, MercuryBulletBarrageAbility.INSTANCE, MercuryRainAbility.INSTANCE, MercuryBubbleAbility.INSTANCE};
    public static final AbilityCore[] COPY = new AbilityCore[] {CopyAbility.INSTANCE};
    public static final AbilityCore[] BEAST = new AbilityCore[] {BearClawAbility.INSTANCE, RhinocerosArmorAbility.INSTANCE, BeastRegenerationPassiveAbility.INSTANCE, CheetaChargeAbility.INSTANCE, JaguarsHunt.INSTANCE, LionsHowlAbility.INSTANCE, HippopotamusBiteAbility.INSTANCE};
    public static final AbilityCore[] SWORD = new AbilityCore[] {OriginalMagicDwellerAbility.INSTANCE, OriginalSlashesAbility.INSTANCE, OriginalMagicDestroyerAbility.INSTANCE, OriginalDemonSlayerAbility.INSTANCE, AirDashAbility.INSTANCE};
    public static final AbilityCore[] ICE = new AbilityCore[] {IceBallAbility.INSTANCE};
    public static final AbilityCore[] UNDEAD = new AbilityCore[] {UndeadRecruitAbility.INSTANCE, UnleashUndeadAbility.INSTANCE};
    public static final AbilityCore[] CURSE = new AbilityCore[] {PoisonousCurseAbility.INSTANCE, MagicCurseAbility.INSTANCE, LifeCurseAbility.INSTANCE, HealingCurseAbility.INSTANCE};
    public static final AbilityCore[] DEVIL = new AbilityCore[] {CrowAbility.INSTANCE, DarkFireBallAbility.INSTANCE, DarkIceAbility.INSTANCE};
    private static void registerAbilities(AbilityCore[] abilities)
    {
        Arrays.stream(abilities).filter(Objects::nonNull).forEach(abl -> BeRegistry.registerAbility(abl));
    }

    public static void register(IEventBus eventBus)
    {
        registerAbilities(GRAVITY);
        registerAbilities(TIME);
        registerAbilities(WATER);
        registerAbilities(MISC);
        registerAbilities(SEALING);
        registerAbilities(ANTIMAGIC);
        registerAbilities(EARTH);
        registerAbilities(FIRE);
        registerAbilities(SLASH);
        registerAbilities(LIGHTNING);
        registerAbilities(LIGHT);
        registerAbilities(DARKNESS);
        registerAbilities(WIND);
        registerAbilities(MERCURY);
        registerAbilities(COPY);
        registerAbilities(BEAST);
        registerAbilities(SWORD);
        //registerAbilities(UNDEAD);
        registerAbilities(ICE);
        registerAbilities(CURSE);
        registerAbilities(DEVIL);
    }
}
