package com.yuanno.block_clover.data.devil;

import java.util.List;

public interface IDevil {

    void fullReset();

    List<String> getControlledDevilList();
    void setControlledDevilList(List<String> controlledDevilList);
    void addControlledDevilList(String devilListAddition);
    void setDevil(String devil);
    String getDevil();
}
