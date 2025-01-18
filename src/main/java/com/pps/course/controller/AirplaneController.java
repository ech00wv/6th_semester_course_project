package com.pps.course.controller;

import com.pps.course.exception.AirplaneAlreadyExistsException;
import com.pps.course.exception.AirplaneNotFoundException;
import com.pps.course.model.Airplane;
import com.pps.course.service.AirplaneService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    @PostMapping
    public ResponseEntity<?> addAirplane(@RequestBody Airplane airplane){
        try{
            airplaneService.addAirplane(airplane);
            return ResponseEntity.ok(airplane);
        } catch (AirplaneAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAirplanes() {
        try{
            List<Airplane> airplanes = airplaneService.getAll();
            return ResponseEntity.ok(airplanes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> deleteAirplane(@RequestParam("airplaneName") String airplaneName) {
        try {
            airplaneService.deleteOne(airplaneName);
            return ResponseEntity.ok().build();
        } catch (AirplaneNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
