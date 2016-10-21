package com.bhargo.service;

import com.bhargo.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bhargo on 20/10/16.
 */
public class EntryServiceImpl implements EntryService {
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    @Autowired
    private ParkingService parkingService;

    @Override
    public void requestParking(Vehicle vehicle) {
        executorService.submit(() -> {
            parkingService.park(vehicle);
        });
    }
}
