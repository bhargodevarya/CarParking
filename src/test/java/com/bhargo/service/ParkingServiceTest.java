package com.bhargo.service;

import com.bhargo.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhargo on 20/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ParkingServiceTest {

    @Mock
    private ParkingService parkingService;

    private CarPark carPark;


    @Before
    public void setup () {
        List<Level> levels = new ArrayList<>();
        List<Slot> slots = new ArrayList<>(10);
        for (int i =0; i<9; i++) {
            slots.add(new Slot(false, SLOT_TYPE.CAR));
        }
        slots.add(new Slot(true, SLOT_TYPE.CAR));
        Level level = new Level(slots);
        levels.add(level);
        carPark = new CarPark(false,levels,5);
    }

    /*@Test
    public void parkSuccessFullTest () {
        boolean result = parkingService.park(new Vehicle(VEHICLE_TYPE.CAR));
        Assert.assertTrue(result);
    }

    @Test
    public void parkFailureTest () {

    }*/

    @Test
    public void CarParkFullTest () {
        Assert.assertFalse(carPark.isSpaceAvailable());
    }
}
