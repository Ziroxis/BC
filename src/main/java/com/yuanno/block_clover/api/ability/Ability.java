package com.yuanno.block_clover.api.ability;

import com.google.common.base.Strings;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.sorts.ChargeableAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.api.data.IExtraUpdateData;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.events.levelEvents.ExperienceUpEvent;
import com.yuanno.block_clover.networking.ManaSync;
import com.yuanno.block_clover.networking.PacketHandler;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/**
 * Base class for all abilities, with most of the information every ability needs.
 */
public class Ability extends ForgeRegistryEntry<Ability> {

    private boolean assignedExperience = false;
    private boolean isEvolved;
    private int evolutionCost;
    private String name = "";
    private String displayName;
    private String textureName = "";
    protected double timeProgression = 1;
    private AbilityCore core;
    private int[] pools = new int[0];
    private ResourceLocation customTexture;

    private int manaCost = 1;
    private int evolvedManaCost = 1;
    private int experiencePoint = 0;
    private int experienceGainLevelCap = 50;
    private ITextComponent tooltip;
    protected double cooldown;
    public double maxCooldown;
    protected double disableTicks;
    protected double maxDisableTicks = 200;
    protected  ICheck check;
    private AbilityCategories.AbilityCategory category = AbilityCategories.AbilityCategory.ALL;
    private AbilityUnlock unlock = AbilityUnlock.PROGRESSION;
    private State state = State.STANDBY;
    private State previousState = State.STANDBY;
    private boolean hideInGUI = false;
    private boolean forcedState = false;
    @Deprecated
    private boolean needsClientSide = false;
    private int poolId = -1;

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

        //TODO put the multiplier here

        player.level.getProfiler().push(() ->
        {
            return Beapi.getResourceName(this.getName());
        });

        if (this.isOnCooldown() && this.getCooldown() <= 10)
            this.stopCooldown(player);

        if (!this.isOnStandby())
            return;
        IEntityStats propsEntity = EntityStatsCapability.get(player);

        if (propsEntity.getExperienceSpell(this.getName()) != null && (int) propsEntity.getExperienceSpell(this.getName()) >= getEvolutionCost() && !this.isEvolved)
            this.evolved(true);

        AbilityUseEvent event = new AbilityUseEvent(player, this);
        if (MinecraftForge.EVENT_BUS.post(event))
            return;

        if (!this.isStateForced() && this.onUseEvent.onUse(player))
        {
            IAbilityData props = AbilityDataCapability.get(player);
            this.checkAbilityPool(player, State.COOLDOWN);

            //IEntityStats propsEntity = EntityStatsCapability.get(player);
            if (!this.isEvolved)
                propsEntity.alterMana(-manaCost);
            else
                propsEntity.alterMana(-evolvedManaCost);
            // experience of the spell
            if (propsEntity.hasExperienceSpell(this.getName())) {
                int experience = propsEntity.getExperienceSpell(this.getName());
                propsEntity.setExperienceSpells(this.getName(), experience + 1);

            }
            else
                propsEntity.setExperienceSpells(this.getName(), 1);

            // experience of player
            if (propsEntity.getLevel() < experienceGainLevelCap)
            {
                float experienceToGive = experiencePoint * propsEntity.getMultiplier();
                propsEntity.alterExperience((int) experienceToGive);

                ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, experiencePoint);
                MinecraftForge.EVENT_BUS.post(eventExperience);
            }
            PacketHandler.sendTo(new ManaSync(propsEntity.getMana()), player);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), propsEntity), player);


            this.startCooldown(player);
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

    public boolean isPassiveEnabled()
    {
        return this.state == State.PASSIVE;
    }

    public boolean isContinuous()
    {
        return this.state == State.CONTINUOUS && !this.isStateForced();
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

    public boolean isEvolved()
    {
        return this.isEvolved;
    }
    public void evolved(boolean isEvolved)
    {
        this.isEvolved = isEvolved;
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

    public void needsClientSide()
    {
        this.needsClientSide = true;
    }

    public boolean isClientSide()
    {
        return this.needsClientSide;
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

    public void setEvolvedManaCost(int value)
    {
        this.evolvedManaCost = value;
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
        String resourceName = Beapi.getResourceName(this.getName());
        return "ability." + Main.MODID + "." + resourceName;
    }

    public String getDisplayName()
    {
        return !Beapi.isNullOrEmpty(this.displayName) ? this.displayName : this.getName();
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
            this.customTexture = new ResourceLocation(this.getCore().getIcon().getNamespace(), "textures/abilities/" + Beapi.getResourceName(texture) + ".png");
    }

    public void setCustomTexture(String texture)
    {
        this.textureName = Beapi.getResourceName(texture);
    }

    public AbilityCategories.AbilityCategory getCategory()
    {
        return this.getCore().getCategory();
    }

    public void setUnlockType(AbilityUnlock unlockType)
    {
        this.unlock = unlockType;
    }

    public AbilityUnlock getUnlockType()
    {
        return this.unlock;
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
        // if(player.level.isClientSide)
        // return;
        player.level.getProfiler().push(() -> Beapi.getResourceName(this.getName()));

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

        if (propsEntity.getMana() < manaCost)
        {
            //player.sendMessage(new TranslationTextComponent("Not enough mana!"), Util.NIL_UUID);

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

    public interface ICheck {
        boolean check(PlayerEntity player);
    }

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
        nbt.putString("id", this.core.getRegistryName().toString());
        nbt.putString("displayName", Strings.isNullOrEmpty(this.getDisplayName()) ? "" : this.getDisplayName());
        nbt.putIntArray("pools", this.getPools());
        nbt.putString("unlock", this.getUnlockType().name());
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
        this.setUnlockType(AbilityUnlock.valueOf(nbt.getString("unlock")));
        this.setDisplayName(nbt.getString("displayName"));
        this.setState(Ability.State.valueOf(nbt.getString("state")));

        if (this instanceof IExtraUpdateData)
        {
            CompoundNBT extraData = nbt.getCompound("extraData");
            ((IExtraUpdateData) this).setExtraData(extraData);
        }
    }
    public void addInPool(AbilityPool... pools)
    {
        int[] intPools = Arrays.stream(pools).mapToInt(AbilityPool::id).toArray();
        this.addInPool(intPools);
    }

    public void addInPool(int... pools)
    {
        this.pools = pools;
    }

    public interface IFactory<A extends Ability>
    {
        A create(AbilityCore<A> ability);
    }
}
