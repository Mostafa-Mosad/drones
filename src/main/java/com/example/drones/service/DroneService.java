package com.example.drones.service;

import com.example.drones.common.enums.State;
import com.example.drones.mapper.DroneMapper;
import com.example.drones.model.dto.MedicationDto;
import com.example.drones.model.entity.Drone;
import com.example.drones.model.dto.DroneDto;
import com.example.drones.model.entity.DroneBatteryLog;
import com.example.drones.repository.DroneBatteryLogRepository;
import com.example.drones.repository.DroneRepository;
import com.example.drones.validation.DroneValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class DroneService {

    @Autowired
    private DroneMapper droneMapper;

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private DroneValidator droneValidator;

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private DroneBatteryLogRepository droneBatteryLogRepository;

    public String registerDrone(DroneDto droneDto) {
        droneValidator.validate(droneDto);
        Drone drone = droneMapper.toEntity(droneDto, Drone.builder().build());
        Drone savedDrone = droneRepository.save(drone);
        return savedDrone.getSerialNumber();
    }

    public void processDroneLoading(DroneDto droneDto) {
        droneValidator.validateDroneState(droneDto.getState());
        droneDto.setState(State.LOADING);
        droneValidator.validateMedications(droneDto.getMedications());
        validateDroneBatteryCapacity(droneDto);
        Drone returnedDrone = droneRepository.findBySerialNumber(droneDto.getSerialNumber());
        droneDto.setState(State.LOADED);
        Drone savedDrone = droneRepository.save(droneMapper.toEntity(droneDto, returnedDrone));
        saveMedications(droneDto.getMedications(), savedDrone);
    }

    private void validateDroneBatteryCapacity(DroneDto droneDto) {
        DroneBatteryLog droneBatteryLog = droneBatteryLogRepository.findFirstByDroneSerialNumberOrderByCreatedDateDesc(droneDto.getSerialNumber());
        String batteryCapacity = droneBatteryLog.getDroneBatteryCapacity();
        if(Integer.parseInt(batteryCapacity.substring(0, batteryCapacity.length()-1)) < 25
                && Integer.parseInt(droneDto.getBatteryCapacity().substring(0, droneDto.getBatteryCapacity().length()-1)) < 25)
            throw new IllegalArgumentException("The drone battery capacity must be >= 25%");
    }

    private void saveMedications(Set<MedicationDto> medications, Drone drone) {
        medications.forEach(medicationDto -> {
            try {
                medicationService.saveMedication(medicationDto, drone);
            } catch (Exception ex) {
               log.info("Error while saving medications: {}", ex.getMessage());
            }
        });
    }

    public Set<DroneDto> getAvailableDrones() {
        Set<Drone> availableDrones = droneRepository.getAvailableDrones();
        Set<DroneDto> availableDronesDto = availableDrones.stream().map(drone -> droneMapper.toDto(drone)).collect(Collectors.toSet());
        return availableDronesDto;
    }
}
