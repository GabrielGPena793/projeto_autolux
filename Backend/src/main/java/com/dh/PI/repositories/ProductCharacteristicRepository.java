package com.dh.PI.repositories;

import com.dh.PI.model.ProductCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCharacteristicRepository extends JpaRepository<ProductCharacteristic, Long> {
    List<ProductCharacteristic> findAllById(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM tb_product_characteristic WHERE product_id = (:id)")
    void dropProductCharacteristicFromOneProduct(@Param("id") Long id);
}
