package com.dh.PI.repositories;

import com.dh.PI.model.Category;
import com.dh.PI.model.City;
import com.dh.PI.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = {"category", "city", "productCharacteristics"})
    Page<Product> findAll(Pageable pageable);
    @EntityGraph(attributePaths = {"category", "city", "productCharacteristics"})
    Optional<Product> findById(Long id);
    @EntityGraph(attributePaths = {"category", "city", "productCharacteristics"})
    List<Product> findAllByCategory(Category category);
    @EntityGraph(attributePaths = {"category", "city", "productCharacteristics"})
    Page<Product> findAllByCategoryQualification(String qualification, Pageable pageable);
    @EntityGraph(attributePaths = {"category", "city", "productCharacteristics"})
    Page<Product> findAllByCity(City city, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_product " +
            "WHERE id not in (SELECT DISTINCT product_id FROM tb_booking " +
            "WHERE (start_date BETWEEN (:dateStart) AND (:dateEnd)) OR (end_date BETWEEN (:dateStart) AND (:dateEnd))) AND city_id = (:cityId)")
    List<Product> findByCityNameBetweenStartDateAndEndDate(@Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd, @Param("cityId") Long cityId);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_product " +
            "WHERE id not in (SELECT DISTINCT product_id FROM tb_booking " +
            "WHERE (start_date BETWEEN (:dateStart) AND (:dateEnd)) OR (end_date BETWEEN (:dateStart) AND (:dateEnd)))")
    List<Product> findAllProductsBetweenStartDateAndEndDate(@Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd);

}
