package com.yuanno.block_clover.entities.goals;

import com.yuanno.block_clover.entities.BCsummon;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.passive.TameableEntity;

import java.util.EnumSet;

public class OwnerHurtTargetGoal extends TargetGoal {

    private final BCsummon tameAnimal;
    private LivingEntity ownerLastHurt;
    private int timestamp;

    public OwnerHurtTargetGoal(BCsummon p_i1668_1_) {
        super(p_i1668_1_, false);
        this.tameAnimal = p_i1668_1_;
        this.setFlags(EnumSet.of(Flag.TARGET));
    }

    public boolean canUse() {

        LivingEntity livingEntity = this.tameAnimal.getOwner();
        this.ownerLastHurt = livingEntity.getLastHurtMob();
        int lvt_2_1_ = livingEntity.getLastHurtMobTimestamp();
        return lvt_2_1_ != this.timestamp && this.canAttack(this.ownerLastHurt, EntityPredicate.DEFAULT);
            
        
        
    }

    public void start() {
        this.mob.setTarget(this.ownerLastHurt);
        LivingEntity livingEntity = this.tameAnimal.getOwner();
        if (livingEntity != null) {
            this.timestamp = livingEntity.getLastHurtMobTimestamp();
        }

        super.start();
    }
}
