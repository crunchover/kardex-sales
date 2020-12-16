package com.pipecode.kardexsales.controller.sales;

import com.pipecode.kardexsales.model.web.QuerySalesRequest;
import com.pipecode.kardexsales.model.web.QuerySalesResponse;
import com.pipecode.kardexsales.model.web.SimpleErrorMessage;
import com.pipecode.kardexsales.usecase.sales.QuerySales;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@Api(tags = "Sales")
@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping(GetSaleOperationEndPoint.PATH)
public class GetSaleOperationEndPoint {

    private final QuerySales usecase;
    public static final String PATH = "/sales";


    @ApiOperation("Permite realizar una consulta de ventas por empleado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta procesada exitosamente.",
                    response = QuerySalesResponse.class),
            @ApiResponse(code = 422, message = "La consulta no pudo ser procesada por un error en validaciones de negocio.",
                    response = SimpleErrorMessage.class),
            @ApiResponse(code = 404, message = "La consulta no arrojo resultados.",
                    response = SimpleErrorMessage.class),
            @ApiResponse(code = 503, message = "El servicio no se encuentra disponible.",
                    response = SimpleErrorMessage.class)})
    @GetMapping
    public QuerySalesResponse getSales(@ApiParam(value = "offset", required = true)
                                       @RequestParam(name = "offset") final Integer offset,
                                       @ApiParam(value = "limit", required = true)
                                       @RequestParam(name = "limit") final Integer limit,
                                       @ApiParam
                                       @RequestParam(name = "employeeId", required = false, defaultValue = "")
                                           final String employeeId) {

        final var request =
                QuerySalesRequest.builder()
                        .offset(offset)
                        .limit(limit)
                        .identificationEmployee(Optional.ofNullable(employeeId)
                                .filter(Strings::isNotBlank)
                                .map(String::strip)
                                .orElse(null))
                        .build();

        return usecase.findAllByCustomParams(request);

    }
}
