package com.pps.course.service;


import com.pps.course.exception.SensorAlreadyExistsException;
import com.pps.course.exception.SensorNotFoundException;
import com.pps.course.model.Airplane;
import com.pps.course.model.Sensor;
import com.pps.course.repository.AirplaneRepo;
import com.pps.course.repository.SensorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {
    @Autowired
    private SensorRepo sensorRepo;

    @Autowired
    private AirplaneRepo airplaneRepo;

    public List<Sensor> getAll(){
        return sensorRepo.findAll();
    }

    public List<Sensor> getAllByAirplaneId(Long airplaneId){
        return sensorRepo.findByAirplaneId(airplaneId);
    }

    public Sensor addSensor(Sensor sensor, Long airplaneId) throws SensorAlreadyExistsException {
        Sensor sensorToCheck = sensorRepo.findByName(sensor.getName());
        if (sensorToCheck != null && sensorToCheck.getAirplane().getId().equals(airplaneId)){
            throw new SensorAlreadyExistsException("Error in sensor creation");
        }
        Airplane airplane = airplaneRepo.findById(airplaneId).orElse(null);
        sensor.setAirplane(airplane);
        return sensorRepo.save(sensor);
    }

    public void deleteOne(String sensorName, Long airplaneId) throws SensorNotFoundException {
        List<Sensor> sensors = sensorRepo.findByAirplaneId(airplaneId);
        Sensor foundSensor = null;
        for (Sensor sensor : sensors) {
            if (sensor.getName().equals(sensorName)) {
                foundSensor = sensor;
                break;
            }
        }
        if (foundSensor == null){
            throw new SensorNotFoundException("Датчика с таким именем не существует");
        }
        sensorRepo.delete(foundSensor);
    }
}
