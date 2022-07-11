package dtos;

import models.Branch;
import models.Vehicle;
import models.VehicleType;

import java.util.List;

public class BranchDTO {
    private Branch branch;
    private List<VehicleType> supportedVehicleTypes;

    public BranchDTO(Branch branch, List<VehicleType> supportedVehicleTypes) {
        this.branch = branch;
        this.supportedVehicleTypes = supportedVehicleTypes;
    }

    public Branch getBranch() {
        return branch;
    }

    public List<VehicleType> getSupportedVehicleTypes() {
        return supportedVehicleTypes;
    }
}
