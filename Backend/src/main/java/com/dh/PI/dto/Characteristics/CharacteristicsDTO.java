package com.dh.PI.dto.characteristics;

import com.dh.PI.model.Characteristic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class CharacteristicsDTO {

    private Long id;
    private String name;
    private String icon;


    public CharacteristicsDTO(Characteristic characteristic) {
        this.id = characteristic.getId();
        this.name = characteristic.getName();
        this.icon = characteristic.getIcon();
    }
}
