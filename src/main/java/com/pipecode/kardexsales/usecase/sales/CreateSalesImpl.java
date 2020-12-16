package com.pipecode.kardexsales.usecase.sales;

import com.pipecode.kardexsales.exception.InvalidOperationException;
import com.pipecode.kardexsales.exception.NotFoundElementException;
import com.pipecode.kardexsales.model.entity.Operation;
import com.pipecode.kardexsales.model.entity.OperationType;
import com.pipecode.kardexsales.model.web.CreateSalesOkResponse;
import com.pipecode.kardexsales.model.web.CreateSalesRequest;
import com.pipecode.kardexsales.repository.OperationRepository;
import com.pipecode.kardexsales.repository.ProductRepository;
import com.pipecode.kardexsales.usecase.employee.GetEmployee;
import com.pipecode.kardexsales.usecase.product.GetProduct;
import com.pipecode.kardexsales.validator.GenericRequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import javax.transaction.Transactional;


@Component
@Transactional
@AllArgsConstructor
public class CreateSalesImpl implements CreateSales {

    private final GenericRequestValidator validator;
    private final GetEmployee getEmployee;
    private final OperationRepository operationRepository;
    private final ProductRepository productRepository;
    private final GetProduct getProduct;


    @Override
    public CreateSalesOkResponse apply(CreateSalesRequest request) {

        validator.accept(request);

        final var employee = getEmployee.get(request.getIdentification(),
                request.getEmployeeName(),
                request.getEmployeeLastName());

        final var operation = Operation.builder().employee(employee)
                .type(OperationType.SELL)
                .build();

        final var id = operationRepository.save(operation).getId();


        final var productList = request.getProduct().stream().map(item -> {
                    final var product = getProduct.get(item.getProductName(), item.getCategoryName());
                    validateQty(product.getQtyInventory(), item.getProductQty());
                    product.setQtyInventory(calculateNewQtyInventory(product.getQtyInventory(), item.getProductQty()));
                    product.setOperation(operation);
                    return product;
                }

        ).collect(Collectors.toSet());

        final var cant = productRepository.saveAll(productList).size();


        return CreateSalesOkResponse.builder()
                .operationId(String.valueOf(id))
                .product(request.getProduct())
                .description(String.format("se realizo la compra por un total de %s productos",
                        cant))
                .build();

    }

    private void validateQty(int qtyInventory, int qtySell) {
        if (qtyInventory == 0) {
            throw new NotFoundElementException("No hay productos disponibles para la venta");
        }

        if (qtyInventory < qtySell) {
            throw new InvalidOperationException("Operacion de venta no se puede realizar la cant " +
                    "de productos disponibles es" + qtyInventory);
        }

    }

    private static int calculateNewQtyInventory(int qtyInventory, int qtySell) {

        return qtyInventory - qtySell;
    }


}
