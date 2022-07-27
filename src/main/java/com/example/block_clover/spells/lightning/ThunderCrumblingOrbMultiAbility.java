package com.example.block_clover.spells.lightning;

import com.example.block_clover.api.ability.Ability;
import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.sorts.RepeaterAbility;
import com.example.block_clover.data.ability.AbilityDataCapability;
import com.example.block_clover.data.ability.IAbilityData;
import com.example.block_clover.entities.projectiles.light.LightBladeProjectile;
import com.example.block_clover.entities.projectiles.lightning.LightningOrbProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class ThunderCrumblingOrbMultiAbility extends RepeaterAbility {
    public static final ThunderCrumblingOrbMultiAbility INSTANCE = new ThunderCrumblingOrbMultiAbility();

    public ThunderCrumblingOrbMultiAbility()
    {
        super("Thunder Crumbling Orb Multi", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Shoots a big amount of lightning orbs");
        this.setMaxCooldown(12);
        this.setmanaCost(25);
        this.setExperiencePoint(25);
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

            try {
                if (ability instanceof ThunderGodGlovesAbility && ability.isContinuous())
                {
                    LightningOrbProjectile projectile = new LightningOrbProjectile(player.level, player);
                    player.level.addFreshEntity(projectile);
                    ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
                    projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1.5f, 1);
                    return true;
                }
                else
                {
                    player.sendMessage(new StringTextComponent("Need to put on your electric gloves!"), Util.NIL_UUID);
                    return false;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return false;
    }
}