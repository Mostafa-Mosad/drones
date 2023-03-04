package com.example.drones.mapper;

import com.example.drones.model.entity.Drone;
import com.example.drones.model.dto.DroneDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DroneMapper {

    @Mapping(target = "id", ignore = true)
    Drone toEntity(DroneDto droneDto, @MappingTarget Drone drone);

    DroneDto toDto(Drone drone);
}
