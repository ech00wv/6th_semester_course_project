package com.pps.course.service;

import com.pps.course.exception.AirplaneAlreadyExistsException;
import com.pps.course.exception.AirplaneNotFoundException;
import com.pps.course.model.Airplane;
import com.pps.course.repository.AirplaneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirplaneService {

    @Autowired
    private AirplaneRepo airplaneRepo;
    public Airplane addAirplane(Airplane airplane) throws AirplaneAlreadyExistsException {
        if (airplaneRepo.findByName(airplane.getName())!=null){
            throw new AirplaneAlreadyExistsException("Error in airplane creation");
        }
        return airplaneRepo.save(airplane);
    }

    public List<Airplane> getAll(){
        return airplaneRepo.findAll();
    }

    public void deleteOne(String name) throws AirplaneNotFoundException {
        if (airplaneRepo.findByName(name) == null){
            throw new AirplaneNotFoundException("Самолета с таким именем не существует!");
        }
        airplaneRepo.deleteByName(name);
    }
}
