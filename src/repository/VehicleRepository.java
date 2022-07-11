package repository;

import exceptions.VehicleAlreadyPresent;
import models.Vehicle;
import models.VehicleType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VehicleRepository {
    private BookingRepository bookingRepository;
    private Map<String, Vehicle> vehicleMap;

    private static VehicleRepository instance = null;

    public static VehicleRepository getInstance(){
        if(instance == null){
            synchronized (VehicleRepository.class){
                if(instance == null){
                    instance = new VehicleRepository();
                }
            }
        }
        return instance;
    }

    private VehicleRepository() {
        this.vehicleMap = new HashMap<>();
        this.bookingRepository = BookingRepository.getInstance();
    }

    public Vehicle addVehicle(Vehicle vehicle) throws VehicleAlreadyPresent {
        if(!vehicleMap.containsKey(vehicle.getId())) {
            synchronized (this) {
                if(!vehicleMap.containsKey(vehicle.getId())) {
                    vehicleMap.put(vehicle.getId(), vehicle);
                    return vehicle;
                }
                else throw  new VehicleAlreadyPresent("Vehicle Id already present");
            }
        }
        else throw  new VehicleAlreadyPresent("Vehicle Id already present");
    }

    private List<Vehicle> filterAvailableVehicles(List<Vehicle> vehicles, int startTime, int endTime){
        return vehicles.stream().filter(
                vehicle -> this.bookingRepository.checkVehicleSlotAvailability(vehicle.getId(), startTime, endTime))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getAllAvailableVehiclesByBranchAndType(String branchId,
                                                 VehicleType vehicleType,
                                                 int startTime,
                                                 int endTime){
        List<Vehicle> allVehicles = this.vehicleMap.values().stream().filter((x) -> x.getBranchId().equals(branchId) &&
                x.getVehicleType().equals(vehicleType)).collect(Collectors.toList());
        return this.filterAvailableVehicles(allVehicles, startTime, endTime);
    }

    public int getAllAvailableVehiclesCount(String branchId,
                                            VehicleType vehicleType,
                                            int startDate,
                                            int endDate){
        return this.getAllAvailableVehiclesByBranchAndType(branchId, vehicleType, startDate,endDate).size();
    }

    public  int getAllVehiclesCount(String branchId, VehicleType vehicleType){
        return (int) this.vehicleMap.values().stream()
                .filter(vehicle -> vehicle.getBranchId().equals(branchId) &&
                        vehicle.getVehicleType().equals(vehicleType)).count();
    }

    public List<Vehicle> getAllAvailableVehicleForBranch(String branchId, int startTime, int endTime){
        return this.filterAvailableVehicles(
          this.vehicleMap.values().stream().filter(vehicle -> vehicle.getBranchId().equals(branchId)).collect(Collectors.toList()),
          startTime,
          endTime
        );
    }
}
