package com.pps.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pps.course.exception.RepairAlreadyExistsException;
import com.pps.course.exception.RepairNotFoundException;
import com.pps.course.exception.SensorNotFoundException;
import com.pps.course.model.Airplane;
import com.pps.course.model.Repair;
import com.pps.course.service.RepairService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/repairs")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @GetMapping
    public ResponseEntity<?> getAllRepairs(@RequestParam("airplaneId") Long airplaneId) {
        try{
            List<Repair> repairs = repairService.getAllByAirplaneId(airplaneId);
            return ResponseEntity.ok(repairs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping
    public ResponseEntity<?> addRepair(@RequestBody Map<String, Object> data){
        ObjectMapper objectMapper = new ObjectMapper();
        Repair repair = objectMapper.convertValue(data.get("repair"), Repair.class);
        Long airplaneId = ((Number) data.get("airplaneId")).longValue();
        try {
            repairService.addRepair(repair, airplaneId);
            return ResponseEntity.ok(repair);
        } catch (RepairAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> deleteRepair(@RequestParam("repairDate") String repairDate, @RequestParam("airplaneId") Long airplaneId) {
        try {
            repairService.deleteOne(repairDate, airplaneId);
            return ResponseEntity.ok().build();
        } catch (RepairNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
