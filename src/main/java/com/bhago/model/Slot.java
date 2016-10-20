package com.bhago.model;

/**
 * Created by hadoop on 20/10/16.
 */
public class Slot {

    private boolean available;
    private SLOT_TYPE slot_type;

    public Slot(boolean available, SLOT_TYPE slot_type) {
        this.available = available;
        this.slot_type = slot_type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setSlot_type(SLOT_TYPE slot_type) {
        this.slot_type = slot_type;
    }

    public SLOT_TYPE getSlot_type() {
        return slot_type;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "available=" + available +
                ", slot_type=" + slot_type +
                '}';
    }
}
