package repository;

import dtos.BranchDTO;
import exceptions.BranchAlreadyExistsException;
import models.Branch;
import models.Vehicle;
import models.VehicleType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BranchRepository {

    private Map<String, Branch> branchMap;
    private Map<String, List<VehicleType>> supportVehicleTypeMap;

    private static BranchRepository instance = null;

    public static BranchRepository getInstance(){
        if(instance == null){
            synchronized (BranchRepository.class){
                if(instance == null){
                    instance = new BranchRepository();
                }
            }
        }
        return instance;
    }

    private BranchRepository() {
        this.branchMap = new HashMap<>();
        this.supportVehicleTypeMap = new HashMap<>();
    }

    public BranchDTO addBranch(Branch branch, List<VehicleType> supportedVehicles) throws BranchAlreadyExistsException {
        if(!branchMap.containsKey(branch.getId())) {
            synchronized (this) {
                if(!branchMap.containsKey(branch.getId())) {
                    branchMap.put(branch.getId(), branch);
                    supportVehicleTypeMap.put(branch.getId(), supportedVehicles);
                    return new BranchDTO(branch, supportedVehicles);
                }else throw new BranchAlreadyExistsException("Branch Already present");
            }
        }else {
            throw new BranchAlreadyExistsException("Branch Already present");
        }
    }

    public boolean isVehicleTypeSupportedByBranch(String branchId, VehicleType vehicleType){
        return this.supportVehicleTypeMap.get(branchId).contains(vehicleType);
    }
}
