package com.example.drones.common.services;

import com.example.drones.model.entity.Drone;
import com.example.drones.model.entity.DroneBatteryLog;
import com.example.drones.repository.DroneBatteryLogRepository;
import com.example.drones.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckDroneBatteryService {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private DroneBatteryLogRepository droneBatteryLogRepository;

    @Scheduled(fixedRateString = "PT1H")
    public void executeCheckBattery() {
        List<Drone> drones = getAllDrones();
        drones.forEach(drone -> {
            DroneBatteryLog droneBatteryLog = DroneBatteryLog.builder()
                    .droneBatteryCapacity(drone.getBatteryCapacity())
                    .droneModel(drone.getModel())
                    .droneSerialNumber(drone.getSerialNumber())
                    .droneState(drone.getState())
                    .build();
            droneBatteryLogRepository.save(droneBatteryLog);
        });
    }

    private List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }
}
