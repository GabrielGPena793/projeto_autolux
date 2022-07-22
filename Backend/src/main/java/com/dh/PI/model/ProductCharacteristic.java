package com.dh.PI.model;

import com.dh.PI.dto.ProductCharacteristicDTO;
import com.dh.PI.dto.productsDTO.ProductRequestDTO;
import com.dh.PI.dto.productsDTO.ProductResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_product_characteristic")
public class ProductCharacteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "characteristic_id")
    private Characteristic characteristic;

    public ProductCharacteristic(String description, Product productModel, Characteristic characteristic) {
        this.description = description;
        this.product = productModel;
        this.characteristic = characteristic;
    }

    public ProductCharacteristic(ProductRequestDTO productRequestDTO, Product product, Characteristic characteristic){
        this.product = product;
        this.description = productRequestDTO.getDescription();
        this.characteristic = characteristic;
    }

}
