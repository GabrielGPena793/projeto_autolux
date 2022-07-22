package com.dh.PI.model;

import com.dh.PI.dto.ImageDTO;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;

    public Image(ImageDTO imageDTO) {
        this.id = imageDTO.getId();
        this.url = imageDTO.getUrl();
    }

}
