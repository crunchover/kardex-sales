package com.pipecode.kardexsales.controller.employee;

import com.pipecode.kardexsales.model.web.CreateEmployeeRequest;
import com.pipecode.kardexsales.model.web.SimpleErrorMessage;
import com.pipecode.kardexsales.usecase.employee.CreateEmployee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@AllArgsConstructor
@Api(tags = "Employes")
@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping(CreateEmployeeEndPoint.PATH)
public class CreateEmployeeEndPoint {

    public static final String PATH = "/employees";

    private final CreateEmployee useCase;

    @ApiOperation("Permite registrar un empleado")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Empleado creada exitosamente"),
            @ApiResponse(code = 404, message = "Empleado cliente no existen.",
                    response = SimpleErrorMessage.class),
            @ApiResponse(code = 400, message = "La request no ha sido realizada correctamente.",
                    response = SimpleErrorMessage.class),
            @ApiResponse(code = 422, message = "El Empleado no pudo ser creada por un error en validaciones de negocio.",
                    response = SimpleErrorMessage.class),
            @ApiResponse(code = 503, message = "Un recurso externo al sistema no se encuentra disponible actualmente.",
                    response = SimpleErrorMessage.class)
    })
    @PostMapping
    public ResponseEntity<String> apply(@RequestBody CreateEmployeeRequest request) {
        useCase.apply(request);
        return ResponseEntity.noContent().build();
    }
}
