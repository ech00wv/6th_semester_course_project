package com.pps.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pps.course.exception.AirplaneNotFoundException;
import com.pps.course.exception.SensorAlreadyExistsException;
import com.pps.course.exception.SensorNotFoundException;
import com.pps.course.model.Sensor;
import com.pps.course.service.SensorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {
    @Autowired
    private SensorService sensorService;

    @GetMapping
    public ResponseEntity<?> getAllSensors(@RequestParam("airplaneId") Long airplaneId) {
        try{
            List<Sensor> sensors = sensorService.getAllByAirplaneId(airplaneId);
            return ResponseEntity.ok(sensors);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping
    public ResponseEntity<?> addSensor(@RequestBody Map<String, Object> data){
        ObjectMapper objectMapper = new ObjectMapper();
        Sensor sensor = objectMapper.convertValue(data.get("sensor"), Sensor.class);
        Long airplaneId = ((Number) data.get("airplaneId")).longValue();
        try {
            sensorService.addSensor(sensor, airplaneId);
            return ResponseEntity.ok(sensor);
        } catch (SensorAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> deleteSensor(@RequestParam("sensorName") String sensorName, @RequestParam("airplaneId") Long airplaneId) {
        try {
            sensorService.deleteOne(sensorName, airplaneId);
            return ResponseEntity.ok().build();
        } catch (SensorNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
