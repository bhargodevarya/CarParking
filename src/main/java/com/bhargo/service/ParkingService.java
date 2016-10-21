package com.bhargo.service;

import com.bhargo.model.Vehicle;

/**
 * Created by bhargo on 20/10/16.
 */
public interface ParkingService {

    boolean park(final Vehicle vehicle);
    boolean unPark(final Vehicle vehicle);
}
