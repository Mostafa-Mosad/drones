package com.example.drones.service;

import com.example.drones.common.exceptions.RecordNotFoundException;
import com.example.drones.model.dto.ImageDto;
import com.example.drones.model.entity.Image;
import com.example.drones.repository.ImageRepository;
import com.example.drones.utils.ImageUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image uploadImage(ImageDto imageDto) throws IOException {
        return imageRepository.save(Image.builder()
        .name(imageDto.getName())
        .type(imageDto.getType())
        .content(ImageUtil.compressImage(imageDto.getContent()))
        .build());
    }

    public byte[] getImageById(Long imageId) {
        Image returnedImage = imageRepository.findById(imageId).orElseThrow(() -> new RecordNotFoundException(String.format("No image found by id %d", imageId)));
        byte[] image = ImageUtil.decompressImage(returnedImage.getContent());
        return image;
    }
}
