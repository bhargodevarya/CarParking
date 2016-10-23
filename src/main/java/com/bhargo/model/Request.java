package com.bhargo.model;

import java.util.Objects;

/**
 * Created by bhargo on 22/10/16.
 */
public abstract class Request {
    private String barricadeId;
    private String vehicleId;

    public Request(String barricadeId, String vehicleId) {
        this.barricadeId = barricadeId;
        this.vehicleId = vehicleId;
    }

    public Request(String barricadeId) {
        this.barricadeId = barricadeId;
    }

    public String getBarricadeId() {
        return barricadeId;
    }

    public void setBarricadeId(String barricadeId) {
        this.barricadeId = barricadeId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(barricadeId, request.barricadeId) &&
                Objects.equals(vehicleId, request.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barricadeId, vehicleId);
    }

    @Override
    public String toString() {
        return "Request{" +
                "barricadeId='" + barricadeId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                '}';
    }
}
