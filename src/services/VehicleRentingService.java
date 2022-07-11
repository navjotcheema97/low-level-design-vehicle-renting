package services;

import dtos.BranchDTO;
import exceptions.*;
import models.Booking;
import models.Branch;
import models.Vehicle;
import models.VehicleType;
import repository.BookingRepository;
import repository.BranchRepository;
import repository.VehicleRepository;
import services.strategies.BookingStrategy;
import services.strategies.PricingStrategy;

import java.util.List;

public class VehicleRentingService {
    private BranchRepository branchRepository;
    private VehicleRepository vehicleRepository;
    private BookingRepository bookingRepository;
    private BookingStrategy bookingStrategy;
    private PricingStrategy pricingStrategy;

    private static VehicleRentingService instance = null;

    private VehicleRentingService(BookingStrategy bookingStrategy, PricingStrategy pricingStrategy) {
        this.branchRepository = BranchRepository.getInstance();
        this.vehicleRepository = VehicleRepository.getInstance();
        this.bookingRepository = BookingRepository.getInstance();
        this.bookingStrategy = bookingStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public static VehicleRentingService getInstance(BookingStrategy bookingStrategy,
                                                    PricingStrategy pricingStrategy){
        if(instance == null){
            synchronized (VehicleRentingService.class){
                if(instance == null){
                    instance = new VehicleRentingService(bookingStrategy, pricingStrategy);
                }
            }
        }
        return instance;
    }

    public BranchDTO onBoardBranch(String branchId, List<VehicleType> supportedVehicleTypes) throws BranchAlreadyExistsException {
        Branch branch = new Branch(branchId);
        return this.branchRepository.addBranch(branch, supportedVehicleTypes);
    }

    public Vehicle addVehicle(String branchId, VehicleType vehicleType,
                              String vehicleId, double price) throws VehicleAlreadyPresent, VehicleTypeNotSupportedException {
        if(this.branchRepository.isVehicleTypeSupportedByBranch(branchId, vehicleType)) {
            Vehicle vehicle = new Vehicle(vehicleId, vehicleType, branchId, price);
            return this.vehicleRepository.addVehicle(vehicle);
        }else{
            throw new VehicleTypeNotSupportedException("Invalid vehicle type for branch");
        }
    }

    public Booking bookVehicle(String branchId, VehicleType vehicleType, int startTime, int endTime) throws BookingFullException, BookingFailedException {
        return this.bookingStrategy.book(branchId, vehicleType, this.pricingStrategy, startTime, endTime);
    }

    public  List<Vehicle> getAllAvailableVehicle(String branchId, int startTime, int endTime){
        return this.vehicleRepository.getAllAvailableVehicleForBranch(branchId, startTime, endTime);
    }

}
