
package com.pipecode.kardexsales.model.web;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
public class QueryProductRequest {
    public static final String BLANK_OFFSET = "El campo offset (Pagina) no puede ser vacio";
    public static final String INVALID_OFFSET = "El campo offset debe ser numerico mayor o igual a cero";
    public static final String BLANK_LIMIT = "El campo limit (Cantidad de resultados por pagina) no puede ser vacio";
    public static final String INVALID_LIMIT = "El campo limit debe ser numerico mayor que cero";
    public static final String WRONG_SIZE_CATEGORY = "El campo origin-id puede contener " +
            "hasta 64 caracteres";
    public static final String WRONG_SIZE_BRAND = "El campo transaction-type" +
            "puede contener hasta 64 caracteres";

    public static final String WRONG_SIZE_PRODUCT_NAME = "El campo ruleset-name" +
            "puede contener hasta 64 caracteres";


    @NotNull(message = BLANK_OFFSET)
    @Min(value = 0, message = INVALID_OFFSET)
    @Digits(integer = 6, fraction = 0, message = INVALID_OFFSET)
    private int offset;

    @NotNull(message = BLANK_LIMIT)
    @Min(value = 1, message = INVALID_LIMIT)
    @Digits(integer = 6, fraction = 0, message = INVALID_LIMIT)
    private int limit;

    @Size(max = 64, message = WRONG_SIZE_CATEGORY)
    private final String category;

    @Size(max = 64, message = WRONG_SIZE_CATEGORY)
    private final String description;

    @Size(max = 64, message = WRONG_SIZE_BRAND)
    private final String brand;

    @Size(max = 64, message = WRONG_SIZE_PRODUCT_NAME)
    private final String productName;
}
