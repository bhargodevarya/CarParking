package com.bhago.model;

import java.util.List;

/**
 * Created by hadoop on 20/10/16.
 */
public class CarPark {

    private boolean multiLevel;
    private List<Level> levels;
    private int capacity;

    public CarPark(boolean multiLevel, List<Level> levels, int capacity) {
        this.multiLevel = multiLevel;
        this.levels = levels;
        this.capacity = capacity;
    }

    public boolean isMultiLevel() {
        return multiLevel;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isSpaceAvailable () {
        boolean[] available = new boolean[1];
        getLevels().stream().forEach(n -> {
            n.getSlotList().forEach(m -> {
                if(m.isAvailable()) {
                    available[0] = true;
                }
            });
        });
        return available[0];
    }

    public void showStatus() {
        getLevels().stream().forEach(n -> n.getSlotList().forEach(System.out::println));
    }
}
