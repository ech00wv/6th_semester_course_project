package com.pps.course.repository;

import com.pps.course.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepo extends JpaRepository<Airplane, Long> {
    public Airplane findByName(String name);
    public Long deleteByName(String name);
}
