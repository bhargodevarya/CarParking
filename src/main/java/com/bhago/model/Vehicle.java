package com.bhago.model;

/**
 * Created by bhargo on 20/10/16.
 */
public class Vehicle {
    private VEHICLE_TYPE vehicle_type;

    public Vehicle(VEHICLE_TYPE vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public VEHICLE_TYPE getVehicle_type() {
        return vehicle_type;
    }
}
