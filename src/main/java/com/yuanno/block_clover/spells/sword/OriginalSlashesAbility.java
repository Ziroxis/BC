package com.yuanno.block_clover.spells.sword;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityDamageKind;
import com.yuanno.block_clover.api.ability.sorts.RepeaterAbility;
import com.yuanno.block_clover.entities.projectiles.sword.OriginalSlashProjectile;
import com.yuanno.block_clover.items.weapons.DemonDwellerItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class OriginalSlashesAbility extends RepeaterAbility {

    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Original slashes", AbilityCategories.AbilityCategory.ATTRIBUTE, OriginalSlashesAbility.class)
            .setDescription("Shoots a big amount of slashes made by your sword")
            .setDamageKind(AbilityDamageKind.ELEMENTAL)
            .setDependencies(OriginalMagicDwellerAbility.INSTANCE)
            .build();

    public OriginalSlashesAbility()
    {
        super(INSTANCE);
        this.setMaxCooldown(10);
        this.setmanaCost(20);
        this.setMaxRepeaterCount(15, 4);
        this.setExperiencePoint(20);
        this.setExperienceGainLevelCap(20);

        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        if (player.getMainHandItem().getItem().asItem() instanceof DemonDwellerItem || player.getOffhandItem().getItem().asItem() instanceof DemonDwellerItem)
        {
            OriginalSlashProjectile projectile = new OriginalSlashProjectile(player.level, player);
            player.level.addFreshEntity(projectile);
            ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
            projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 3, 20);

            return true;
        }
        else
        {
            player.sendMessage(new StringTextComponent("Need to hold the demon dweller sword!"), Util.NIL_UUID);
            this.endContinuity(player);
            return false;
        }
    }
}
