package com.pipecode.kardexsales.controller.usecase;

import com.pipecode.kardexsales.model.web.CreateSalesRequest;
import com.pipecode.kardexsales.repository.OperationRepository;
import com.pipecode.kardexsales.repository.ProductRepository;
import com.pipecode.kardexsales.usecase.employee.GetEmployee;
import com.pipecode.kardexsales.usecase.product.GetProduct;
import com.pipecode.kardexsales.usecase.sales.CreateSalesImpl;
import com.pipecode.kardexsales.validator.GenericRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.only;

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

}
