package com.example.drones.controller;


import com.example.drones.model.dto.DroneDto;
import com.example.drones.service.DroneService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/v1/drone")
@AllArgsConstructor
public class DroneController {

    @Autowired
    private DroneService droneService;

    @Operation(summary = "Submit New Drone", description = "Submit New Drone", tags = {"DRONES"})
    @PostMapping(value = "/submit-drone")
    public ResponseEntity<String> submitDrone(@Valid @RequestBody DroneDto droneDto) {
        String droneSerialNumber = droneService.submitDrone(droneDto);
        return new ResponseEntity<String>(droneSerialNumber, HttpStatus.CREATED);
    }

    @Operation(summary = "Loading a Specific Drone With Medication Items", description = "Loading a Specific Drone With Medication Items", tags = {"DRONES"})
    @PostMapping(value = "/process-drone-loading")
    public ResponseEntity<String> loadingDrone(@RequestBody DroneDto droneDto) {
        droneService.processDroneLoading(droneDto);
        return new ResponseEntity<String>("Drone Loaded Successfully!", HttpStatus.OK);
    }

    @Operation(summary = "Get Available Drones for Loading", description = "Get Available Drones for Loading", tags = {"DRONES"})
    @GetMapping(value = "/get-available-drones")
    public ResponseEntity<Set<DroneDto>> getAvailableDrones() {
        Set<DroneDto> availableDrones = droneService.getAvailableDrones();
        return new ResponseEntity<Set<DroneDto>>(availableDrones, HttpStatus.OK);
    }
}
