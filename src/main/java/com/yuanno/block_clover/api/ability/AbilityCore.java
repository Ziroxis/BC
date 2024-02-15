package com.yuanno.block_clover.api.ability;

import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.spells.water.WaterBallAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.io.Serializable;

public class AbilityCore<A extends Ability> extends ForgeRegistryEntry<AbilityCore<?>>
{
	private Class<? extends A> clazz;
	private final String name;
	private final AbilityCategories.AbilityCategory category;
	private ResourceLocation icon;
	private Ability.IFactory<A> factory;
	private ITextComponent description;
	private AbilityDamageKind damageKind;
	private ICanUnlock unlockCheck;
	private AbilityCore<? extends ContinuousAbility>[] deps;
	private boolean isHidden = false;
	
	@Deprecated
	protected AbilityCore(String name, AbilityCategories.AbilityCategory category, Class<? extends A> clazz)
	{
		this.name = name;
		this.category = category;
		this.clazz = clazz;
	}
	
	protected AbilityCore(String name, AbilityCategories.AbilityCategory category, Ability.IFactory<A> factory)
	{
		this.name = name;
		this.category = category;
		this.factory = factory;
	}
	
	public String getName()
	{
		return this.name;
	}

	public AbilityCategories.AbilityCategory getCategory()
	{
		return this.category;
	}
	
	public Class<? extends A> getAbilityClass()
	{
		return this.clazz;
	}
	
	public Ability.IFactory<? extends A> getFactory()
	{
		return this.factory;
	}
	
	private void setUnlockCheck(ICanUnlock check)
	{
		this.unlockCheck = check;
	}

	public ICanUnlock getUnlockCheck()
	{
		return this.unlockCheck;
	}
	
	public boolean canUnlock(LivingEntity entity)
	{
		return this.getUnlockCheck().canUnlock(entity);
	}
	
	private void setDamageKind(AbilityDamageKind kind)
	{
		this.damageKind = kind;
	}
	
	public AbilityDamageKind getDamageKind()
	{
		return this.damageKind;
	}
	
	protected void setDescription(String tooptip)
	{
		this.setDescription(new StringTextComponent(tooptip));
	}
	
	protected void setDescription(ITextComponent tooltip)
	{
		this.description = tooltip;
	}
	
	public ITextComponent getDescription()
	{
		return this.description;
	}
	
	protected void setDependencies(AbilityCore<? extends ContinuousAbility>... deps)
	{
		this.deps = deps;
	}
	
	public void setIcon(ResourceLocation res)
	{
		if(this.icon != null)
			return;
		this.icon = new ResourceLocation(res.getNamespace(), "textures/abilities/" + res.getPath() + ".png");
	}
	
	public ResourceLocation getIcon()
	{
		return this.icon;
	}
	
	@Nullable
	public AbilityCore<? extends ContinuousAbility>[] getDependencies()
	{
		return this.deps;
	}
	
	public void setHidden()
	{
		this.isHidden = true;
	}
	
	public boolean isHidden()
	{
		return this.isHidden;
	}
	
	@Nullable
	public A createAbility()
	{
    	if(this.factory != null)
        	return this.factory.create(this);
		try
    	{
        	// Check if the clazz field is a container for Ability subclasses

			/*
        	if (clazz.getEnclosingClass() != null && Ability.class.isAssignableFrom(clazz.getEnclosingClass())) {
				Class<?>[] declaredClasses = clazz.getDeclaredClasses();

				for (Class<?> declaredClass : declaredClasses) {
                // Check if the declared class is a subclass of Ability
                	if (Ability.class.isAssignableFrom(declaredClass)) {
                    	// Create an instance of the declared class and return it
                   	 return (A) declaredClass.getConstructor().newInstance();
					}
				}
			}

			 */
			if (clazz.getDeclaredClasses().length > 0)
			{
				Class<?>[] declaredClasses = WaterBallAbility.class.getDeclaredClasses();
				for (Class<?> declaredClass : declaredClasses) {
					if (Ability.class.isAssignableFrom(declaredClass))
						return (A) declaredClass.getConstructor().newInstance();
				}
			}
        	// If the clazz field is not a container for Ability subclasses, create an instance of the clazz field itself
        	return this.getAbilityClass().getConstructor().newInstance();
    }
		catch (Exception ex)
		{
			System.out.println("Exception raised for " + this.getName());
			ex.printStackTrace();
		}
		return null;
	}
	
	@Nullable
	public static <A extends Ability> AbilityCore<A> get(ResourceLocation res)
	{
		AbilityCore<A> core = (AbilityCore<A>) GameRegistry.findRegistry(AbilityCore.class).getValue(res);
		return core;
	}
	
	public static class Builder<T extends Builder, A extends Ability>
	{
		private Class<? extends A> clazz;
		private final String name;
		private final AbilityCategories.AbilityCategory category;
		private Ability.IFactory<A> factory;
		private ITextComponent tooltip;
		private AbilityDamageKind damageKind = AbilityDamageKind.UNKNOWN;
		private ICanUnlock unlockCheck = (entity) -> true;
		private AbilityCore<? extends ContinuousAbility>[] deps;
		private boolean isHidden = false;
		
		public Builder(String name, AbilityCategories.AbilityCategory category, Class<? extends A> clazz)
		{
			this.name = name;
			this.category = category;
			this.clazz = clazz;
		}
		
		public Builder(String name, AbilityCategories.AbilityCategory category, Ability.IFactory<A> factory)
		{
			this.name = name;
			this.category = category;
			this.factory = factory;
		}
		
		public T setUnlockCheck(ICanUnlock unlockCheck)
		{
			this.unlockCheck = unlockCheck;
			return (T) this;
		}
		
		public T setDamageKind(AbilityDamageKind kind)
		{
			this.damageKind = kind;
			return (T) this;
		}
		
		public T setDescription(String desc)
		{
			this.setDescription(new StringTextComponent(desc));
			return (T) this;
		}
		
		public T setDescription(ITextComponent tooltip)
		{
			this.tooltip = tooltip;
			return (T) this;
		}

		public T setDependencies(AbilityCore<? extends ContinuousAbility>... deps)
		{
			this.deps = deps;
			return (T) this;
		}
		
		public T setHidden()
		{
			this.isHidden = true;
			return (T) this;
		}
		
		public AbilityCore<A> build()
		{
			AbilityCore<A> core = null;
			if(this.factory != null)
				core = new AbilityCore(this.name, this.category, this.factory);
			else
				core = new AbilityCore(this.name, this.category, this.clazz);
			core.setUnlockCheck(this.unlockCheck);
			core.setDescription(this.tooltip);
			core.setDamageKind(this.damageKind);
			core.setDependencies(this.deps);
			if(this.isHidden) core.setHidden();
			return core;
		}
	}
	
	public interface ICanUnlock extends Serializable
	{
		boolean canUnlock(LivingEntity entity);
	}
}
