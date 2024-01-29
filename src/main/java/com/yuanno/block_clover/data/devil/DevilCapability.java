package com.yuanno.block_clover.data.devil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DevilCapability {

    @CapabilityInject(IDevil.class)
    public static final Capability<IDevil> INSTANCE = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IDevil.class, new Capability.IStorage<IDevil>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IDevil> capability, IDevil instance, Direction side) {
                CompoundNBT props = new CompoundNBT();

                props.putString("innateDevilName", instance.getDevil());

                // Save the List
                CompoundNBT listNBT = new CompoundNBT();
                List<String> myList = instance.getControlledDevilList();
                ListNBT listTag = new ListNBT();
                for (String value : myList)
                    listTag.add(StringNBT.valueOf(value));

                listNBT.put("devil_list_data", listTag);
                props.put("devil_list", listNBT);


                return props;
            }

            @Override
            public void readNBT(Capability<IDevil> capability, IDevil instance, Direction side, INBT nbt) {
                CompoundNBT props = (CompoundNBT) nbt;

                instance.setDevil(props.getString("innateDevilName"));

                if (!instance.getControlledDevilList().isEmpty())
                {
                    CompoundNBT listNBT = props.getCompound("devil_list");
                    ListNBT listTag = listNBT.getList("devil_list_data", Constants.NBT.TAG_STRING);
                    ArrayList<String> myList = new ArrayList<>();
                    for (int i = 0; i < listTag.size(); i++) {
                        String value = listTag.getString(i);
                        myList.add(value);
                        instance.addControlledDevilList(value);
                    }
                }
            }
        }, () -> new DevilBase());
    }

    public static IDevil get(final LivingEntity entity)
    {
        return entity.getCapability(INSTANCE, null).orElse(new DevilBase());
    }
}
