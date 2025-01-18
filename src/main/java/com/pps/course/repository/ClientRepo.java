package com.pps.course.repository;

import com.pps.course.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
    public Client findByUsername(String username);
}
