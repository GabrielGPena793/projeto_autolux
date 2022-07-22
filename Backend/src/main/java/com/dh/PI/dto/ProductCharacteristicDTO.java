package com.dh.PI.dto;

import com.dh.PI.model.ProductCharacteristic;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductCharacteristicDTO implements Serializable {

    private String name;
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String icon;

}
