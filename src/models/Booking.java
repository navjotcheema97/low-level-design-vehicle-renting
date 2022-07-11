package models;

public class Booking extends Id {
    private String vehicleId;
    private int startDate;
    private int endDate;
    private double amount;

    public Booking(String bookingId, String vehicleId, int startDate, int endDate, double amount) {
        super(bookingId);
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public int getStartDate() {
        return startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public double getAmount() {
        return amount;
    }
}
