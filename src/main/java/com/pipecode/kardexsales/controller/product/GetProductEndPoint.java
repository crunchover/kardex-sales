package com.pipecode.kardexsales.controller.product;

import com.pipecode.kardexsales.model.web.QueryProductRequest;
import com.pipecode.kardexsales.model.web.QueryProductResponse;
import com.pipecode.kardexsales.model.web.SimpleErrorMessage;
import com.pipecode.kardexsales.usecase.product.QueryProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    @GetMapping("/search")
    public QueryProductResponse getProducts(QueryProductRequest request){

         return usecase.findAllByCustomParams(request);
    }

}
