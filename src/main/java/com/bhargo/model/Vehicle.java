package com.bhargo.model;

import java.util.Objects;

/**
 * Created by bhargo on 20/10/16.
 */
public class Vehicle {
    private VEHICLE_TYPE vehicle_type;
    private String regNum;

    public void setVehicle_type(VEHICLE_TYPE vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public Vehicle(VEHICLE_TYPE vehicle_type, String regNum) {
        this.vehicle_type = vehicle_type;
        this.regNum = regNum;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicle_type=" + vehicle_type +
                ", regNum='" + regNum + '\'' +
                '}';
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public Vehicle(VEHICLE_TYPE vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicle_type == vehicle.vehicle_type &&
                Objects.equals(regNum, vehicle.regNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicle_type, regNum);
    }

    public VEHICLE_TYPE getVehicle_type() {
        return vehicle_type;
    }
}
