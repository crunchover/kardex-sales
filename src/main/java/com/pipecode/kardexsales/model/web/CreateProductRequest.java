package com.pipecode.kardexsales.model.web;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CreateProductRequest {

    private static final String EMPTY_CATEGORY = "El campo category no debe estar vacío";
    private static final String WRONG_SIZE = "La categoria debe tener entre 1 y 25 caracteres";
    private static final String INVALID_QTY = "La cantidad debe estar entre 1 y 9999";
    private static final String NULL_PRICE = "El campo precio no puede ser nulo";

    @NotBlank(message = EMPTY_CATEGORY)
    @Size(max = 25, message = WRONG_SIZE)
    private String categoryName;

    @NotBlank(message = EMPTY_CATEGORY)
    @Size(max = 25, message = WRONG_SIZE)
    private String name;

    @NotBlank(message = EMPTY_CATEGORY)
    @Size(max = 25, message = WRONG_SIZE)
    private String brand;

    @NotBlank(message = EMPTY_CATEGORY)
    @Size(max = 25, message = WRONG_SIZE)
    private String description;

    @NotNull(message = NULL_PRICE)
    private BigDecimal price;

    @Max(value = 9999, message = INVALID_QTY)
    @Min(value = 1, message = INVALID_QTY)
    private int qty;

}
