package com.dh.PI.dto.categoriesDTO;

import com.dh.PI.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDTO implements Serializable {

    private Long id;
    private String qualification;
    private String description;
    private String imageUrl;


    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.qualification = category.getQualification();
        this.description = category.getDescription();
        this.imageUrl = category.getImageUrl();
    }
}


