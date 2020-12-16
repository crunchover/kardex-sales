package com.pipecode.kardexsales.model.web;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
@Builder
public class QuerySalesRequest {

    public static final String BLANK_OFFSET = "El campo offset (Pagina) no puede ser vacio";
    public static final String INVALID_OFFSET = "El campo offset debe ser numerico mayor o igual a cero";
    public static final String BLANK_LIMIT = "El campo limit (Cantidad de resultados por pagina) no puede ser vacio";
    public static final String INVALID_LIMIT = "El campo limit debe ser numerico mayor que cero";
    public static final String WRONG_SIZE_CATEGORY = "El campo category puede contener " +
            "hasta 64 caracteres";
    public static final String WRONG_SIZE_BRAND = "El campo brand" +
            "puede contener hasta 64 caracteres";

    public static final String WRONG_SIZE_PRODUCT_NAME = "El campo product name" +
            "puede contener hasta 64 caracteres";
    private static final String EMPTY_FIELD = "El campo identificacion del empleado no debe estar vacío";


    @NotNull(message = BLANK_OFFSET)
    @Min(value = 0, message = INVALID_OFFSET)
    @Digits(integer = 6, fraction = 0, message = INVALID_OFFSET)
    private int offset;

    @NotNull(message = BLANK_LIMIT)
    @Min(value = 1, message = INVALID_LIMIT)
    @Digits(integer = 6, fraction = 0, message = INVALID_LIMIT)
    private int limit;

    @NotBlank(message = EMPTY_FIELD)
    @Size(max = 64, message = WRONG_SIZE_CATEGORY)
    private final String identificationEmployee;

}
