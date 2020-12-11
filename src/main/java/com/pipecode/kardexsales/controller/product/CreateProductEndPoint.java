package com.pipecode.kardexsales.controller.product;

import com.pipecode.kardexsales.model.web.CreateProductRequest;
import com.pipecode.kardexsales.model.web.SimpleErrorMessage;
import com.pipecode.kardexsales.usecase.product.CreateProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Api(tags = "Products")
@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping(CreateProductEndPoint.PATH)
public class CreateProductEndPoint {

    public static final String PATH = "/products";

    private final CreateProduct useCase;

    @ApiOperation("Permite crear un producto y su categoria si no existe")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Producto creada exitosamente"),
            @ApiResponse(code = 404, message = "Producto cliente no existen.",
                    response = SimpleErrorMessage.class),
            @ApiResponse(code = 400, message = "La request no ha sido realizada correctamente.",
                    response = SimpleErrorMessage.class),
            @ApiResponse(code = 422, message = "El Producto no pudo ser creada por un error en validaciones de negocio.",
                    response = SimpleErrorMessage.class),
            @ApiResponse(code = 503, message = "Un recurso externo al sistema no se encuentra disponible actualmente.",
                    response = SimpleErrorMessage.class)
    })
    @PostMapping
    public ResponseEntity<String> apply(@RequestBody CreateProductRequest request) {
        useCase.apply(request);
        return ResponseEntity.noContent().build();
    }

}
