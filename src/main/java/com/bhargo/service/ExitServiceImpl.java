package com.bhargo.service;

import com.bhargo.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bhargo on 20/10/16.
 */
public class ExitServiceImpl implements ExitService {

    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    @Autowired
    private ParkingService parkingService;

    @Override
    public void requestExit(Vehicle vehicle) {
        parkingService.unPark(vehicle);
    }
}
