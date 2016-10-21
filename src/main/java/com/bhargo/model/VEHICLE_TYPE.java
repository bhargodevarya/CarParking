package com.bhargo.model;

/**
 * Created by hadoop on 20/10/16.
 */
public enum VEHICLE_TYPE {
    CAR("car"), TRUCK("truck");

    private String type;

    VEHICLE_TYPE(String type) {
        this.type = type;
    }
}
