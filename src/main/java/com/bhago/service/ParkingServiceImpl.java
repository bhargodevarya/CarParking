package com.bhago.service;

import com.bhago.model.CarPark;
import com.bhago.model.Vehicle;

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
        if(carPark.isSpaceAvailable()) {
            carPark.getLevels().get(0).getSlotList()
                    .stream().filter(n -> n.isAvailable())
                    .findFirst().get().setAvailable(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean unPark(Vehicle vehicle) {
        return false;
    }
}
