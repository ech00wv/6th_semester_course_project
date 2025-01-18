package com.pps.course.repository;

import com.pps.course.model.Airplane;
import com.pps.course.model.Repair;
import com.pps.course.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SensorRepo extends JpaRepository<Sensor, Long> {
    public Sensor findByName(String name);

    public List<Sensor> findByAirplaneId(@Param("airplaneId") Long airplaneId);

}
