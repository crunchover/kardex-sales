package com.pipecode.kardexsales.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ChangeProductQtyRequest {
    private static final String EMPTY_USER_NAME = "El campo identificacion del empleado no debe estar vacío";
    private static final String NULL_LIST_PRODUCT = "El campo nombre del empleado no debe estar vacío";
    private static final String WRONG_SIZE = "El campo debe tener entre 1 y 25 caracteres";

    @NotBlank(message = EMPTY_USER_NAME)
    @Size(max = 25, message = WRONG_SIZE)
    private String username;

    @Valid
    @NotNull(message = NULL_LIST_PRODUCT)
    ProductDto product;


}
