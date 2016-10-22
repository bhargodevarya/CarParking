package com.bhargo.exitpoint;

import com.bhargo.model.Vehicle;

import java.util.Queue;

/**
 * Created by bhargo on 2V1/10/16.
 */
public class ExitBarricade implements Runnable{


    private Queue<Vehicle> vehicleQueue;

    public ExitBarricade(Queue<Vehicle> vehicleQueue) {
        this.vehicleQueue = vehicleQueue;
    }

    @Override
    public void run() {

    }
}
