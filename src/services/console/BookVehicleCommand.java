package services.console;

import exceptions.BookingFailedException;
import exceptions.BookingFullException;
import models.Booking;
import models.VehicleType;
import services.VehicleRentingService;
import services.factory.VehicleTypeFactory;

import java.util.List;

public class BookVehicleCommand implements ICommand {
    @Override
    public void execute(List<String> inputList, VehicleRentingService vehicleRentingService) {
        String branchId = inputList.get(1);
        VehicleType vehicleType = VehicleTypeFactory.getVehicleTypeFromString(inputList.get(2));
        int startTime = Integer.parseInt(inputList.get(3));
        int endTime = Integer.parseInt(inputList.get(4));
        try {
            Booking booking = vehicleRentingService.bookVehicle(branchId, vehicleType, startTime, endTime);
            System.out.println(booking.getAmount());
        } catch (Exception e) {
            System.out.println(-1);
        }
    }

    @Override
    public boolean matches(List<String> inputList) {
        return inputList.get(0).equals(CommandTypes.BOOK.toString());
    }
}
