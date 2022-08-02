package com.yuanno.block_clover.entities;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModAttributes;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;


public class GrimoireMagicianEntity extends CreatureEntity
{
    public GrimoireMagicianEntity(EntityType type, World world)
    {
        super(type, world);
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
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand)
    {
        if (hand != Hand.MAIN_HAND)
            return ActionResultType.PASS;

        if (!player.level.isClientSide)
        {
            IEntityStats stats = EntityStatsCapability.get(player);

            int magicLevel = stats.getLevel();

            if (stats.hasGrimoire())
            {
                player.displayClientMessage(new StringTextComponent("You already got your grimoire go away!"), false);

            }
            else {
                if (magicLevel >= 5)
                {
                    player.displayClientMessage(new StringTextComponent("Here is your Grimoire! You seem mature enough"), false);
                    stats.setGrimoire(true);
                    PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), stats), player);

                } else
                {
                    player.displayClientMessage(new StringTextComponent("Come back when you're mature enough for a grimoire! Get at least level 5!"), false);
                }
            }

        }
        return ActionResultType.PASS;
    }

    /*
    @Override
    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag)
    {
        spawnData = super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);

        ItemStack hatStack = new ItemStack(ItemInit.MAGE_HAT.get());
        this.setItemSlot(EquipmentSlotType.HEAD, hatStack);
        ItemStack chestStack = new ItemStack(ItemInit.MAGE_CHEST.get());
        this.setItemSlot(EquipmentSlotType.CHEST, chestStack);
        ItemStack legsStack = new ItemStack(ItemInit.MAGE_LEGS.get());
        this.setItemSlot(EquipmentSlotType.LEGS, legsStack);
        ItemStack feetStack = new ItemStack(ItemInit.MAGE_FEET.get());
        this.setItemSlot(EquipmentSlotType.FEET, feetStack);

        return spawnData;
    }
     */

}
