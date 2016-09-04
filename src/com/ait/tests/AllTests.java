package com.ait.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BookingDataTableTest.class, BookingTest.class, DriverDataTableTest.class, DriverTest.class,
		EventBookingTest.class, EventsTest.class, UsersTest.class, VehicleDataTableTest.class,
		VehicleTest.class, EventsDataTableTest.class, EventBookingDataTableTest.class,
		DateUtilsTest.class, UsersDataTableTest.class})

public class AllTests {
}
