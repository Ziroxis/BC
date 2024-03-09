package com.yuanno.block_clover.api.ability;

import com.google.common.base.Strings;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.ability.interfaces.IShootAbility;
import com.yuanno.block_clover.api.ability.sorts.ChargeableAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.api.data.IExtraUpdateData;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.devil.DevilCapability;
import com.yuanno.block_clover.data.devil.IDevil;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.events.ability.AbilityUseEvent;
import com.yuanno.block_clover.networking.ManaSync;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.CUseAbilityPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import com.yuanno.block_clover.networking.server.SUpdateEquippedAbilityPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Base class for all abilities, with most of the information every ability needs.
 * The start for quite literally every ability {@link #use(PlayerEntity)}, very often override, triggered here:
 * @see com.yuanno.block_clover.networking.client.CUseAbilityPacket#handle(CUseAbilityPacket, Supplier) 
 * All the cooldown logic is also handled here {@link #cooldown(PlayerEntity)}, triggered here:
 * @see com.yuanno.block_clover.events.ability.AbilitiesEvents#onLivingUpdate(LivingEvent.LivingUpdateEvent)
 */
public class Ability extends ForgeRegistryEntry<Ability> {

    private AbilityCore evolvedAbility;
    private int evolutionCost = 0;
    private String displayName;
    protected double timeProgression = 1;
    private AbilityCore core;
    private int[] pools = new int[0];
    private ResourceLocation customTexture;

    private int manaCost = 1;
    private int experiencePoint = 0;
    private int experienceGainLevelCap = 50;
    private ITextComponent tooltip;
    protected double cooldown;
    public double maxCooldown;
    protected double disableTicks;
    protected double maxDisableTicks = 200;
    private State state = State.STANDBY;
    private State previousState = State.STANDBY;
    private boolean hideInGUI = false;
    private boolean forcedState = false;
    private int poolId = -1;
    private boolean isDevil = false;

    protected final Random random = new Random();

    // Setting the defaults so that no crash occurs and so they will be null safe.
    protected IOnUse onUseEvent = (player) -> { return true; };
    protected IDuringCooldown duringCooldownEvent = (player, cooldown) -> {};
    protected IOnEndCooldown onEndCooldownEvent = (player) -> {};

    public Ability(AbilityCore core) {
    this.core = core;
    }

    /*
     * Event Starters
     */

    //Is called when player uses any ability
    public void use(PlayerEntity player)
    {
        if (player.level.isClientSide)
            return;
        System.out.println("WATERBAALL");
        player.level.getProfiler().push(() ->
        {
            return BeJavapi.getResourceName(this.getName());
        });

        if (this.isOnCooldown() && this.getCooldown() <= 10)
            this.stopCooldown(player);

        if (!this.isOnStandby())
            return;

        /*
        if (!this.isStateForced() && this.onUseEvent.onUse(player))
        {
            AbilityUseEvent pre = new AbilityUseEvent.Pre(player, this);
            MinecraftForge.EVENT_BUS.post(pre);

            IAbilityData props = AbilityDataCapability.get(player);
            this.checkAbilityPool(player, State.COOLDOWN);

            this.startCooldown(player);
            AbilityUseEvent post = new AbilityUseEvent.Post(player, this);
            MinecraftForge.EVENT_BUS.post(post);

            props.setPreviouslyUsedAbility(this);
            PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);

        }

         */
        if (this instanceof IShootAbility)
        {
            AbilityUseEvent pre = new AbilityUseEvent.Pre(player, this);
            MinecraftForge.EVENT_BUS.post(pre);

            ((IShootAbility) this).onUse(player, this);
            IAbilityData props = AbilityDataCapability.get(player);
            this.checkAbilityPool(player, State.COOLDOWN);

            this.startCooldown(player);
            AbilityUseEvent post = new AbilityUseEvent.Post(player, this);
            MinecraftForge.EVENT_BUS.post(post);

            props.setPreviouslyUsedAbility(this);
            PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
        }
        else if (this.onUseEvent.onUse(player))
        {
            AbilityUseEvent pre = new AbilityUseEvent.Pre(player, this);
            MinecraftForge.EVENT_BUS.post(pre);

            IAbilityData props = AbilityDataCapability.get(player);
            this.checkAbilityPool(player, State.COOLDOWN);

            this.startCooldown(player);
            AbilityUseEvent post = new AbilityUseEvent.Post(player, this);
            MinecraftForge.EVENT_BUS.post(post);

            props.setPreviouslyUsedAbility(this);
            PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);

        }
        player.level.getProfiler().pop();
    }

    /*
     * Setters/Getters
     */

    public boolean isOnStandby()
    {
        return this.state == State.STANDBY;
    }

    public boolean isOnCooldown()
    {
        return this.state == State.COOLDOWN;
    }


    public boolean isCharging()
    {
        return this.state == State.CHARGING && !this.isStateForced();
    }

    public boolean isDisabled()
    {
        return this.state == State.DISABLED;
    }

    public void startStandby()
    {
        this.previousState = this.state;
        this.state = State.STANDBY;
    }

    public void startDisable()
    {
        this.startDisable(20);
    }

    public void startDisable(int ticks)
    {
        this.previousState = this.state;
        this.state = State.DISABLED;
        this.maxDisableTicks = ticks;
        this.disableTicks = this.maxDisableTicks;
    }

    public double getDisableTicks()
    {
        return this.disableTicks;
    }

    public void setDisableTicks(double ticks)
    {
        this.disableTicks = ticks;
    }

    public void startCooldown(PlayerEntity player)
    {
        this.previousState = this.state;
        this.state = State.COOLDOWN;
    }

    public void stopCooldown(PlayerEntity player)
    {
        if (player.level.isClientSide)
            return;

        this.checkAbilityPool(player, State.STANDBY);

        this.cooldown = this.maxCooldown;
        this.previousState = this.state;
        this.state = State.STANDBY;
        if(!this.isStateForced())
        {
            this.onEndCooldownEvent.onEndCooldown(player);
            PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
        }
        this.setForcedState(false);
    }

    public void setState(State state)
    {
        this.previousState = this.state;
        this.state = state;
    }



    public State getState()
    {
        return this.state;
    }

    public State getPreviousState()
    {
        return this.previousState;
    }

    public void setForcedState(boolean flag)
    {
        this.forcedState = flag;
    }

    public boolean isStateForced()
    {
        return this.forcedState;
    }

    public void hideInGUI()
    {
        this.getCore().isHidden();
    }

    public boolean isHideInGUI()
    {
        return this.hideInGUI;
    }

    public void setInPool(int poolId)
    {
        this.poolId = poolId;
    }

    public int getPoolId()
    {
        return this.poolId;
    }

    public boolean isInPool()
    {
        return false;
    }

    /**
     *
     * @param cooldown - seconds before the ability can be used again
     */
    public void setMaxCooldown(double cooldown)
    {
        this.maxCooldown = cooldown * 20;
        this.cooldown = this.maxCooldown;
    }

    public double getMaxCooldown()
    {
        return this.maxCooldown;
    }

    public void setCooldown(double cooldown)
    {
        this.cooldown = cooldown * 20;
    }

    public double getCooldown()
    {
        return this.cooldown;
    }

    public void setEvolutionCost(int evolutionCost)
    {
        this.evolutionCost = evolutionCost;
    }

    public int getEvolutionCost()
    {
        return this.evolutionCost;
    }

    public void setExperienceGainLevelCap(int experienceGainLevelCap)
    {
        this.experienceGainLevelCap = experienceGainLevelCap;
    }

    public int getExperienceGainLevelCap()
    {
        return this.experienceGainLevelCap;
    }

    public void setExperiencePoint(int experiencePoint)
    {
        this.experiencePoint = experiencePoint;
    }

    public int getExperiencePoint()
    {
        return this.experiencePoint;
    }

    public void setmanaCost(int value)
    {
        this.manaCost = value;
    }

    public float getmanaCost()
    {
        return this.manaCost;
    }


    public double getTimeProgression()
    {
        return this.timeProgression;
    }

    public void setDescription(String desc)
    {
        this.tooltip = new StringTextComponent(desc);
    }

    public void setDescription(ITextComponent tooltip)
    {
        this.tooltip = tooltip;
    }

    public ITextComponent getDescription()
    {
        return this.getCore().getDescription();
    }

    public String getName()
    {
        return this.getCore().getName();
    }

    public String getI18nKey()
    {
        String resourceName = BeJavapi.getResourceName(this.getName());
        return "ability." + Main.MODID + "." + resourceName;
    }

    public String getDisplayName()
    {
        return !BeJavapi.isNullOrEmpty(this.displayName) ? this.displayName : this.getName();
    }

    public void setDisplayName(String name)
    {
        this.displayName = name;
    }

    public boolean hasCustomIcon()
    {
        return this.customTexture != null;
    }

    public ResourceLocation getIcon()
    {
        return this.hasCustomIcon() ? this.customTexture : this.getCore().getIcon();
    }

    public void setCustomIcon(ResourceLocation texture)
    {
        this.customTexture = texture;
    }

    public void setCustomIcon(String texture)
    {
        if(Strings.isNullOrEmpty(texture))
            this.customTexture = null;
        else
            this.customTexture = new ResourceLocation(this.getCore().getIcon().getNamespace(), "textures/abilities/" + BeJavapi.getResourceName(texture) + ".png");
    }

    public AbilityCategories.AbilityCategory getCategory()
    {
        return this.getCore().getCategory();
    }



    /*
     * Methods
     */

    public void disableTick(PlayerEntity player)
    {
        if(this.isDisabled() && this.disableTicks > 0)
        {
            this.disableTicks--;
        }
    }

    public void cooldown(PlayerEntity player)
    {
        player.level.getProfiler().push(() -> BeJavapi.getResourceName(this.getName()));

        if (this.isOnCooldown() && this.cooldown > 0)
        {
            this.cooldown-= 1 * this.getTimeProgression();
            if (!player.level.isClientSide && this.getPreviousState() != State.DISABLED && !this.isStateForced())
                this.duringCooldownEvent.duringCooldown(player, (int) this.cooldown);
        }
        else if (this.isOnCooldown() && this.cooldown <= 0)
        {
            this.stopCooldown(player);
        }

        player.level.getProfiler().pop();
    }

    public void checkAbilityPool(PlayerEntity player, State state)
    {
        IAbilityData props = AbilityDataCapability.get(player);
        if(this.isInPool() && !this.isStateForced())
        {
            for(Ability abl : props.getEquippedAbilities())
            {
                if(abl != null && abl != this && abl.isInPool() && abl.getPoolId() == this.getPoolId())
                {
                    double[] values = null;
                    boolean forced = true;

                    if(state == State.COOLDOWN)
                        values = new double[] { this.getCooldown() / 20, this.getCooldown() / 20 };
                    else if(state == State.CHARGING && abl instanceof ChargeableAbility && this instanceof ChargeableAbility)
                        values = new double[] { ((ChargeableAbility)this).getChargeTime() / 20, ((ChargeableAbility)this).getMaxChargeTime() / 20 };
                    else if(state == State.CONTINUOUS && abl instanceof ContinuousAbility && this instanceof ContinuousAbility)
                        values = new double[] { ((ContinuousAbility)this).getContinueTime() / 20, ((ContinuousAbility)this).getThreshold() / 20 };
                    else if(state == State.STANDBY)
                        forced = false;

                    abl.previousState = this.getState();
                    abl.state = state;

                    abl.forcedState = forced;

                    if(values != null)
                        PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, abl, state, values), player);
                    else
                        PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, abl), player);
                }
            }
        }
    }

    /**
     * @return % of remaining cooldown between 0-100, going towards 0% from max cooldown(100%)
     */
    public double getCooldownPercentage()
    {
        return (this.cooldown / this.maxCooldown) * 100;
    }

    /**
     * @return % of passed cooldown between 0-100, going towards 100% from 0%
     */
    public double getInvertedCooldownPercentage()
    {
        return (1 - (this.cooldown / this.maxCooldown)) * 100;
    }

    @Override
    public boolean equals(Object abl)
    {
        if (!(abl instanceof Ability))
            return false;

        return this.getName().equalsIgnoreCase(((Ability) abl).getName());
    }

    @Nullable
    public Ability create()
    {
        try
        {
            return this.getClass().getConstructor().newInstance();
        }
        catch (Exception ex)
        {
            System.out.println("Exception raised for " + this.getDisplayName());
            ex.printStackTrace();
        }
        return null;
    }

    @Nullable
    public static Ability get(ResourceLocation res)
    {
        Ability ability = GameRegistry.findRegistry(Ability.class).getValue(res);
        return ability;
    }

    public boolean canUse(PlayerEntity player)
    {
        ExtendedWorldData worldData = ExtendedWorldData.get(player.level);
        IEntityStats propsEntity = EntityStatsCapability.get(player);
        IDevil devil = DevilCapability.get(player);

        if (!this.isDevil && (propsEntity.getMana() < getmanaCost() + 5 && getmanaCost() != 0 && propsEntity.getMana() != 0)) {
            return false;
        }
        else if (this.isDevil && (devil.getDevilMana() < getmanaCost() + 5 && getmanaCost() != 0 && devil.getDevilMana() != 0)) {
            return false;
        }
        if (worldData.isInsideRestrictedArea((int)player.position().x(), (int)player.position().y(), (int)player.position().z()))
        {
            boolean isWhitelsited = false;
            if(isWhitelsited)
                return true;

            player.sendMessage(new TranslationTextComponent("Can't use here"), Util.NIL_UUID);
            return false;
        }

        return true;
    }

    /*
     * Enums
     */

    public enum State
    {
        STANDBY, DISABLED,

        COOLDOWN, PASSIVE, CONTINUOUS, CHARGING
    }

    /*
     * Interfaces
     */
    public interface IOnUse extends Serializable
    {
        boolean onUse(PlayerEntity player);
    }

    public interface IDuringCooldown extends Serializable
    {
        void duringCooldown(PlayerEntity player, int cooldown);
    }

    public interface IOnEndCooldown extends Serializable
    {
        void onEndCooldown(PlayerEntity player);
    }

    public AbilityDamageKind getDamageKind()
    {
        return this.getCore().getDamageKind();
    }
    public CompoundNBT save(CompoundNBT nbt)
    {
        if (this.core.getRegistryName() != null)
            nbt.putString("id", this.core.getRegistryName().toString());
        nbt.putString("displayName", Strings.isNullOrEmpty(this.getDisplayName()) ? "" : this.getDisplayName());
        nbt.putIntArray("pools", this.getPools());
        nbt.putString("state", this.getState().toString());

        if (this instanceof IExtraUpdateData)
        {
            CompoundNBT extraData = ((IExtraUpdateData) this).getExtraData();
            nbt.put("extraData", extraData);
        }

        return nbt;
    }
    public AbilityCore getCore()
    {
        return this.core;
    }
    public int[] getPools()
    {
        return this.pools;
    }
    public void load(CompoundNBT nbt)
    {
        this.addInPool(nbt.getIntArray("pools"));
        this.setDisplayName(nbt.getString("displayName"));
        this.setState(Ability.State.valueOf(nbt.getString("state")));

        if (this instanceof IExtraUpdateData)
        {
            CompoundNBT extraData = nbt.getCompound("extraData");
            ((IExtraUpdateData) this).setExtraData(extraData);
        }
    }

    public void addInPool(int... pools)
    {
        this.pools = pools;
    }

    public interface IFactory<A extends Ability>
    {
        A create(AbilityCore<A> ability);
    }

    public void setDevil(boolean flag)
    {
        this.isDevil = flag;
    }
    public boolean getIsDevil()
    {
        return this.isDevil;
    }
    public void setEvolvedAbility(AbilityCore ability)
    {
        this.evolvedAbility = ability;
    }
    public AbilityCore getEvolvedAbility()
    {
        return this.evolvedAbility;
    }
}
