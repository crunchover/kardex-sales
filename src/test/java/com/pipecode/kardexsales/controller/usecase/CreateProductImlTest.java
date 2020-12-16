package com.pipecode.kardexsales.controller.usecase;

import com.pipecode.kardexsales.model.web.CreateProductRequest;
import com.pipecode.kardexsales.repository.ProductRepository;
import com.pipecode.kardexsales.usecase.product.CreateProductImpl;
import com.pipecode.kardexsales.usecase.product.GetCategory;
import com.pipecode.kardexsales.usecase.product.ValidateProduct;
import com.pipecode.kardexsales.validator.GenericRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



public class CreateProductImlTest {


    @Test
    void whenArgumentIsNull_thenThrowNullPointerException() {
        // Given
        final var useCase = new CreateProductImpl(null, null, null,
                null);

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
        final var request = mock(CreateProductRequest.class);
        final var validator = mock(GenericRequestValidator.class);
        final var productRepository = mock(ProductRepository.class);
        final var validateProduct = mock(ValidateProduct.class);
        final var category = mock(GetCategory.class);

        when(validator.andThen(any())).thenCallRealMethod();
        doThrow(RuntimeException.class).when(validator).accept(request);
        final var useCase = new CreateProductImpl(validator, validateProduct, productRepository, category);

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
