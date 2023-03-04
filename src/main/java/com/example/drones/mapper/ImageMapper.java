package com.example.drones.mapper;

import com.example.drones.model.dto.ImageDto;
import com.example.drones.model.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "id", ignore = true)
    Image toEntity(ImageDto imageDto, @MappingTarget Image image);

    ImageDto toDto(Image image);
}
