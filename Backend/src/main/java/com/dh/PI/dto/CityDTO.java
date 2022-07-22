package com.dh.PI.dto;

import com.dh.PI.model.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class CityDTO {

    private Long id;
    private String name;
    private String country;
    private Double longitude;
    private Double latitude;

    public CityDTO(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.country = city.getCountry();
        this.longitude = city.getLongitude();
        this.latitude = city.getLatitude();
    }
}
