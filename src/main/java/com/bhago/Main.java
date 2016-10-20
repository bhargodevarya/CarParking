package com.bhago;

import com.bhago.model.*;
import com.bhago.service.ParkingService;
import com.bhago.service.ParkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.BootGlobalAuthenticationConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        Future<Boolean> status = null;
        for (int i =0;i<=12;i++) {
            status = executorService.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return parkingService.park(new Vehicle(VEHICLE_TYPE.CAR));
                }
            });
            System.out.println(status.get());
        }
        /*while (!status.isDone()) {
            System.out.println(status.get());
        }
        new Thread(() -> {parkingService.park(new Vehicle(VEHICLE_TYPE.CAR));}).start();
        new Thread(() -> {parkingService.park(new Vehicle(VEHICLE_TYPE.CAR));}).start();
        new Thread(() -> {parkingService.park(new Vehicle(VEHICLE_TYPE.CAR));}).start();*/
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
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
