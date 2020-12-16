package com.pipecode.kardexsales.usecase.product;

import com.pipecode.kardexsales.exception.ValidatorException;
import com.pipecode.kardexsales.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class ValidateProductImpl implements ValidateProduct {

    private final ProductRepository productRepository;

    @Override
    public void verify(String productName, String productCategory) {
        if (productRepository.findByNameAndCategoryName(productName, productCategory).stream().count() > 0) {
            throw new ValidatorException("Error el producto ya existe", Collections.singletonList(productName + " - "
                    + productCategory));
        }
    }

}
