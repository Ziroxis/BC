package com.yuanno.block_clover.spells.sealing;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.spells.fire.LeoPalmaAbility;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UltimateSealAbility extends Ability {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Ultimate Seal", AbilityCategories.AbilityCategory.ATTRIBUTE, UltimateSealAbility.class)
            .setDescription("Seals the enemy in a temporary seal.")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .build();
    private List<BlockPos> blockList = new ArrayList<>();
    private List<BlockPos> placedBlockList = new ArrayList<>();
    public static final int MAX_THRESHOLD = 2;
    private int chargingTicks = 0;
    public static final int MAX_ROOM_SIZE = 7;
    private int size = 5;
    private BlockPos centerBlock;
    private List<LivingEntity> entities = new ArrayList<LivingEntity>();

    public UltimateSealAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(30);
        this.setmanaCost(12);
        this.setExperiencePoint(50);

        this.onUseEvent = this::onUseEvent;

    }

    private boolean onUseEvent(PlayerEntity player)
    {
        /*
        LivingEntity target;
        BlockPos posEntity = target.blockPosition();
        if (target instanceof PlayerEntity || target instanceof ZombieEntity || target instanceof SkeletonEntity || target instanceof CreeperEntity)
        {
            player.level.setBlock(player.blockPosition(), Blocks.GLASS.defaultBlockState(), Constants.BlockFlags.DEFAULT);
        }
         */

        RayTraceResult mop = Beapi.rayTraceBlocksAndEntities(player, 32);


        int x0 = (int) mop.getLocation().x();
        int y0 = (int) mop.getLocation().y();
        int z0 = (int) mop.getLocation().z();
        List<LivingEntity> targets = Beapi.getEntitiesNear(new BlockPos(x0, y0, z0), player.level, 1, LivingEntity.class);
        targets.remove(player);
        if (targets.isEmpty())
            return false;
        this.entities.addAll(targets);
        for (LivingEntity target : this.entities)
        {
            if (!target.hasEffect(ModEffects.SEALING.get()))
                target.addEffect(new EffectInstance(ModEffects.SEALING.get(), 140, 0));
        }
        int radius = 3;
        for (int y = y0 - radius; y <= y0 + radius; y++)
        {
            for (int x = x0 - radius; x <= x0 + radius; x++)
            {
                for (int z = z0 - radius; z <= z0 + radius; z++)
                {
                    double distance = ((x0 - x) * (x0 - x) + ((z0 - z) * (z0 - z)) + ((y0 - y) * (y0 - y)));

                    if (distance < radius * radius && !(true && distance < ((radius - 1) * (radius - 1))))
                    {
                        BlockPos pos = new BlockPos(x, y, z);
                        if(true)
                            blockList.add(pos);
                    }
                }
            }
        }
        Iterator<BlockPos> iter = blockList.iterator();
        while (iter.hasNext())
        {
            BlockPos pos = iter.next();
            player.level.setBlock(pos, Blocks.GLASS.defaultBlockState(), Constants.BlockFlags.DEFAULT);
            iter.remove();
        }
        return true;
    }
}
