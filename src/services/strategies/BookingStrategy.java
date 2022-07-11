package services.strategies;

import exceptions.BookingFailedException;
import exceptions.BookingFullException;
import models.Booking;
import models.VehicleType;

public interface BookingStrategy {

    public Booking book(String branchId, VehicleType vehicleType,PricingStrategy pricingStrategy, int startTime, int endTime)
            throws BookingFullException, BookingFailedException;

}
