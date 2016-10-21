package com.bhargo.service;

import com.bhargo.model.CarPark;
import com.bhargo.model.Level;
import com.bhargo.model.Slot;
import com.bhargo.model.Vehicle;

import java.util.Date;
import java.util.List;

/**
 * Created by bhargo on 20/10/16.
 */
public class ParkingServiceImpl implements ParkingService {

    private final CarPark carPark;

    public ParkingServiceImpl(CarPark carPark) {
        this.carPark = carPark;
    }

    @Override
    public boolean park(Vehicle vehicle) {
        Slot slot;
        if(carPark.isSpaceAvailable()) {
            slot = carPark.getLevels().get(0).getSlotList()
                    .stream().filter(n -> n.isAvailable())
                    .findFirst().get();
            slot.setAvailable(false);
            slot.setStartTime(new Date());
            slot.setVehicle(vehicle);
            return true;
        }
        return false;
    }

    @Override
    public boolean unPark(Vehicle vehicle) {

        Slot slot;
        List<Level> levels = carPark.getLevels();
        Date exitTime = new Date();
        Date entryTime;
        for (Level level: levels) {
            slot = level.getSlotList().stream().filter(n -> Boolean.FALSE.equals(n.isAvailable()) && n.getVehicle().equals(vehicle))
                    .findFirst().get();
            if(slot != null) {
                entryTime = slot.getStartTime();
                slot.setAvailable(true);
                slot.setEndTime(exitTime);
                slot.setVehicle(null);
                System.out.println(vehicle + " entered at " + entryTime + " exited at " + exitTime);
                System.out.println(carPark.getLevels().get(0).getSlotList());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }

        return false;
    }
}
