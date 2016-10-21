package com.bhargo;

import com.bhargo.model.*;
import com.bhargo.service.ParkingService;
import com.bhargo.service.ParkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by bhargo on 20/10/16.
 */
@SpringBootApplication
public class Main implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private CarPark carPark;

    @Override
    public void run(String... args) throws Exception {
        //carPark.showStatus();
        //System.out.println(carPark);
        //parkingService.park(new Vehicle(VEHICLE_TYPE.CAR));

        List<Vehicle> entryVehicles = Arrays.asList(new Vehicle[]{new Vehicle(VEHICLE_TYPE.CAR,"1"),new Vehicle(VEHICLE_TYPE.CAR, "2"),
                new Vehicle(VEHICLE_TYPE.CAR, "3"),new Vehicle(VEHICLE_TYPE.CAR, "4"),
                new Vehicle(VEHICLE_TYPE.CAR, "5"),new Vehicle(VEHICLE_TYPE.CAR, "6"),
                new Vehicle(VEHICLE_TYPE.CAR, "7"),new Vehicle(VEHICLE_TYPE.CAR, "8"),
                new Vehicle(VEHICLE_TYPE.CAR, "9"),new Vehicle(VEHICLE_TYPE.CAR, "10"),
                new Vehicle(VEHICLE_TYPE.CAR, "11"),new Vehicle(VEHICLE_TYPE.CAR, "12"),
                new Vehicle(VEHICLE_TYPE.CAR, "13")});

        int[] entryIndex = new int[1];

        List<Vehicle> exitVehicles = Arrays.asList(new Vehicle[]{new Vehicle(VEHICLE_TYPE.CAR,"1"),new Vehicle(VEHICLE_TYPE.CAR, "2"),
                new Vehicle(VEHICLE_TYPE.CAR, "3"),new Vehicle(VEHICLE_TYPE.CAR, "4"),
                new Vehicle(VEHICLE_TYPE.CAR, "5")});

        int[] exitIndex = new int[1];

        new Thread(() -> {
            ExecutorService executorService = Executors.newFixedThreadPool(12);
            Future<Boolean> status = null;
            for (int i =0;i<=12;i++) {
                entryIndex[0] = i;
                status = executorService.submit(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        //System.out.println(Thread.currentThread().getName() + " is parking " +  entryIndex[0]);
                        Thread.sleep(1000);
                        return parkingService.park(entryVehicles.get(entryIndex[0]));
                    }
                });
                try {
                    System.out.println(entryVehicles.get(entryIndex[0]) + " has been parked " + status.get() + " at " + new Date());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Thread.sleep(3000);

        ExecutorService exitService = Executors.newFixedThreadPool(2);
        Future<Boolean> exitStatus = null;
        for (int i =0;i<7;i++) {
            exitIndex[0] = i;
            exitStatus = exitService.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    Thread.sleep(2000);
                    return parkingService.unPark(exitVehicles.get(exitIndex[0]));
                }
            });
            while (!exitStatus.isDone()) {

            }
            //System.out.println("<<<<<<<<<vehicle has been unparked " + exitStatus.get() + " at " + new Date());
        }

        //Thread.sleep(5000);
        //carPark.showStatus();
    }

    @Bean
    public ParkingService parkingService() {
        return new ParkingServiceImpl(carPark());
    }

    @Bean
    public CarPark carPark() {
        List<Level> levels = new ArrayList<>(1);
        levels.add(level());
        return new CarPark(false, levels, 10);
    }

    @Bean
    public Level level() {
        List<Slot> slots = new ArrayList<>(10);
        for (int i =0;i<10;i++) {
            slots.add(new Slot(true, SLOT_TYPE.CAR));
        }
        return new Level(slots);
    }
}
