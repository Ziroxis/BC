package com.yuanno.block_clover.data.ability;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.sorts.ChargeableAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.PassiveAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;
import java.util.stream.Collectors;

public class AbilityDataCapability
{
	@CapabilityInject(IAbilityData.class)
	public static final Capability<IAbilityData> INSTANCE = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IAbilityData.class, new Capability.IStorage<IAbilityData>()
		{
			@Override
			public INBT writeNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side)
			{
				CompoundNBT props = new CompoundNBT();

				try
				{
					ListNBT unlockedAbilities = new ListNBT();
					for (int i = 0; i < instance.getUnlockedAbilities(AbilityCategories.AbilityCategory.ALL).size(); i++)
					{
						Ability ability = instance.getUnlockedAbilities(AbilityCategories.AbilityCategory.ALL).get(i);

						CompoundNBT nbtAbility = new CompoundNBT();
						nbtAbility = ability.save(nbtAbility);
						if(ability instanceof PassiveAbility)
						{
							nbtAbility.putBoolean("isPaused", ((PassiveAbility)ability).isPaused());
						}
						unlockedAbilities.add(nbtAbility);
					}
					props.put("unlocked_abilities", unlockedAbilities);

					ListNBT equippedAbilities = new ListNBT();
					for (int i = 0; i < instance.getEquippedAbilities().size(); i++)
					{
						Ability ability = instance.getEquippedAbilities().get(i);
						if(ability != null)
						{
							String name = BeJavapi.getResourceName(ability.getName());
							CompoundNBT nbtAbility = new CompoundNBT();
							nbtAbility = ability.save(nbtAbility);
							nbtAbility.putInt("pos", i);
							nbtAbility.putDouble("cooldown", ability.getCooldown());
							nbtAbility.putDouble("maxCooldown", ability.getMaxCooldown());
							nbtAbility.putBoolean("isForced", ability.isStateForced());
							if(ability instanceof ContinuousAbility)
							{
								nbtAbility.putDouble("continueTimer", ((ContinuousAbility)ability).getContinueTime());
								nbtAbility.putDouble("threshold", ((ContinuousAbility)ability).getThreshold());
							}
							if(ability instanceof ChargeableAbility)
								nbtAbility.putDouble("chargeTimer", ((ChargeableAbility)ability).getChargeTime());
							equippedAbilities.add(nbtAbility);
						}
						else
						{
							CompoundNBT nbtAbility = new CompoundNBT();
							nbtAbility.putInt("pos", i);
							equippedAbilities.add(nbtAbility);
						}
					}
					props.put("equipped_abilities", equippedAbilities);
					
					props.putInt("combat_bar_set", instance.getCombatBarSet());
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				return props;
			}

			@Override
			public void readNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				try
				{
					instance.clearEquippedAbilities(AbilityCategories.AbilityCategory.ALL);
					instance.clearUnlockedAbilities(AbilityCategories.AbilityCategory.ALL);

					ListNBT unlockedAbilities = props.getList("unlocked_abilities", Constants.NBT.TAG_COMPOUND);
					for (int i = 0; i < unlockedAbilities.size(); i++)
					{
						CompoundNBT nbtAbility = unlockedAbilities.getCompound(i);

						try
						{
							AbilityCore core = (AbilityCore) GameRegistry.findRegistry(AbilityCore.class).getValue(new ResourceLocation(nbtAbility.getString("id")));
							if(core == null)
								continue;
							Ability ability = core.createAbility();
							ability.load(nbtAbility);
							if(ability instanceof PassiveAbility)
							{
								((PassiveAbility)ability).setPause(nbtAbility.getBoolean("isPaused"));
							}
							instance.loadUnlockedAbility(ability);
						}
						catch(Exception e)
						{
							System.out.println(nbtAbility.getString("name") + " not registering");
							e.printStackTrace();
						}
					}

					ListNBT equippedAbilities = props.getList("equipped_abilities", Constants.NBT.TAG_COMPOUND);
					List<Ability> activeAbilitiesUnlocked = instance.getUnlockedAbilities(AbilityCategories.AbilityCategory.ALL).parallelStream().filter(ability -> !(ability instanceof PassiveAbility)).collect(Collectors.toList());
					for (int i = 0; i < equippedAbilities.size(); i++)
					{
						CompoundNBT nbtAbility = equippedAbilities.getCompound(i);
						AbilityCore core = (AbilityCore) GameRegistry.findRegistry(AbilityCore.class).getValue(new ResourceLocation(nbtAbility.getString("id")));
						if (core != null)
						{
							Ability ability = core.createAbility();
							if (activeAbilitiesUnlocked.contains(ability))
							{
								ability.load(nbtAbility);
								int cooldown = (int) (nbtAbility.getDouble("cooldown") / 20);
								int maxCooldown = (int) (nbtAbility.getDouble("maxCooldown") / 20);
								int pos = nbtAbility.getInt("pos");
								boolean isForced = nbtAbility.getBoolean("isForced");
								int experience = nbtAbility.getInt("experience");
								ability.setMaxCooldown(maxCooldown);
								ability.setCooldown(cooldown);
								ability.setForcedState(isForced);
								if (ability instanceof ContinuousAbility)
								{
									int continueTime = (int) (nbtAbility.getDouble("continueTime") / 20.0);
									((ContinuousAbility) ability).setContinueTime(continueTime);
									int threshold = (int) (nbtAbility.getDouble("threshold") / 20.0);
									((ContinuousAbility) ability).setThreshold(threshold);
								}
								if (ability instanceof ChargeableAbility)
								{
									int chargeTime = (int) (nbtAbility.getDouble("chargeTime") / 20.0);
									((ChargeableAbility) ability).setChargeTime(chargeTime);
								}
								instance.setEquippedAbility(pos, ability);
							}
						}
						else if(core == null)
						{
							int pos = nbtAbility.getInt("pos");
							instance.setEquippedAbility(pos, null);
						}
					}
					
					instance.setCombatBarSet(props.getInt("combat_bar_set"));
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}

		}, AbilityDataBase::new);
	}

	public static IAbilityData get(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(new AbilityDataBase());
	}

	public static LazyOptional<IAbilityData> getLazy(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null);
	}
}
