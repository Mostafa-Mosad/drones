package com.example.drones.controller;


import com.example.drones.model.dto.DroneDto;
import com.example.drones.service.DroneService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/drone")
@AllArgsConstructor
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping(value = "/submit-drone")
    public ResponseEntity<String> submitDrone(@Valid @RequestBody DroneDto droneDto) {
        String droneSerialNumber = droneService.registerDrone(droneDto);
        return new ResponseEntity<String>(droneSerialNumber, HttpStatus.CREATED);
    }

    @PostMapping(value = "/process-drone-loading")
    public ResponseEntity<String> loadingDrone(@RequestBody DroneDto droneDto) {
        log.info("DroneDto===={}", droneDto);
        droneService.processDroneLoading(droneDto);
        return new ResponseEntity<String>("Drone Loaded Successfully!", HttpStatus.OK);
    }
}
