package com.example.drones.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDto {

    private Long id;

    private String name;

    private Integer weight;

    private String code;

    private ImageDto image;
}
