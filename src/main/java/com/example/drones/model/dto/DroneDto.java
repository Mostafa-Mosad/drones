package com.example.drones.model.dto;

import com.example.drones.common.enums.Model;
import com.example.drones.common.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneDto {

    private String serialNumber;

    private Model model;

    private Integer weight;

    private String batteryCapacity;

    private State state;

    private Set<MedicationDto> medications;
}
