package com.yuanno.block_clover.items.artifacts;

import com.yuanno.block_clover.init.ModItemGroup;
import com.yuanno.block_clover.init.ModTiers;
import com.yuanno.block_clover.items.MagicWeapon;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class AbyssalScytheItem extends MagicWeapon {
    public AbyssalScytheItem() {
        super(ModTiers.WEAPON, 7, 1f, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_WEAPONS).stacksTo(1));
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("Hold \\u00A7eSHIFT\\u00A7r for more Information!"));
        } else {
            tooltip.add(new TranslationTextComponent("An old scythe found in the abyss"));
        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

    //Pushes enemies towards you and inflicts blindness
    //https://github.com/kwpugh/gobber/blob/1.18.2/src/main/java/com/kwpugh/gobber2/items/rings/ItemCustomRingAttraction.java#L86
    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker)
    {
        if (attacker instanceof PlayerEntity)
        {
            if (!(target.hasEffect(Effects.BLINDNESS)))
                target.addEffect(new EffectInstance(Effects.BLINDNESS, 80, 0));

            target.push((attacker.getX() - target.getX()) * 0.3, (attacker.getY() - target.getY() + 1) * 0.05, (attacker.getZ() - target.getZ()) * 0.3);
        }
        return super.hurtEnemy(itemStack, target, attacker);
    }


}
