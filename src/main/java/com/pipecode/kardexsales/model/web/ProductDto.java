package com.pipecode.kardexsales.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class  ProductDto {

    private static final String EMPTY_CATEGORY_NAME = "El campo nombre de categoria no debe estar vacío";
    private static final String EMPTY_NAME_PRODUCT = "El campo nombre de producto no debe estar vacío";
    private static final String WRONG_SIZE = "La categoria debe tener entre 1 y 25 caracteres";
    private static final String INVALID_QTY = "La cantidad a comprar debe estar entre 1 y 9999";


    @NotBlank(message = EMPTY_CATEGORY_NAME)
    @Size(max = 25, message = WRONG_SIZE)
    private String categoryName;

    @NotBlank(message = EMPTY_NAME_PRODUCT)
    @Size(max = 25, message = WRONG_SIZE)
    private String productName;

    @Max(value = 9999, message = INVALID_QTY)
    @Min(value = 1, message = INVALID_QTY)
    private int productQty;
}
