package com.yuanno.block_clover.items.clothes;

import com.yuanno.block_clover.init.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.function.Supplier;

public enum WitchHatBase implements IArmorMaterial {
    WITCH_HAT("witch_hat", 6, new int[]{0, 0, 0, 3}, 8, SoundEvents.ARMOR_EQUIP_LEATHER, 1F, 0.0F, () -> {
        return Ingredient.of(ModItems.WITCH_HAT_SHRED.get());
    }),;

    private static int[] health_for_slot = new int[]{13, 15, 16, 11};
    private final String name;
    private  int durabilityMultiplier;
    private  int[] slotProtections;
    private  int enchantmentValue;
    private  final SoundEvent sound;
    private  float toughness;
    private  float knockbackResistance;
    private  final LazyValue<Ingredient> repairIngredient;

    private WitchHatBase(String p_i231593_3_, int p_i231593_4_, int[] p_i231593_5_, int p_i231593_6_, SoundEvent p_i231593_7_, float p_i231593_8_, float p_i231593_9_, Supplier<Ingredient> p_i231593_10_) {
        this.name = p_i231593_3_;
        this.durabilityMultiplier = p_i231593_4_;
        this.slotProtections = p_i231593_5_;
        this.enchantmentValue = p_i231593_6_;
        this.sound = p_i231593_7_;
        this.toughness = p_i231593_8_;
        this.knockbackResistance = p_i231593_9_;
        this.repairIngredient = new LazyValue<>(p_i231593_10_);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return this.health_for_slot[slot.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slot) {
        return this.slotProtections[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public void setHealthForSlot(EquipmentSlotType slot, int health) {
        this.health_for_slot[slot.getIndex()] = health;
    }

    public void setDurabilityMultiplier(int multiplier) {
        this.durabilityMultiplier = multiplier;
    }

    public void setDefenseForSlot(int defense) {
        this.slotProtections[EquipmentSlotType.HEAD.getIndex()] = defense;
    }


    public void setEnchantmentValue(int value) {
        this.enchantmentValue = value;
    }

    public void setToughness(float toughness) {
        this.toughness = toughness;
    }

    public void setKnockbackResistance(float resistance) {
        this.knockbackResistance = resistance;
    }
}
