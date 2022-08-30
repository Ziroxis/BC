package com.yuanno.block_clover.entities.goals;

import com.yuanno.block_clover.entities.BCsummon;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.passive.TameableEntity;

import java.util.EnumSet;

public class OwnerHurtByTargetGoal extends TargetGoal {

    private final BCsummon tameAnimal;
    private LivingEntity ownerLastHurtBy;
    private int timestamp;

    public OwnerHurtByTargetGoal(BCsummon p_i1667_1_) {
        super(p_i1667_1_, false);
        this.tameAnimal = p_i1667_1_;
        this.setFlags(EnumSet.of(Flag.TARGET));
    }

    public boolean canUse() {
        
        LivingEntity livingEntity = this.tameAnimal.getOwner();
        this.ownerLastHurtBy = livingEntity.getLastHurtByMob();
        int lvt_2_1_ = livingEntity.getLastHurtByMobTimestamp();
        return lvt_2_1_ != this.timestamp && this.canAttack(this.ownerLastHurtBy, EntityPredicate.DEFAULT);
            
        
    }

    public void start() {
        this.mob.setTarget(this.ownerLastHurtBy);
        LivingEntity livingEntity = this.tameAnimal.getOwner();
        if (livingEntity != null) {
            this.timestamp = livingEntity.getLastHurtByMobTimestamp();
        }

        super.start();
    }
}
