package com.bhargo.model;

import java.util.Objects;

/**
 * Created by bhargo on 22/10/16.
 */
public abstract class Request {
    private String vehicleId;

    public Request(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(vehicleId, request.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId);
    }

    @Override
    public String toString() {
        return "Request{" +
                "vehicleId='" + vehicleId + '\'' +
                '}';
    }
}
