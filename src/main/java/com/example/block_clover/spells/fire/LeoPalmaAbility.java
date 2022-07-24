package com.example.block_clover.spells.fire;

import com.example.block_clover.api.Beapi;
import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class LeoPalmaAbility extends Ability {
    public static final LeoPalmaAbility INSTANCE = new LeoPalmaAbility();

    private List<LivingEntity> entities = new ArrayList<LivingEntity>();

    public LeoPalmaAbility()
    {
        super("Leo Palma", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Engulfs the entity you're looking at with flames");
        this.setMaxCooldown(10);
        this.setmanaCost(30);
        this.setExperiencePoint(30);
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        RayTraceResult mop = Beapi.rayTraceBlocksAndEntities(player, 10);

        double i = mop.getLocation().x;
        double j = mop.getLocation().y - (mop instanceof EntityRayTraceResult ? 1 : 0);
        double k = mop.getLocation().z;

        List<LivingEntity> targets = Beapi.getEntitiesNear(new BlockPos(i, j, k), player.level, 5, LivingEntity.class);
        targets.remove(player);
        this.entities.addAll(targets);


        for(LivingEntity target : this.entities)
        {
            double targetX = target.getX();
            double targetY = target.getY();
            double targetZ = target.getZ();
            double playerX = player.getX();
            double playerY = player.getY();
            double playerZ = player.getZ();
            double distance = Math.sqrt(Math.pow(targetX - playerX, 2) + Math.pow(targetZ - playerZ, 2));
            target.addEffect(new EffectInstance(ModEffects.LEO_PALMA.get(), 80, 0));
        }

        return true;
    }

}
