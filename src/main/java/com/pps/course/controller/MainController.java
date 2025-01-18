package com.pps.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @GetMapping("/login")
    public String loadLogin() {
        return "login";
    }

    @GetMapping("/main")
    public String loadMain() {
        return "main";
    }

    @GetMapping("/sensors")
    public String loadSensors() {
        return "sensors";
    }

    @GetMapping("/database")
    public String loadDatabase() {
        return "database";
    }

    @GetMapping("/history")
    public String loadHistory() {
        return "history";
    }

    @GetMapping("/airplane_add")
    public String loadAirplaneAdd() {
        return "airplane_add";
    }

    @GetMapping("/history_add")
    public String loadHistoryAdd() {
        return "history_add";
    }

    @GetMapping("/sensor_add")
    public String loadSensorAdd() {
        return "sensor_add";
    }

      @GetMapping("/user_add")
      public String loadUserAdd() {
        return "user_add";
      }
}
