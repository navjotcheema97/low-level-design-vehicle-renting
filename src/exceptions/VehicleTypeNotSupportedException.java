package exceptions;

public class VehicleTypeNotSupportedException extends RentalVehicleExceptions {
    public VehicleTypeNotSupportedException(String message) {
        super(message);
    }
}
