package com.example.block_clover.spells.slash;

import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.data.ability.AbilityDataCapability;
import com.example.block_clover.data.ability.IAbilityData;
import com.example.block_clover.entities.projectiles.slash.DeathScytheProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class DeathScytheAbility extends Ability {
    public static final DeathScytheAbility INSTANCE = new DeathScytheAbility();

    public DeathScytheAbility()
    {
        super("Death Scythe", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a scythe of death");
        this.setMaxCooldown(5);
        this.setmanaCost(5);
        this.setExperiencePoint(10);
        this.setExperienceGainLevelCap(10);
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
                    projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1.5f);
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
    }}
