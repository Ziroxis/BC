package com.yuanno.block_clover.data.config;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ConfigCapability {


    @CapabilityInject(IConfig.class)
    public static final Capability<IConfig> INSTANCE = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IConfig.class, new Capability.IStorage<IConfig>()
        {
            @Override
            public INBT writeNBT(Capability<IConfig> capability, IConfig instance, Direction side)
            {
                CompoundNBT props = new CompoundNBT();

                props.putBoolean("pickUpItems", instance.getPickUpItems());

                return props;
            }

            @Override
            public void readNBT(Capability<IConfig> capability, IConfig instance, Direction side, INBT nbt)
            {
                CompoundNBT props = (CompoundNBT) nbt;

                instance.setPickUpItems(props.getBoolean("pickUpItems"));
            }
        }, () -> new ConfigBase());

    }
    public static IConfig get(final LivingEntity entity)
    {
        return entity.getCapability(INSTANCE, null).orElse(new ConfigBase());
    }
}
