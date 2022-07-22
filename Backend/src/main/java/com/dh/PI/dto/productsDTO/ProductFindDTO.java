package com.dh.PI.dto.productsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFindDTO {

    @NotNull
    String cityName;
    @NotNull
    LocalDate init;
    @NotNull
    LocalDate end;

}
