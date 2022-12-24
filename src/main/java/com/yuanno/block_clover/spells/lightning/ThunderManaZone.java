package com.yuanno.block_clover.spells.lightning;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityHelper;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.blocks.tileentities.LightningBlockTileEntity;
import com.yuanno.block_clover.init.ModBlocks;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SUpdateEquippedAbilityPacket;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ThunderManaZone extends ContinuousAbility implements IParallelContinuousAbility {
    public static final ThunderManaZone INSTANCE = new ThunderManaZone();

    public static final int MAX_ZONE_SIZE = 40;
    public static final int MAX_THRESHOLD = 2;

    private List<BlockPos> blockList = new ArrayList<>();
    public List<BlockPos> placedBlockList = new ArrayList<>();
    private int roomSize = 0;
    private int chargingTicks = 0;
    private BlockPos centerBlock;
    boolean zoneSet = false;

    public ThunderManaZone()
    {
        super("Thunder Mana zone", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Creates a dome of lightning, repeatedly hitting entities with lightning in it");
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
            this.stopContinuity(player);
        if (this.getThreshold() == 0)
        {
            if (this.blockList.isEmpty())
            {
                this.blockList.addAll(AbilityHelper.createSphere(player.level, player.blockPosition(), this.roomSize, true, ModBlocks.LIGHTNING.get(), 0));
                this.centerBlock = new BlockPos(player.getX(), player.getY(), player.getZ());
                player.level.setBlockAndUpdate(this.centerBlock, ModBlocks.LIGHTNING.get().defaultBlockState());
                TileEntity tileEntity = player.level.getBlockEntity(this.centerBlock);
                zoneSet = true;
                if (tileEntity != null && tileEntity instanceof LightningBlockTileEntity)
                {
                    ((LightningBlockTileEntity)tileEntity).setOwner(player);
                    ((LightningBlockTileEntity)tileEntity).setChanged();
                }
                this.blockList.add(new BlockPos(MathHelper.floor(player.getX()), MathHelper.floor(player.getY()), MathHelper.floor(player.getZ())));
                this.placedBlockList.addAll(this.blockList);
                this.setThreshold(0);
            }
            else
            {
                int placedBlock = 0;
                Iterator<BlockPos> iterator = this.placedBlockList.iterator();
                while (iterator.hasNext())
                {
                    BlockPos pos = iterator.next();
                    player.level.sendBlockUpdated(pos, Blocks.AIR.defaultBlockState(), ModBlocks.LIGHTNING.get().defaultBlockState(), 0);
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
                this.stopContinuity(player);
            List<LivingEntity> entities = Beapi.getEntitiesAround(this.centerBlock, player.level, roomSize + 30, LivingEntity.class);
            if (entities.contains(player))
                entities.remove(player);
            entities.forEach(entity -> {
                LightningBoltEntity lightningBoltEntity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, player.level);
                lightningBoltEntity.setPos(entity.getX(), entity.getY(), entity.getZ());
                player.level.addFreshEntity(lightningBoltEntity);
            });
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
        for (BlockPos pos : this.blockList)
        {
            Block currentBlock = player.level.getBlockState(pos).getBlock();
            if (currentBlock == ModBlocks.LIGHTNING.get() || currentBlock == ModBlocks.LIGHTNING.get())
                player.level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }
//        player.level.setBlockAndUpdate(centerBlock, Blocks.AIR.defaultBlockState());
        this.zoneSet = false;
        this.blockList.clear();
        this.placedBlockList.clear();
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
