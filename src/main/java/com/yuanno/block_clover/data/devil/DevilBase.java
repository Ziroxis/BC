package com.yuanno.block_clover.data.devil;

import java.util.ArrayList;
import java.util.List;

public class DevilBase implements IDevil {

    private String innateDevilName = "";
    List<String> controlledDevilList = new ArrayList<>();
    private double maxDevilMana = 0;
    private double devilMana = 0;


    @Override
    public void fullReset() {
        innateDevilName = "";
        controlledDevilList.clear();
    }

    @Override
    public List<String> getControlledDevilList()
    {
        return controlledDevilList;
    }

    @Override
    public void setControlledDevilList(List<String> controlledDevilList)
    {
        this.controlledDevilList = controlledDevilList;
    }

    @Override
    public void addControlledDevilList(String devilListAddition)
    {
        this.controlledDevilList.add(devilListAddition);
    }

    @Override
    public void setDevil(String devil)
    {
        this.innateDevilName = devil;
    }

    @Override
    public String getDevil()
    {
        return this.innateDevilName;
    }

    @Override
    public double getMaxDevilMana() {
        return this.maxDevilMana;
    }

    @Override
    public void setMaxDevilMana(double maxDevilMana) {
        this.maxDevilMana = maxDevilMana;
    }

    @Override
    public void alterMaxDevilMana(double maxDevilMana) {
        this.maxDevilMana += maxDevilMana;
    }

    @Override
    public double getDevilMana() {
        return this.devilMana;
    }

    @Override
    public void setDevilMana(double devilMana) {
        this.devilMana = devilMana;
    }

    @Override
    public void alterDevilMana(double devilMana) {
        this.devilMana += devilMana;
    }
}
