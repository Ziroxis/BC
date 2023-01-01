package com.yuanno.block_clover.entities;

import com.yuanno.block_clover.data.ability.AbilityDataBase;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModAttributes;
import com.yuanno.block_clover.init.ModItems;
import com.yuanno.block_clover.init.ModQuests;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;


public class GrimoireMagicianEntity extends NPCentity
{
    public GrimoireMagicianEntity(EntityType type, World world)
    {
        super(type, world);
        this.preRequisite = true;
        this.requisite = 1;
        this.levelrequisites.add(0);
        this.levelrequisites.add(10);
        this.levelrequisites.add(30);
        this.quests.add(ModQuests.GRIMOIRE);
        this.quests.add(ModQuests.REINFORCEMENT);
        this.quests.add(ModQuests.MANASKIN);
        this.quests.add(ModQuests.MANAZONEQUEST);
        this.requisiteSpeech = "You'll need to be a bit stronger to learn the spells I want to teach you!";
        this.questChoiceSpeech = "Would you like to learn the art of mana?";
        this.acceptanceSpeech = "Fine... You'll have to meditate for that.";
        this.declineSpeech = "Not everyone is ready for the path of strength";
        this.waitingSpeech = "Have you done your meditation?";
        this.doneSpeech = "My teachings end here for now";
    }
    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1, true));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 4));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 50)
                .add(Attributes.ARMOR, 20)
                .add(Attributes.MAX_HEALTH, 300)
                .add(Attributes.FOLLOW_RANGE, 100)
                .add(Attributes.MOVEMENT_SPEED, 3)
                .add(ModAttributes.FALL_RESISTANCE.get(), 50);

    }

    @Override
    public boolean removeWhenFarAway(double d)
    {
        return false;
    }



    
    @Override
    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag)
    {
        spawnData = super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);

        ItemStack hatStack = new ItemStack(ModItems.MAGE_HAT.get());
        this.setItemSlot(EquipmentSlotType.HEAD, hatStack);
        ItemStack chestStack = new ItemStack(ModItems.MAGE_CHEST.get());
        this.setItemSlot(EquipmentSlotType.CHEST, chestStack);
        ItemStack legsStack = new ItemStack(ModItems.MAGE_LEGS.get());
        this.setItemSlot(EquipmentSlotType.LEGS, legsStack);
        ItemStack feetStack = new ItemStack(ModItems.MAGE_FEET.get());
        this.setItemSlot(EquipmentSlotType.FEET, feetStack);

        return spawnData;
    }
     

}
