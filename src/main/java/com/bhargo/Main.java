package com.bhargo;

import com.bhargo.entrypoint.Entry;
import com.bhargo.entrypoint.EntryBarricade;
import com.bhargo.exitpoint.Exit;
import com.bhargo.model.*;
import com.bhargo.service.*;
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

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private QueuingService<EntryRequest> entryRequestQueuingService;

    @Autowired
    private QueuingService<ExitRequest> exitRequestQueuingService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public BlockingQueue<EntryRequest> entryRequestBlockingQueue () {
        return new LinkedBlockingQueue<EntryRequest>();
    }

    @Bean
    public BlockingQueue<ExitRequest> exitRequestBlockingQueue () {
        return new LinkedBlockingQueue<ExitRequest>();
    }

    @Bean
    public QueuingService<EntryRequest> entryRequestQueuingService() {
        return new EntryQueuingServiceImpl(entryRequestBlockingQueue());
    }

    @Bean
    public QueuingService<ExitRequest> exitRequestQueuingService() {
        return new ExitQueuingServiceImpl(exitRequestBlockingQueue());
    }

    @Bean
    public EntryService entryService () {
        return  new EntryServiceImpl(entryRequestBlockingQueue(),parkingService());
    }

    @Bean
    public List<EntryBarricade> entryBarricades() {
        List<EntryBarricade> entryBarricades = new ArrayList<>();
        EntryBarricade entryBarricade;
        Runnable runnable;

        for (int i =0;i<=3;i++) {
            String barricadeId = "Barricade-"+i;
            runnable = new Runnable() {
                @Override
                public void run() {
                    ExecutorService entryGate1 = Executors.newSingleThreadExecutor();
                    for (int j =0;j<=9;j++) {
                        entryGate1.submit(() -> {
                            EntryRequest er = new EntryRequest(barricadeId, Thread.currentThread().getName()
                                    + " " + new Double(Math.random()));
                            entryRequestQueuingService.Queue(er);
                        });
                    }
                }
            };
            entryBarricade = new EntryBarricade("Barricade-"+i, runnable);
            entryBarricades.add(entryBarricade);
        }
        return entryBarricades;
    }

    @Bean
    public EntryBarricadeService entryBarricadeService() {
        return new EntryBarricadeService(entryBarricades(), (EntryServiceImpl)entryService());
    }

    @Bean
    public ParkingService parkingService() {

        List<Level> levels = new ArrayList<>(1);
        List<Slot> slots = new ArrayList<>(10);
        for (int i =0;i<10;i++) {
            slots.add(new Slot(true, SLOT_TYPE.CAR));
        }
        levels.add(new Level(slots));
        return new ParkingServiceImpl(new CarPark(false, levels, 10));
    }

    @Override
    public void run(String... args) throws Exception {

    }


    private void oldModel() throws Exception{


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
        boolean[] status = new boolean[1];

        new Thread(() -> {
            ExecutorService executorService = Executors.newFixedThreadPool(12);
            //Future<Boolean> status = null;
            for (int i =0;i<=12;i++) {
                entryIndex[0] = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Thread " + Thread.currentThread().getName() + " " + entryIndex[0]);
                        status[0] = parkingService.park(entryVehicles.get(entryIndex[0]));
                        System.out.println(entryVehicles.get(entryIndex[0]) + " has been parked " + status[0] + " at " + new Date());
                    }
                }).start();

                /*status = executorService.submit(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        //System.out.println(Thread.currentThread().getName() + " is parking " +  entryIndex[0]);
                        Thread.sleep(1000);
                        return parkingService.park(entryVehicles.get(entryIndex[0]));
                    }
                });*/
                try {
                    //System.out.println(entryVehicles.get(entryIndex[0]) + " has been parked " + status[0] + " at " + new Date());
                    //Thread.sleep(1000);
                } catch (Exception e) {
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

    /*@Bean
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
    }*/
}
