package com.bhargo.model;

import java.util.List;

/**
 * Created by hadoop on 20/10/16.
 */
public class Level {

    private List<Slot> slotList;

    public Level(List<Slot> slotList) {
        this.slotList = slotList;
    }

    public List<Slot> getSlotList() {
        return slotList;
    }
}
