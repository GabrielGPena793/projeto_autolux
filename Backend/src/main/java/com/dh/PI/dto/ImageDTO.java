package com.dh.PI.dto;

import com.dh.PI.model.Image;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class ImageDTO implements Serializable {

    private Long id;
    private String url;

    public ImageDTO(Image image) {
        this.id = image.getId();
        this.url = image.getUrl();
    }
}
