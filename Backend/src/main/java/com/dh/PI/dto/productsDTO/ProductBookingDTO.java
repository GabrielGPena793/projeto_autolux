package com.dh.PI.dto.productsDTO;

import com.dh.PI.dto.Characteristics.CharacteristicsDTO;
import com.dh.PI.dto.ImageDTO;
import com.dh.PI.model.Category;
import com.dh.PI.model.City;
import com.dh.PI.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductBookingDTO implements Serializable{

    private Long id;
    private String name;
    private String description;

    private Category category;
    private City city;
    private List<ImageDTO> images;

    private Set<CharacteristicsDTO> characteristics;

    public ProductBookingDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.city = product.getCity();
        this.category = product.getCategory();
        this.images = product.getImages().stream().map(ImageDTO::new).collect(Collectors.toList());
        this.characteristics = product.getCharacteristics().stream().map(CharacteristicsDTO::new)
                .collect(Collectors.toSet());
    }


}
