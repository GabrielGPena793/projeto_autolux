package com.dh.PI.dto.productsDTO;

import com.dh.PI.dto.ImageDTO;
import com.dh.PI.dto.ProductCharacteristicDTO;
import com.dh.PI.model.Category;
import com.dh.PI.model.City;
import com.dh.PI.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductResponseDTO implements Serializable{

    private Long id;
    private String name;
    private Double score;
    private Integer countScore;
    private String description;
    private Category category;
    private City city;
    private List<ImageDTO> images;
    private List<ProductCharacteristicDTO> characteristics;


    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.score = product.getScore();
        this.countScore = product.getCount();
        this.description = product.getDescription();
        this.city = product.getCity();
        this.category = product.getCategory();
        this.images = product.getImages().stream().map(ImageDTO::new).collect(Collectors.toList());
        this.characteristics = product.getProductCharacteristics().stream().map(characteristic ->
                new ProductCharacteristicDTO(characteristic.getCharacteristic().getName(), characteristic.getDescription(),
                        characteristic.getCharacteristic().getIcon())).collect(Collectors.toList());
    }

}
