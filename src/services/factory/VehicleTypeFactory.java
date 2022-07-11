package services.factory;

import models.Vehicle;
import models.VehicleType;

public class VehicleTypeFactory {

    public static VehicleType getVehicleTypeFromString(String vehicleString){
        VehicleType vehicleType = VehicleType.UNKNOWN;
        switch (vehicleString) {
            case "CAR": vehicleType = VehicleType.CAR;
                break;
            case "BIKE": vehicleType = VehicleType.BIKE;
                break;
            case "VAN": vehicleType = VehicleType.VAN;
                break;
            case "BUS": vehicleType = VehicleType.BUS;
                break;
        }
        return vehicleType;
    }
}
