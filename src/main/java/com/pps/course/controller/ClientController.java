package com.pps.course.controller;

import com.pps.course.exception.AirplaneAlreadyExistsException;
import com.pps.course.exception.ClientAlreadyExistsException;
import com.pps.course.model.Client;
import com.pps.course.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String getRole(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return clientService.getClient(username).getRole();
    }

    @PostMapping
    public ResponseEntity<?> addClient(@RequestBody Client client) {
        try{
            clientService.addClient(client);
            return ResponseEntity.ok(client);
        } catch (ClientAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
