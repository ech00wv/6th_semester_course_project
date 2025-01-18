package com.pps.course.repository;

import com.pps.course.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepairRepo extends JpaRepository<Repair, Long> {
    public Repair findByDate(String date);
    List<Repair> findByAirplaneId(@Param("airplaneId") Long airplaneId);
}
