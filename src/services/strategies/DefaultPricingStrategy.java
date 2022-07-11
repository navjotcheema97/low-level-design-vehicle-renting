package services.strategies;

import models.Vehicle;

public class DefaultPricingStrategy implements PricingStrategy {
    @Override
    public double getPrice(Vehicle vehicle, int startTime, int endTime) {
        return vehicle.getPricePerHour() * (endTime-startTime);
    }
}
