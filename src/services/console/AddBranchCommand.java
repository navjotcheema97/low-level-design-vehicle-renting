package services.console;

import models.VehicleType;
import services.VehicleRentingService;
import services.factory.VehicleTypeFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddBranchCommand implements ICommand {
    @Override
    public void execute(List<String> inputList, VehicleRentingService vehicleRentingService) {
        String branchId = inputList.get(1);
        List<String> vehicleStrings = Arrays.asList(inputList.get(2).split(","));
        List<VehicleType> availableVehicleTypes = vehicleStrings.stream()
                .map(VehicleTypeFactory::getVehicleTypeFromString).collect(Collectors.toList());
        try {
            vehicleRentingService.onBoardBranch(branchId, availableVehicleTypes);
            System.out.println("TRUE");
        } catch (Exception e) {
            System.out.println("FALSE");
        }
    }

    @Override
    public boolean matches(List<String> inputList) {
        return inputList.get(0).equals(CommandTypes.ADD_BRANCH.toString());
    }
}
