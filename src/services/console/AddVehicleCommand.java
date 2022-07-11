package services.console;

import exceptions.VehicleAlreadyPresent;
import exceptions.VehicleTypeNotSupportedException;
import models.VehicleType;
import services.VehicleRentingService;
import services.factory.VehicleTypeFactory;

import java.util.List;

public class AddVehicleCommand implements ICommand {
    @Override
    public void execute(List<String> inputList, VehicleRentingService vehicleRentingService) {
        String branchId = inputList.get(1);
        VehicleType vehicleType = VehicleTypeFactory.getVehicleTypeFromString(inputList.get(2));
        String vehicleId = inputList.get(3);
        double pricePerHr = Double.parseDouble(inputList.get(4));
        try {
            vehicleRentingService.addVehicle(branchId, vehicleType, vehicleId, pricePerHr);
            System.out.println("TRUE");
        } catch (Exception e) {
            System.out.println("FALSE");
        }
    }

    @Override
    public boolean matches(List<String> inputList) {
        return inputList.get(0).equals(CommandTypes.ADD_VEHICLE.toString());
    }
}
