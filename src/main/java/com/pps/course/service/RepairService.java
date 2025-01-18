package com.pps.course.service;

import com.pps.course.exception.RepairAlreadyExistsException;
import com.pps.course.exception.RepairNotFoundException;
import com.pps.course.exception.SensorNotFoundException;
import com.pps.course.model.Airplane;
import com.pps.course.model.Repair;
import com.pps.course.model.Sensor;
import com.pps.course.repository.AirplaneRepo;
import com.pps.course.repository.RepairRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {

    @Autowired
    private RepairRepo repairRepo;

    @Autowired
    private AirplaneRepo airplaneRepo;

    public List<Repair> getAll(){
        return repairRepo.findAll();
    }

    public List<Repair> getAllByAirplaneId(Long airplaneId){
        return repairRepo.findByAirplaneId(airplaneId);
    }

    public Repair addRepair(Repair repair, Long airplaneId) throws RepairAlreadyExistsException {
        Repair repairToCheck = repairRepo.findByDate(repair.getDate());
        if (repairToCheck != null && repairToCheck.getAirplane().getId().equals(airplaneId)){
            throw new RepairAlreadyExistsException("Error in repair creation");
        }
        Airplane airplane = airplaneRepo.findById(airplaneId).orElse(null);
        repair.setAirplane(airplane);
        return repairRepo.save(repair);
    }

    public void deleteOne(String repairDate, Long airplaneId) throws RepairNotFoundException {
        List<Repair> repairs = repairRepo.findByAirplaneId(airplaneId);
        Repair foundRepair = null;
        for (Repair repair : repairs) {
            if (repair.getDate().equals(repairDate)) {
                foundRepair = repair;
                break;
            }
        }
        if (foundRepair == null){
            throw new RepairNotFoundException("Ремонта с этой датой не существует");
        }
        repairRepo.delete(foundRepair);
    }
}
