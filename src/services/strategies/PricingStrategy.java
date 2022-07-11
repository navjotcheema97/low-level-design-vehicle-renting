package services.strategies;

import models.Vehicle;

public interface PricingStrategy {

    double getPrice(Vehicle vehicle, int startTime, int endTime);

}
