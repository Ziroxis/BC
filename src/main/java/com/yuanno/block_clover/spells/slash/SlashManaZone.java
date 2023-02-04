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
        int randX = random.nextInt(8);
        int randY = random.nextInt(2);
        int randZ = random.nextInt(8);
        boolean x = false;
        boolean y = false;
        if (Beapi.RNGboolean())
            x = true;
        if (Beapi.RNGboolean())
            y = true;
        DeathScytheProjectile projectile = new DeathScytheProjectile(player.level, player);
        projectile.setDamage(12);
        if (x && y)
            projectile.setPos(player.getX() + randX, player.getY() + 0 + randY, player.getZ() + randZ);
        else if (x)
            projectile.setPos(player.getX() + randX, player.getY() + 0 + randY, player.getZ() - randZ);
        else if (y)
            projectile.setPos(player.getX() - randX, player.getY() + 0 + randY, player.getZ() + randZ);
        else
            projectile.setPos(player.getX() - randX, player.getY() + 0 + randY, player.getZ() - randZ);
        projectile.push((projectile.getX() - player.getX()) * 0.3, (projectile.getY() - player.getY()) * 0.1, (projectile.getZ() - player.getZ()) * 0.3);
        player.level.addFreshEntity(projectile);
        DeathScytheProjectile projectile2 = new DeathScytheProjectile(player.level, player);
        projectile2.setDamage(12);
        if (x && y)
            projectile2.setPos(player.getX() + randX, player.getY() + 0 + randY, player.getZ() + randZ);
        else if (x)
            projectile2.setPos(player.getX() + randX, player.getY() + 0 + randY, player.getZ() - randZ);
        else if (y)
            projectile2.setPos(player.getX() - randX, player.getY() + 0 + randY, player.getZ() + randZ);
        else
            projectile2.setPos(player.getX() - randX, player.getY() + 0 + randY, player.getZ() - randZ);
        projectile2.push((projectile2.getX() - player.getX()) * 0.3, (projectile2.getY() - player.getY()) * 0.1, (projectile2.getZ() - player.getZ()) * 0.3);
        player.level.addFreshEntity(projectile2);
        DeathScytheProjectile projectile3 = new DeathScytheProjectile(player.level, player);
        projectile3.setDamage(12);
        if (x && y)
            projectile3.setPos(player.getX() + randX, player.getY() + 0 + randY, player.getZ() + randZ);
        else if (x)
            projectile3.setPos(player.getX() + randX, player.getY() + 0 + randY, player.getZ() - randZ);
        else if (y)
            projectile3.setPos(player.getX() - randX, player.getY() + 0 + randY, player.getZ() + randZ);
        else
            projectile3.setPos(player.getX() - randX, player.getY() + 0 + randY, player.getZ() - randZ);
        projectile3.push((projectile3.getX() - player.getX()) * 0.3, (projectile3.getY() - player.getY()) * 0.1, (projectile3.getZ() - player.getZ()) * 0.3);
        player.level.addFreshEntity(projectile3);
        DeathScytheProjectile projectile4 = new DeathScytheProjectile(player.level, player);
        if (x && y)
            projectile4.setPos(player.getX() + randX, player.getY() + 0 + randY, player.getZ() + randZ);
        else if (x)
            projectile4.setPos(player.getX() + randX, player.getY() + 0 + randY, player.getZ() - randZ);
        else if (y)
            projectile4.setPos(player.getX() - randX, player.getY() + 0 + randY, player.getZ() + randZ);
        else
            projectile4.setPos(player.getX() - randX, player.getY() + 0 + randY, player.getZ() - randZ);
        projectile4.push((projectile4.getX() - player.getX()) * 0.3, (projectile4.getY() - player.getY()) * 0.1, (projectile4.getZ() - player.getZ()) * 0.3);
        projectile4.setDamage(12);
        player.level.addFreshEntity(projectile4);
        if (timer >= 480)
            this.stopContinuity(player);
    }

}
