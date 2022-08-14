package com.yuanno.block_clover.client.gui;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class SpellUnlockOverlay extends AbstractGui {
    int unlockedSpell = 1;
    int previousLevel = 1;
    int tickCount;
    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        assert player != null;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        String spellUnlocked = "You unlocked a spell!";
        if (!entityStats.hasGrimoire())
            return;
        if (previousLevel < entityStats.getLevel() && unlockedSpell < abilityData.countUnlockedAbilities(AbilityCategories.AbilityCategory.ATTRIBUTE))
        {
            drawString(event.getMatrixStack(), Minecraft.getInstance().font, TextFormatting.BOLD + spellUnlocked, 273, 20, -1);
            int tickCountPlus = player.tickCount;
            if ((tickCountPlus - tickCount) > 55)
            {
                previousLevel = entityStats.getLevel();
                unlockedSpell = abilityData.countUnlockedAbilities(AbilityCategories.AbilityCategory.ATTRIBUTE);
            }
        }
        else
        {
            tickCount = player.tickCount;
        }
    }
}
