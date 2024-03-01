package com.yuanno.block_clover.spells.water;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.*;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.blocks.tileentities.AntiMagicBlockTileEntity;
import com.yuanno.block_clover.blocks.tileentities.LightningBlockTileEntity;
import com.yuanno.block_clover.init.ModBlocks;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SUpdateEquippedAbilityPacket;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WaterManaZoneAbility extends ContinuousAbility implements IParallelContinuousAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Water mana zone", AbilityCategories.AbilityCategory.ATTRIBUTE, WaterManaZoneAbility.class)
            .setDescription("Teleports you into the air, creating a large dome of water around you and your enemy.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    public static final int MAX_ZONE_SIZE = 30;
    public static final int MAX_THRESHOLD = 2;

    private List<BlockPos> blockListCreate = new ArrayList<>();
    private List<BlockPos> blockListDelete = new ArrayList<>();
    public List<BlockPos> placedBlockList = new ArrayList<>();
    private int roomSize = 0;
    private int chargingTicks = 0;
    private BlockPos centerBlock;
    boolean zoneSet = false;
    Entity entity;

    public WaterManaZoneAbility()
    {
        super(INSTANCE);
        this.setmanaCost(0);
        this.setMaxCooldown(180);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.duringContinuityEvent = this::duringContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
        this.onStopContinuityEvent = this::onStopContinuityEvent;

    }
    public int getROOMSize()
    {
        return this.roomSize;
    }
    private boolean onStartContinuityEvent(PlayerEntity player)
    {
            this.chargingTicks = 10;
            this.setThreshold(MAX_THRESHOLD);
            return true;
    }
    public void duringContinuityEvent(PlayerEntity player, int timer)
    {
        if (timer == 180 * 20)
            this.endContinuity(player);
        if (this.getThreshold() == 0)
        {
            if (this.blockListCreate.isEmpty())
            {
                //player.teleportTo(player.getX(), player.getY() + 50, player.getZ());
                //entity.teleportTo(player.getX() + 3, player.getY() + 50, player.getZ() + 3);
                this.blockListCreate.addAll(AbilityHelper.createSphere(player.level, player.blockPosition(), this.roomSize, false, Blocks.WATER, 0));
                this.centerBlock = new BlockPos(player.getX(), player.getY(), player.getZ());
                player.level.setBlockAndUpdate(this.centerBlock, ModBlocks.LIGHTNING.get().defaultBlockState());
                TileEntity tileEntity = player.level.getBlockEntity(this.centerBlock);
                zoneSet = true;

                if (tileEntity != null && tileEntity instanceof LightningBlockTileEntity)
                {
                    ((LightningBlockTileEntity)tileEntity).setOwner(player);
                    ((LightningBlockTileEntity)tileEntity).setChanged();
                }


                this.blockListCreate.add(new BlockPos(MathHelper.floor(player.getX()), MathHelper.floor(player.getY()), MathHelper.floor(player.getZ())));
                this.placedBlockList.addAll(this.blockListCreate);
                this.setThreshold(0);

            }
            else
            {
                int placedBlock = 0;
                Iterator<BlockPos> iterator = this.placedBlockList.iterator();
                while (iterator.hasNext())
                {
                    BlockPos pos = iterator.next();
                    if (!(player.level.getBlockState(pos).getBlock() == Blocks.AIR))
                        player.level.sendBlockUpdated(pos, Blocks.AIR.defaultBlockState(), Blocks.WATER.defaultBlockState(), 0);
                    iterator.remove();
                    placedBlock++;
                    if (placedBlock > 500)
                        return;
                }
            }
        }
        else
        {
            if(0 >= this.chargingTicks)
            {
                this.chargingTicks = 19;
            }
            else
                this.chargingTicks++;
        }
        if (zoneSet)
        {
            if (!isEntityInThisRoom(player))
                this.endContinuity(player);
            if (!player.hasEffect(Effects.WATER_BREATHING))
                player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 80, 0));
            if (!player.hasEffect(Effects.DOLPHINS_GRACE))
                player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 80, 0));

        }
    }
    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        if (this.getThreshold() > 0)
        {
            this.setThreshold(0);
            PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
            this.roomSize = Math.max(8, (int) (MAX_ZONE_SIZE * ((this.continueTime / 20.0 ) / MAX_THRESHOLD)));
            return false;
        }
        return true;
    }
    private boolean onStopContinuityEvent(PlayerEntity player)
    {
        this.roomSize = this.roomSize + 28;
        this.blockListDelete.addAll(AbilityHelper.createSphere(player.level, player.blockPosition(), this.roomSize, false, Blocks.WATER, 0));
        this.blockListDelete.add(new BlockPos(MathHelper.floor(player.getX()), MathHelper.floor(player.getY()), MathHelper.floor(player.getZ())));
        for (BlockPos pos : this.blockListDelete)
        {
            Block currentBlock = player.level.getBlockState(pos).getBlock();
            if (currentBlock == Blocks.WATER || currentBlock == Blocks.AIR)
                player.level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }
//        player.level.setBlockAndUpdate(centerBlock, Blocks.AIR.defaultBlockState());
        this.zoneSet = false;
        this.blockListCreate.clear();
        this.placedBlockList.clear();
        this.blockListDelete.clear();
        this.setMaxCooldown(0);
        PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
        return true;
    }

    public boolean isEntityInThisRoom(Entity entity)
    {
        return this.isInsideROOM(entity.level, entity.blockPosition());
    }

    public boolean isInsideROOM(World world, BlockPos pos)
    {
        int roomSize = this.roomSize;
        for (int i = -roomSize; i < roomSize; i++)
            for (int j = -roomSize; j < roomSize; j++)
                for (int k = -roomSize; k < roomSize; k++)
                {
                    BlockPos posCheck = pos.offset(i, j, k);
                    if (world.getBlockState(posCheck).getBlock() == ModBlocks.LIGHTNING.get())
                    {
                        double distance = pos.distSqr(posCheck);
                        if(distance < (roomSize - 1) * (roomSize - 1))
                            return true;
                    }
                }

        return false;
    }
}
