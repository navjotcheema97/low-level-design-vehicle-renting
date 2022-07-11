package services.console;

import models.Vehicle;
import services.VehicleRentingService;

import java.util.List;

public class DisplayVehicleCommand implements ICommand {
    @Override
    public void execute(List<String> inputList, VehicleRentingService vehicleRentingService) {
        String branchId = inputList.get(1);
        int startTime = Integer.parseInt(inputList.get(2));
        int endTime = Integer.parseInt(inputList.get(3));
        List<Vehicle> availableVehicles = vehicleRentingService.getAllAvailableVehicle(branchId, startTime, endTime);
        for(Vehicle vehicle: availableVehicles){
            System.out.print(vehicle.getId() + ", ");
        }
    }

    @Override
    public boolean matches(List<String> inputList) {
        return inputList.get(0).equals(CommandTypes.DISPLAY_VEHICLES.toString());
    }
}
