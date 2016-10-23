package com.bhargo.service;

import com.bhargo.entrypoint.EntryBarricade;
import com.bhargo.model.EntryRequest;
import com.bhargo.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bhargo on 20/10/16.
 */
public class EntryServiceImpl implements EntryService, Observable<EntryBarricade> {
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    private ParkingService parkingService;

    private final BlockingQueue<EntryRequest> parkingRequestBlockingQueue;
    private List<EntryBarricade> list;

    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public ParkingService getParkingService() {
        return parkingService;
    }

    public BlockingQueue<EntryRequest> getParkingRequestBlockingQueue() {
        return parkingRequestBlockingQueue;
    }

    public List<EntryBarricade> getList() {
        return list;
    }

    public EntryServiceImpl(BlockingQueue<EntryRequest> parkingRequestBlockingQueue, ParkingService parkingService, List<EntryBarricade> list) {
        this.parkingRequestBlockingQueue = parkingRequestBlockingQueue;
        this.parkingService = parkingService;
    }

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

    @PostConstruct
    public void pollQueue () throws InterruptedException {

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    EntryRequest entryRequest = parkingRequestBlockingQueue.take();
                    //code to get the parking slot

                    list.stream().filter(n -> n.getBarricadeId().equals(entryRequest.getBarricadeId()))
                            .findFirst().get().parkAt(" processed " + entryRequest.getVehicleId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void register(EntryBarricade entryBarricade) {
        if(list == null) {
            list = new ArrayList<>();
        }
        list.add(entryBarricade);
    }

    @Override
    public void unregister(EntryBarricade entryBarricade) {
        if(list != null) {
            list.remove(entryBarricade);
        }
    }
}
