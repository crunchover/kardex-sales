package com.pipecode.kardexsales.controller.product;

import com.pipecode.kardexsales.model.web.QueryProductRequest;
import com.pipecode.kardexsales.model.web.QueryProductResponse;
import com.pipecode.kardexsales.model.web.SimpleErrorMessage;
import com.pipecode.kardexsales.usecase.product.QueryProduct;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@AllArgsConstructor
@Api(tags = "Products")
@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping(GetProductEndPoint.PATH)
public class GetProductEndPoint {

    private final QueryProduct usecase;
    public static final String PATH = "/products";


    @ApiOperation("Permite realizar una consulta de prodcutos.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta procesada exitosamente.",
                    response = QueryProductResponse.class),
            @ApiResponse(code = 422, message = "La consulta no pudo ser procesada por un error en validaciones de negocio.",
                    response = SimpleErrorMessage.class),
            @ApiResponse(code = 404, message = "La consulta no arrojo resultados.",
                    response = SimpleErrorMessage.class),
            @ApiResponse(code = 503, message = "El servicio no se encuentra disponible.",
                    response = SimpleErrorMessage.class)})
    @GetMapping
    public QueryProductResponse getProducts(@ApiParam(value = "offset", required = true)
                                            @RequestParam(name = "offset") final Integer offset,
                                            @ApiParam(value = "limit", required = true)
                                            @RequestParam(name = "limit") final Integer limit,
                                            @ApiParam
                                            @RequestParam(name = "brand", required = false, defaultValue = "") final String brand,
                                            @ApiParam
                                            @RequestParam(name = "description", required = false, defaultValue = "") final String description,
                                            @ApiParam
                                            @RequestParam(name = "productName", required = false, defaultValue = "") final String productName,
                                            @ApiParam
                                                @RequestParam(name = "category", required = false, defaultValue = "") final String category) {

        final var request =
                QueryProductRequest.builder()
                        .offset(offset)
                        .limit(limit)
                        .description(Optional.ofNullable(description)
                                .filter(Strings::isNotBlank)
                                .map(String::strip)
                                .orElse(null))
                        .brand(Optional.ofNullable(brand)
                                .filter(Strings::isNotBlank)
                                .map(String::strip)
                                .orElse(null))
                        .productName(Optional.ofNullable(productName)
                                .filter(Strings::isNotBlank)
                                .map(String::strip)
                                .orElse(null))
                        .category(Optional.ofNullable(category)
                                .filter(Strings::isNotBlank)
                                .map(String::strip)
                                .orElse(null))
                        .build();

        return usecase.findAllByCustomParams(request);
    }

}
