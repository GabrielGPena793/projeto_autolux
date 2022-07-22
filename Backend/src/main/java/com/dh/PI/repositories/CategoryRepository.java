package com.dh.PI.repositories;

import com.dh.PI.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    @EntityGraph(attributePaths = "products")
    Page<Category> findAll(Pageable pageable);

    Category findByQualification(String qualification);

    boolean existsByQualification(String qualification);
}
