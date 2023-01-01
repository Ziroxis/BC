package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.spells.antimagic.*;
import com.yuanno.block_clover.spells.darkness.*;
import com.yuanno.block_clover.spells.earth.*;
import com.yuanno.block_clover.spells.fire.*;
import com.yuanno.block_clover.spells.gravity.GravitySingularityAbility;
import com.yuanno.block_clover.spells.gravity.HeavyInfightingAbility;
import com.yuanno.block_clover.spells.gravity.PresenceOfTheDemonKingAbility;
import com.yuanno.block_clover.spells.light.*;
import com.yuanno.block_clover.spells.lightning.*;
import com.yuanno.block_clover.spells.misc.ManaReinforcement;
import com.yuanno.block_clover.spells.misc.ManaSkinAbility;
import com.yuanno.block_clover.spells.sealing.*;
import com.yuanno.block_clover.spells.slash.*;
import com.yuanno.block_clover.spells.time.*;
import com.yuanno.block_clover.spells.water.*;
import com.yuanno.block_clover.spells.wind.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.*;

import java.util.Arrays;
import java.util.Objects;

public class ModAbilities {

    public static <T extends IForgeRegistryEntry<T>> void make(ResourceLocation name, Class<T> type)
    {
        new RegistryBuilder<T>().setName(name).setType(type).setMaxID(Integer.MAX_VALUE - 1).create();
    }
    static
    {
        make(new ResourceLocation(Main.MODID, "abilities"), Ability.class);
    }

    public static final IForgeRegistry<Ability> ABILITIES_REGISTRY = RegistryManager.ACTIVE.getRegistry(Ability.class);
    private static final DeferredRegister<Ability> ABILITIES = DeferredRegister.create(ABILITIES_REGISTRY, Main.MODID);

    public static final Ability[] MISC = {ManaSkinAbility.INSTANCE, ManaReinforcement.INSTANCE};
    public static final Ability[] WATER = {WaterBallAbility.INSTANCE, WaterShieldAbility.INSTANCE, WaterDragonAbility.INSTANCE, WaterSpearAbility.INSTANCE, ValkyrieArmorAbility.INSTANCE, PointBlankDragonAbility.INSTANCE, WaterManaZoneAbility.INSTANCE};
    public static final Ability[] SEALING = {SealingProjectileAbility.INSTANCE, SelfHealSealingAbility.INSTANCE, SealingPunchAbility.INSTANCE, OtherHealSealingAbility.INSTANCE, UltimateSealAbility.INSTANCE, SealingManaZoneAbility.INSTANCE};
    public static final Ability[] ANTIMAGIC = {DemonSlayerAbility.INSTANCE, BullThrustAbility.INSTANCE, DemonStateAbility.INSTANCE, BlackSlashAbility.INSTANCE, BlackTornadoAbility.INSTANCE, AntiMagicManaZoneAbility.INSTANCE};
    public static final Ability[] EARTH = {EarthChunkAbility.INSTANCE, EarthChargeAbility.INSTANCE, EarthGlovesAbility.INSTANCE, EarthMinionAbility.INSTANCE, EarthManipulationAbility.INSTANCE, EarthGolemAbility.INSTANCE, EarthQuakeAbility.INSTANCE, EarthPassiveAbility.INSTANCE, EarthManaZoneAbility.INSTANCE};
    public static final Ability[] FIRE = {FireBallAbility.INSTANCE, FlameRoarAbility.INSTANCE, SolLineaAbility.INSTANCE, SpiralFlameAbility.INSTANCE, LeoPalmaAbility.INSTANCE, WildBurstingFlamesAbility.INSTANCE, CalidusBrachiumBarrageAbility.INSTANCE};
    public static final Ability[] SLASH = {SlashBladesAbility.INSTANCE, DeathScytheAbility.INSTANCE, DeathScytheShowerAbility.INSTANCE, ForwardThrustAbility.INSTANCE, LunaticSlashAbility.INSTANCE, RoundLunaticSlashAbility.INSTANCE, SlashManaZone.INSTANCE};
    public static final Ability[] LIGHTNING = {ThunderGodBootsAbility.INSTANCE, ThunderGodGlovesAbility.INSTANCE, ThunderCrumblingOrbAbility.INSTANCE, ThunderCrumblingOrbMultiAbility.INSTANCE, ThunderDischargeAbility.INSTANCE, ThunderFiendAbility.INSTANCE, ThunderFiendMultiAbility.INSTANCE, ThunderSlashAbility.INSTANCE, ThunderChargeAbility.INSTANCE, ThunderManaZone.INSTANCE};
    public static final Ability[] LIGHT = {LightBladeAbility.INSTANCE, LightBladeShowerAbility.INSTANCE, LightHealingAbility.INSTANCE, LightMovementAbility.INSTANCE, LightSwordAbility.INSTANCE, ArrowsOfJudgement.INSTANCE};
    public static final Ability[] DARKNESS = {DarkCloakedBladeAbility.INSTANCE, AvidyaSlashAbility.INSTANCE, AvidyaWildSlashAbility.INSTANCE, BluntStrikeAbility.INSTANCE, BlackHoleAbility.INSTANCE, BlackMoonAbility.INSTANCE};
    public static final Ability[] WIND = {WindBladeAbility.INSTANCE, WindCrescentAbility.INSTANCE, WindBladeShowerAbility.INSTANCE, ToweringTornadoAbility.INSTANCE, WindFlightAbility.INSTANCE, TornadoPiercingAbility.INSTANCE, WindGaleAbility.INSTANCE, WindManaZoneAbility.INSTANCE};
    public static final Ability[] TIME = {TimeStealAbility.INSTANCE, TimeHealAbility.INSTANCE, ChronoStasisAbility.INSTANCE, TimeHopAbility.INSTANCE, ChronoStasisGrigoraAbility.INSTANCE, TimeRessurectionAbility.INSTANCE, TimeMagicManaZoneAbility.INSTANCE};
    public static final Ability[] GRAVITY = {GravitySingularityAbility.INSTANCE, HeavyInfightingAbility.INSTANCE, PresenceOfTheDemonKingAbility.INSTANCE};

    private static Ability registerAbility(Ability ability)
    {
        String resourceName = Beapi.getResourceName(ability.getName());
        Beapi.getLangMap().put(ability.getI18nKey(), ability.getName());

        final ResourceLocation key = new ResourceLocation(Main.MODID, resourceName);
        RegistryObject<Ability> ret = RegistryObject.of(key, ABILITIES_REGISTRY);
        if(!ABILITIES.getEntries().contains(ret))
            ABILITIES.register(resourceName, () -> ability);

        return ability;
    }

    private static void registerAbilities(Ability[] abilities)
    {
        Arrays.stream(abilities).filter(Objects::nonNull).forEach(ModAbilities::registerAbility);
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
        ABILITIES.register(eventBus);
    }
}
