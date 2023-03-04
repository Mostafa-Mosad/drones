package com.example.drones.service;

import com.example.drones.common.enums.State;
import com.example.drones.mapper.DroneMapper;
import com.example.drones.model.dto.MedicationDto;
import com.example.drones.model.entity.Drone;
import com.example.drones.model.dto.DroneDto;
import com.example.drones.repository.DroneRepository;
import com.example.drones.validation.DroneValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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
        droneValidator.validateDroneBatteryCapacity(droneDto.getBatteryCapacity());
        Drone returnedDrone = droneRepository.findBySerialNumber(droneDto.getSerialNumber());
        droneDto.setState(State.LOADED);
        Drone savedDrone = droneRepository.save(droneMapper.toEntity(droneDto, returnedDrone));
        saveMedications(droneDto.getMedications(), savedDrone);
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
}
