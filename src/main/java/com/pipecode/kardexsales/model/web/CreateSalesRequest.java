package com.pipecode.kardexsales.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CreateSalesRequest {

    private static final String EMPTY_ID = "El documento del empleado no debe estar vacío";
    private static final String EMPTY_NAME = "El campo nombre no debe estar vacío";
    private static final String EMPTY_LAST_NAME = "El campo apellido no debe estar vacío";
    private static final String NULL_LIST_PRODUCT = "El campo producto no debe ser nulo";

    private static final String WRONG_SIZE = "La categoria debe tener entre 1 y 25 caracteres";

    @NotBlank(message = EMPTY_ID)
    @Size(max = 25, message = WRONG_SIZE)
    private String identification;

    @NotBlank(message = EMPTY_NAME)
    @Size(max = 25, message = WRONG_SIZE)
    private String employeeName;

    @NotBlank(message = EMPTY_LAST_NAME)
    @Size(max = 25, message = WRONG_SIZE)
    private String employeeLastName;

    @Valid
    @NotNull(message = NULL_LIST_PRODUCT)
    private Set<ProductDto> product;

}
