package com.pipecode.kardexsales.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CreateEmployeeRequest {

    private static final String EMPTY_NAME = "El campo nombre del empleado no debe estar vacío";
    private static final String EMPTY_LAST_NAME = "El campo Apellido del empleado no debe estar vacío";
    private static final String EMPTY_ADDRESS = "El campo Direccion del empleado no debe estar vacío";
    private static final String WRONG_SIZE = "El campo debe tener entre 1 y 25 caracteres";
    @NotBlank(message = EMPTY_NAME)
    @Size(max = 25, message = WRONG_SIZE)
    private String name;

    @NotBlank(message = EMPTY_LAST_NAME)
    @Size(max = 25, message = WRONG_SIZE)
    private String lastName;

    @NotBlank(message = EMPTY_ADDRESS)
    @Size(max = 25, message = WRONG_SIZE)
    private String address;

}
