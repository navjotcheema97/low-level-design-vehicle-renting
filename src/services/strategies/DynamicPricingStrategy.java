package services.strategies;

import models.Vehicle;
import models.VehicleType;
import repository.VehicleRepository;

public class DynamicPricingStrategy implements PricingStrategy {
    VehicleRepository vehicleRepository;

    public DynamicPricingStrategy() {
        this.vehicleRepository = VehicleRepository.getInstance();
    }

    @Override
    public double getPrice(Vehicle vehicle, int startTime, int endTime) {
        String branchId = vehicle.getBranchId();
        VehicleType vehicleType = vehicle.getVehicleType();
        double availabilityPercentage = (double) this.vehicleRepository.getAllAvailableVehiclesCount(
                branchId, vehicleType, startTime, endTime) / (double) this.vehicleRepository.getAllVehiclesCount(branchId, vehicleType);
        double priceFactor = 1.0;
        if(availabilityPercentage <= 0.2)
            priceFactor = 1.1;
        return priceFactor*(endTime-startTime)*vehicle.getPricePerHour();
    }

}
