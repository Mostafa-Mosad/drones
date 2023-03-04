package com.example.drones.service;

import com.example.drones.mapper.MedicationMapper;
import com.example.drones.model.dto.MedicationDto;
import com.example.drones.model.entity.Drone;
import com.example.drones.model.entity.Image;
import com.example.drones.model.entity.Medication;
import com.example.drones.repository.MedicationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@AllArgsConstructor
public class MedicationService {

    private MedicationMapper medicationMapper;

    private MedicationRepository medicationRepository;

    private ImageService imageService;


    public void saveMedication(MedicationDto medicationDto, Drone drone) throws IOException {
        Image savedImage = imageService.uploadImage(medicationDto.getImage());
        Medication medication = medicationMapper.toEntity(medicationDto, Medication.builder()
                .drone(drone)
                .image(savedImage)
                .build());
        medicationRepository.save(medication);
    }
}
