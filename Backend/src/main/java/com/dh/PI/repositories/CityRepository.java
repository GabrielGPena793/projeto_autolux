package com.dh.PI.repositories;

import com.dh.PI.model.City;
import com.dh.PI.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String name);
    boolean existsByNameAndCountry(String name, String country);
}
