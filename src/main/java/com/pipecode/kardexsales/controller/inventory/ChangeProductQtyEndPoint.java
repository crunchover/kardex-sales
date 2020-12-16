package com.pipecode.kardexsales.controller.inventory;


import com.pipecode.kardexsales.model.web.ChangeProductQtyRequest;
import com.pipecode.kardexsales.model.web.SimpleErrorMessage;
import com.pipecode.kardexsales.usecase.inventory.ChangeProductQty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@Api(tags = "Products")
@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping(ChangeProductQtyEndPoint.PATH)
public class ChangeProductQtyEndPoint {

    public static final String PATH = "/products";

    private final ChangeProductQty useCase;

    @ApiOperation("Permite actualizar el inventario de un producto")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Se actualizo el inventario del producto correctamente"),
            @ApiResponse(code = 400, message = "Formulario invalido o transacción invalido"),
            @ApiResponse(code = 404, message = "Cliente o tarjeta no encontrados"),
            @ApiResponse(code = 422, message = "La entidad no pudo ser procesada por un error en validaciones de negocio."),
            @ApiResponse(code = 503, message = "El servicio no se encuentra disponible.", response = SimpleErrorMessage.class)
    })

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> apply(@RequestBody final ChangeProductQtyRequest request) {

        useCase.apply(request);
        log.debug("Actualizacion de inventario del producto "+ request.getProduct() +"realizado correctamente" );
        return ResponseEntity.noContent().build();

    }


}
