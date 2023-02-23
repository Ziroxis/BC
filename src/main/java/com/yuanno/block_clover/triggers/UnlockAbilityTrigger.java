package com.yuanno.block_clover.triggers;

import com.google.gson.JsonObject;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.AbilityCore;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.loot.ConditionArraySerializer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class UnlockAbilityTrigger extends AbstractCriterionTrigger<UnlockAbilityTrigger.Instance>
{
	private static final ResourceLocation ID = new ResourceLocation(Main.MODID, "unlock_ability");
	
	@Override
	public ResourceLocation getId()
	{
		return ID;
	}
	
	@Override
	public Instance createInstance(JsonObject json, EntityPredicate.AndPredicate pEntityPredicate, ConditionArrayParser pConditionsParser)
	{
		AbilityCore ability = (AbilityCore) GameRegistry.findRegistry(AbilityCore.class).getValue(new ResourceLocation(JSONUtils.getAsString(json, "ability")));
		return new Instance(pEntityPredicate, ability);
	}

	public void trigger(ServerPlayerEntity player, AbilityCore ability)
	{
		this.trigger(player, (instance) ->
		{
			return instance.matches(player, ability);
		});
	}
	
	public static class Instance extends CriterionInstance
	{
		private AbilityCore ability;
		
		public Instance(EntityPredicate.AndPredicate entityPredicate, AbilityCore ability)
		{
			super(UnlockAbilityTrigger.ID, entityPredicate);
			this.ability = ability;
		}

		public boolean matches(ServerPlayerEntity player, AbilityCore ability)
		{
			return this.ability.equals(ability);
		}

		@Override
		public JsonObject serializeToJson(ConditionArraySerializer pConditions)
		{
			JsonObject jsonobject = super.serializeToJson(pConditions);
			jsonobject.addProperty("ability", this.ability.getRegistryName().toString());
			return jsonobject;
		}
	}
}
