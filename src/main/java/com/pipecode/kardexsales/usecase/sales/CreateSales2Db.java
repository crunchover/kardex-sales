package com.pipecode.kardexsales.usecase.sales;

import com.pipecode.kardexsales.exception.InvalidOperationException;
import com.pipecode.kardexsales.exception.NotFoundElementException;
import com.pipecode.kardexsales.gateway.db.OperationRepository;
import com.pipecode.kardexsales.model.entity.Operation;
import com.pipecode.kardexsales.model.entity.OperationType;
import com.pipecode.kardexsales.model.web.CreateSalesOkResponse;
import com.pipecode.kardexsales.model.web.CreateSalesRequest;
import com.pipecode.kardexsales.usecase.employee.GetEmployee;
import com.pipecode.kardexsales.usecase.product.GetProduct;
import com.pipecode.kardexsales.validator.BaseValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.stream.Collectors;


@Component
@Transactional
@AllArgsConstructor
public class CreateSales2Db implements CreateSales {

    private final BaseValidator validator;
    private final GetEmployee getEmployee;
    private final OperationRepository operationRepository;
    private final GetProduct getProduct;


    @Override
    public CreateSalesOkResponse apply(CreateSalesRequest request) {

        validator.accept(request);

        final var employee = getEmployee.get(request.getIdentification(),
                request.getEmployeeName(),
                request.getEmployeeLastName());
        Operation operation = new Operation();
        operation.setEmployee(employee);
        operation.setType(OperationType.SELL);


        final var productList = request.getProduct().stream().map(item -> {

                    final var product = getProduct.get(item.getProductName(), item.getCategoryName());
                    //cantidad a vender
                    final var inventario = product.getQtyInventory();

                    final var qtyBuy = item.getQtyBuy();

                    final var newQty = calculateNewQtyInventory(inventario, qtyBuy);
                    product.setQtyInventory(newQty);
                    product.setOperation(operation);
                    return product;
                }

        ).collect(Collectors.toSet());


        operation.setProducts(productList);


        final var id = operationRepository.save(operation).getId();

        return CreateSalesOkResponse.builder()
                .operationId(String.valueOf(id))
                .product(request.getProduct())
                .description(String.format("se realizo la compra por un total de productos",
                        request.getProduct().size()))
                .build();

    }

    private static int calculateNewQtyInventory(int qtyInventory, int qtySell) {

        if (qtyInventory == 0) {
            throw new NotFoundElementException("No hay productos disponibles para la venta");
        }

        if (qtyInventory < qtySell) {
            throw new InvalidOperationException("Operacion de venta no se puede realizar la cant de productos disponibles es" + qtyInventory);
        }

        return qtyInventory - qtySell;
    }


}
