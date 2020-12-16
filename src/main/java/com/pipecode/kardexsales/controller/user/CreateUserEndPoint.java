package com.pipecode.kardexsales.controller.user;

import com.pipecode.kardexsales.model.web.CreateUserRequest;
import com.pipecode.kardexsales.model.web.SimpleErrorMessage;
import com.pipecode.kardexsales.usecase.user.CreateUser;
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
@Api(tags = "Users")
@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping(CreateUserEndPoint.PATH)
public class CreateUserEndPoint {

    public static final String PATH = "/users";

    private final CreateUser useCase;

    @ApiOperation("Permite registrar un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Usuario creada exitosamente"),
            @ApiResponse(code = 404, message = "Usuario cliente no existen.",
                    response = SimpleErrorMessage.class),
            @ApiResponse(code = 400, message = "La request no ha sido realizada correctamente.",
                    response = SimpleErrorMessage.class),
            @ApiResponse(code = 422, message = "El Usuario no pudo ser creada por un error en validaciones de negocio.",
                    response = SimpleErrorMessage.class),
            @ApiResponse(code = 503, message = "Un recurso externo al sistema no se encuentra disponible actualmente.",
                    response = SimpleErrorMessage.class)
    })
    @PostMapping
    public ResponseEntity<String> apply(@RequestBody CreateUserRequest request) {
        useCase.apply(request);
        return ResponseEntity.noContent().build();
    }
}
