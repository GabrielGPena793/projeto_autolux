package com.dh.PI.services;

import com.dh.PI.model.Image;
import com.dh.PI.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void saveAllImages(Set<Image> images){
        imageRepository.saveAll(images);
    }
}
