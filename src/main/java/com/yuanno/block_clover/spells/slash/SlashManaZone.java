package com.yuanno.block_clover.spells.slash;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.entities.projectiles.slash.DeathScytheProjectile;
import net.minecraft.entity.player.PlayerEntity;

public class SlashManaZone extends ContinuousAbility {
    
    public static final SlashManaZone INSTANCE = new SlashManaZone();
    
    public SlashManaZone()
    {
        super("Slash mana zone", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Throws a multitude of slashes around you in a zone");
        this.setMaxCooldown(0);
        this.setmanaCost(0);
        this.duringContinuityEvent = this::duringContinuityEvent;
    }

    private void duringContinuityEvent(PlayerEntity player, int timer)
    {
        int randX = random.nextInt(16);
        int randY = random.nextInt(4);
        int randZ = random.nextInt(16);
        boolean x = false;
        boolean y = false;
        if (Beapi.RNGboolean())
            x = true;
        if (Beapi.RNGboolean())
            y = true;
        DeathScytheProjectile projectile = new DeathScytheProjectile(player.level, player);
        if (x && y)
            projectile.setPos(player.getX() + randX, player.getY() + 3 + randY, player.getZ() + randZ);
        else if (x)
            projectile.setPos(player.getX() + randX, player.getY() + 3 + randY, player.getZ() - randZ);
        else if (y)
            projectile.setPos(player.getX() - randX, player.getY() + 3 + randY, player.getZ() + randZ);
        else
            projectile.setPos(player.getX() - randX, player.getY() + 3 + randY, player.getZ() - randZ);
        projectile.push((projectile.getX() - player.getX()) * 0.3, (projectile.getY() - player.getY()) * 0.3, (projectile.getZ() - player.getZ()) * 0.3);
        player.level.addFreshEntity(projectile);

        if (timer >= 240)
            this.stopContinuity(player);
    }

}
