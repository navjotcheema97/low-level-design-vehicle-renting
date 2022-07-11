package services.console;

import services.VehicleRentingService;

import java.util.List;

public interface ICommand {

    void execute(List<String> inputList, VehicleRentingService vehicleRentingService);

    boolean matches(List<String> inputList);
}
