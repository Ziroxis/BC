package com.yuanno.block_clover.entities.humanoid.npc;

import com.yuanno.block_clover.client.IDynamicRenderer;
import com.yuanno.block_clover.entities.api.BCentity;
import com.yuanno.block_clover.init.ModAttributes;
import com.yuanno.block_clover.init.ModItems;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SOpenChatPromptScreenPacket;
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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;


public class GrimoireMagicianEntity extends BCentity implements IDynamicRenderer
{
    public GrimoireMagicianEntity(EntityType type, World world)
    {
        super(type, world);
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand)
    {
        if (hand != Hand.MAIN_HAND)
            return ActionResultType.PASS;
        if (!player.level.isClientSide) {
            this.lookAt(player, 1, 1);
            PacketHandler.sendTo(new SOpenChatPromptScreenPacket(), player);
        }
        return ActionResultType.PASS;
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


    @Override
    public String getMobTexture() {
        return "grimoire_magician1";
    }

    @Override
    public String getDefaultTexture() {
        return null;
    }
}