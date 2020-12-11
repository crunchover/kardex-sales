package com.pipecode.kardexsales.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CreateUserRequest {

    private static final String EMPTY_NAME = "El campo nombre del empleado no debe estar vacío";
    private static final String MSG_NOT_NULL = "El campo isACtive es requerido";
    private static final String WRONG_SIZE = "La categoria debe tener entre 1 y 25 caracteres";

    @NotBlank(message = EMPTY_NAME)
    @Size(max = 25, message = WRONG_SIZE)
    private String userName;

    @NotNull(message = MSG_NOT_NULL)
    private Boolean isActive;


}
