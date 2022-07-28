package com.example.block_clover.spells.slash;

import com.example.block_clover.api.Beapi;
import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.sorts.RepeaterAbility;
import com.example.block_clover.data.ability.AbilityDataCapability;
import com.example.block_clover.data.ability.IAbilityData;
import com.example.block_clover.entities.projectiles.slash.DeathScytheProjectile;
import com.example.block_clover.entities.projectiles.wind.WindBladeProjectile;
import com.example.block_clover.spells.wind.WindBladeShowerAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class DeathScytheShowerAbility extends RepeaterAbility {
    public static final DeathScytheShowerAbility INSTANCE = new DeathScytheShowerAbility();

    public DeathScytheShowerAbility()
    {
        super("Death Scythe Shower", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a big amount of death scythes");
        this.setMaxCooldown(10);
        this.setmanaCost(20);
        this.setExperiencePoint(15);
        this.setExperienceGainLevelCap(20);
        this.setMaxRepeaterCount(7, 5);
        this.onUseEvent = this::onUseEvent;

    }

    private boolean onUseEvent(PlayerEntity player)
    {
        IAbilityData abilityProps = AbilityDataCapability.get(player);
        for (Ability ability : abilityProps.getEquippedAbilities(AbilityCategories.AbilityCategory.ALL))
        {
            if (ability == null)
                continue;

            try
            {
                if (ability instanceof SlashBladesAbility && ability.isContinuous())
                {
                    DeathScytheProjectile projectile = new DeathScytheProjectile(player.level, player);
                    player.level.addFreshEntity(projectile);
                    ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
                    projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1.5f, 2f);
                    return true;
                }
                else
                {
                    player.sendMessage(new StringTextComponent("Need to put on your slash blades!"), Util.NIL_UUID);
                    return false;
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return false;
    }
}
