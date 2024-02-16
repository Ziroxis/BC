package com.yuanno.block_clover.data.config;

public class ConfigBase implements IConfig {

    private boolean pickUpItems = false;

    @Override
    public void setPickUpItems(boolean flag)
    {
        this.pickUpItems = flag;
    }

    @Override
    public boolean getPickUpItems()
    {
        return this.pickUpItems;
    }
}
