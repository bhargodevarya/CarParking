package com.bhargo.service;

import com.bhargo.model.EntryRequest;
import com.bhargo.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bhargo on 20/10/16.
 */
public class EntryServiceImpl implements EntryService {
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    private ParkingService parkingService;

    private final BlockingQueue<EntryRequest> parkingRequestBlockingQueue;

    public EntryServiceImpl(BlockingQueue<EntryRequest> parkingRequestBlockingQueue, ParkingService parkingService) {
        this.parkingRequestBlockingQueue = parkingRequestBlockingQueue;
        this.parkingService = parkingService;
    }

    @Override
    public void requestParking(Vehicle vehicle) {
        executorService.submit(() -> {
            parkingService.park(vehicle);
        });
    }

    //@PostConstruct
    public void pollQueue () throws InterruptedException {
        while (true) {
            //Thread.sleep(2000);
            System.out.println("Processing " + parkingRequestBlockingQueue.take());
        }
    }
}
