package com.pps.course.service;

import com.pps.course.exception.AirplaneAlreadyExistsException;
import com.pps.course.exception.ClientAlreadyExistsException;
import com.pps.course.model.Client;
import com.pps.course.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;

    public Client getClient(String username){
        return clientRepo.findByUsername(username);
    }

    public Client addClient(Client client) throws ClientAlreadyExistsException {
        if (clientRepo.findByUsername(client.getUsername())!=null){
            throw new ClientAlreadyExistsException("Error in client creation");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        return clientRepo.save(client);
    }
}
