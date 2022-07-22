package com.dh.PI.repositories;

import com.dh.PI.model.Category;
import com.dh.PI.model.Characteristic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {

    Characteristic findByName(String name);
}
