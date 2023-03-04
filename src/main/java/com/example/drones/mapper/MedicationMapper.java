package com.example.drones.mapper;

import com.example.drones.model.dto.MedicationDto;
import com.example.drones.model.entity.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

    @Mapping(target = "id", ignore = true)
    Medication toEntity(MedicationDto medicationDto, @MappingTarget Medication medication);

    MedicationDto toDto(Medication medication);
}
