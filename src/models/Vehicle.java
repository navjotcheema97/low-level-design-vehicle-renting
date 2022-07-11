package models;

public class Vehicle extends Id {
    private VehicleType vehicleType;
    private String branchId;
    private double pricePerHour;


    public Vehicle(String vehicleId, VehicleType vehicleType, String branchId, double pricePerHour) {
        super(vehicleId);
        this.vehicleType = vehicleType;
        this.branchId = branchId;
        this.pricePerHour = pricePerHour;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getBranchId() {
        return branchId;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }
}
