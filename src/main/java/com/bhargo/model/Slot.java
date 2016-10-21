package com.bhargo.model;

import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;

import java.util.Date;

/**
 * Created by hadoop on 20/10/16.
 */
public class Slot {

    private boolean available;
    private SLOT_TYPE slot_type;
    private Date startTime;
    private Date endTime;
    private Vehicle vehicle;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

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
