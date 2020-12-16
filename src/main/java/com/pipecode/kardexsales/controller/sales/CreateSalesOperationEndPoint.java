package com.pipecode.kardexsales.controller.sales;


import com.pipecode.kardexsales.model.web.CreateSalesOkResponse;
import com.pipecode.kardexsales.model.web.CreateSalesRequest;
import com.pipecode.kardexsales.model.web.SimpleErrorMessage;
import com.pipecode.kardexsales.usecase.sales.CreateSales;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@Api(tags = "Sales")
@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping(CreateSalesOperationEndPoint.PATH)
public class CreateSalesOperationEndPoint {

    public static final String PATH = "/sales";

    private final CreateSales useCase;

    @ApiOperation("Permite crear una Venta a un empleado.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Venta creada exitosamente"),
            @ApiResponse(code = 404, message = "La cuenta o el cliente no existen.", response = SimpleErrorMessage.class),
            @ApiResponse(code = 422, message = "La entidad no pudo ser procesada por un error en validaciones de negocio.",
                    response = SimpleErrorMessage.class),
            @ApiResponse(code = 503, message = "Un recurso externo al sistema no se encuentra disponible actualmente.",
                    response = SimpleErrorMessage.class)
    })

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateSalesOkResponse> apply(@RequestBody CreateSalesRequest request) {

        final var response = useCase.apply(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
