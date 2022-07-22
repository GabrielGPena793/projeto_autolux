package com.dh.PI.dto.productsDTO;

import com.dh.PI.dto.ImageDTO;
import com.dh.PI.dto.ProductCharacteristicDTO;
import com.dh.PI.model.Characteristic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductRequestDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String category;
    private String city;
    private List<ProductCharacteristicDTO> characteristics;
    private List<ImageDTO> imageDTOS;

}