package com.dh.PI.repositories;

import com.dh.PI.model.Image;
import com.dh.PI.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
