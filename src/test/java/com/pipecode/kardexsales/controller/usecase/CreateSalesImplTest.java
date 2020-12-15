package com.pipecode.kardexsales.controller.usecase;

import com.pipecode.kardexsales.model.entity.Employee;
import com.pipecode.kardexsales.model.web.CreateSalesRequest;
import com.pipecode.kardexsales.repository.OperationRepository;
import com.pipecode.kardexsales.repository.ProductRepository;
import com.pipecode.kardexsales.usecase.employee.GetEmployee;
import com.pipecode.kardexsales.usecase.product.GetProduct;
import com.pipecode.kardexsales.usecase.sales.CreateSalesImpl;
import com.pipecode.kardexsales.validator.GenericRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateSalesImplTest {


    @Test
    void whenArgumentIsNull_thenThrowNullPointerException() {
        // Given
        final var useCase = new CreateSalesImpl(null,null,null,
                null,null);

        // When
        final Executable executable = () -> useCase.apply(null);

        // Then
        assertThrows(
                NullPointerException.class,
                executable,
                "Passing a null argument must throw a NullPointerException."
        );
    }

    @Test
    void whenValidatorThrowsAnException_thenEscalateException() {
        // Given
        final var request = mock(CreateSalesRequest.class);
        final var employee = mock(GetEmployee.class);
        final var operation = mock(OperationRepository.class);
        final var productRepository = mock(ProductRepository.class);
        final var product = mock(GetProduct.class);
        final var validator = mock(GenericRequestValidator.class);

        when(validator.andThen(any())).thenCallRealMethod();
        doThrow(RuntimeException.class).when(validator).accept(request);
        final var useCase = new CreateSalesImpl(validator,employee,operation,productRepository,product);

        // When
        final Executable executable = () -> useCase.apply(request);

        // Then
        assertThrows(
                RuntimeException.class,
                executable,
                "When the validator throws an exception, then escalate it."
        );
        verify(validator, atLeastOnce()).accept(request);

    }

    @Test
    void whenValidatorDoesNotThrowAnyException_thenCallClient() {
        // Given
        final var request = mock(CreateSalesRequest.class);
        final var employee = mock(GetEmployee.class);
        final var operation = mock(OperationRepository.class);
        final var productRepository = mock(ProductRepository.class);
        final var product = mock(GetProduct.class);

        final var validator = mock(GenericRequestValidator.class);
        when(validator.andThen(any())).thenCallRealMethod();
        Employee employee1= new Employee();
        employee1.setIdentification("1");
        employee1.setName("Daniel");
        employee1.setLastName("Villalobos");
        employee1.setAddress("av");
        when(employee.get(anyString(),anyString(),anyString())).thenReturn(employee1);
        when(operation.save(any())).thenReturn(1L);
        //doNothing().when(operation).save(any());
      //  doNothing().when(productRepository.saveAll(any()));

        final var useCase = new CreateSalesImpl(validator,employee,operation,productRepository,product);

        // When
        final Executable executable = () -> useCase.apply(request);

        // Then
        assertDoesNotThrow(
                executable,
                "When the validator does not throw any exception, then complete operation."
        );
       // verify(validator).accept(request);
       // verify(employee).get(anyString(),anyString(),anyString());
    }


    /*

     private final GenericRequestValidator validator;
    private final GetEmployee getEmployee;
    private final OperationRepository operationRepository;
    private final ProductRepository productRepository;
    private final GetProduct getProduct;


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

        final var cant=productRepository.saveAll(productList).size();

     */
}
