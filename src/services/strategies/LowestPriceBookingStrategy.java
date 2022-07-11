package services.strategies;

import exceptions.BookingFailedException;
import exceptions.BookingFullException;
import models.Booking;
import models.Vehicle;
import models.VehicleType;
import repository.BookingRepository;
import repository.BranchRepository;
import repository.VehicleRepository;

import java.util.List;
import java.util.UUID;

public class LowestPriceBookingStrategy implements BookingStrategy{
    private VehicleRepository vehicleRepository;
    private BookingRepository bookingRepository;

    public LowestPriceBookingStrategy() {
        this.vehicleRepository = VehicleRepository.getInstance();
        this.bookingRepository = BookingRepository.getInstance();
    }

    @Override
    public Booking book(String branchId,
                        VehicleType vehicleType,
                        PricingStrategy pricingStrategy,
                        int startTime,
                        int endTime) throws BookingFullException, BookingFailedException {
        List<Vehicle> availableVehicles = this.vehicleRepository.getAllAvailableVehiclesByBranchAndType(branchId, vehicleType, startTime, endTime);
        if(availableVehicles.size() == 0){
            throw new BookingFullException("Vehicle type already booked");
        }
        Vehicle cheapestVehicle = availableVehicles.get(0);
        double cheapestVehiclePrice = pricingStrategy.getPrice(cheapestVehicle, startTime, endTime);
        for(Vehicle vehicle: availableVehicles){
            double currentVehicleCost = pricingStrategy.getPrice(vehicle, startTime, endTime);
            if(cheapestVehiclePrice > currentVehicleCost){
                cheapestVehiclePrice = currentVehicleCost;
                cheapestVehicle = vehicle;
            }
        }
        Booking booking = new Booking(UUID.randomUUID().toString(), cheapestVehicle.getId(),
                startTime, endTime, cheapestVehiclePrice);
        synchronized (cheapestVehicle) {
            if(this.bookingRepository.checkVehicleSlotAvailability(cheapestVehicle.getId(), startTime,endTime))
                this.bookingRepository.addBooking(booking);
            else throw new BookingFailedException("Booking failed please try again");
        }
        return booking;
    }
}
