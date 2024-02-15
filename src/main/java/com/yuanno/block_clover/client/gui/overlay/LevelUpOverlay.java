package com.yuanno.block_clover.client.gui.overlay;

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
public class LevelUpOverlay extends AbstractGui {
    int previousLevel = 1;
    int tickCount;
    int tickCountSpell;
    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event)
    {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        assert player != null;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        String levelUp = "You leveled up!";
        if (previousLevel < entityStats.getLevel())
        {
            drawString(event.getMatrixStack(), Minecraft.getInstance().font, TextFormatting.BOLD + levelUp, 310, 30, -1);
            int tickCountPlus = player.tickCount;
            if ((tickCountPlus - tickCount) > 55)
            {
                previousLevel = entityStats.getLevel();
            }
        }
        else
        {
            tickCount = player.tickCount;
        }
    }
    @SubscribeEvent
    public void renderOverlaySpell(RenderGameOverlayEvent.Post event)
    {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        assert player != null;
        IEntityStats propsEntity = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        String spellEvolution = "";
        /*
        for (int i = 0; i < abilityData.getEquippedAbilities().size(); i++)
        {
            if (abilityData.getEquippedAbility(i) != null
                    && abilityData.getEquippedAbility(i).isOnCooldown()
                    && abilityData.getEquippedAbility(i).getEvolutionCost() != 0
                    && (int) propsEntity.getExperienceSpell(abilityData.getEquippedAbility(i).getName()) == abilityData.getEquippedAbility(i).getEvolutionCost()
                    && !abilityData.getEquippedAbility(i).isEvolved())
            {
                spellEvolution = abilityData.getEquippedAbility(i).getName() + " has evolved!";
                drawString(event.getMatrixStack(), Minecraft.getInstance().font, TextFormatting.BOLD + spellEvolution, 30, 30, -1);
                int tickCountEvolution = player.tickCount + 80;
                if (player.tickCount > tickCountEvolution)
                    abilityData.getEquippedAbility(i).evolved(true);
            }
        }

         */
    }
}
