package com.dh.PI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ScoreDTO {

    private String userEmail;
    private Long productId;
    private Double score;

}
