package com.pipecode.kardexsales.usecase.product;

import com.pipecode.kardexsales.exception.NotFoundElementException;
import com.pipecode.kardexsales.exception.ValidatorException;
import com.pipecode.kardexsales.gateway.db.ProductRepository;
import com.pipecode.kardexsales.model.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class GetProductByNameAndCategoryFromDb implements GetProduct {

    private final ProductRepository productRepository;

    @Override
    public void validate(String productName, String productCategory) {
        if(productRepository.findByNameAndCategoryName(productName, productCategory).stream().count()>0){
            throw new ValidatorException("Error el producto ya existe", Collections.singletonList(productName+" - "
                    +productCategory));
        }
    }

    @Override
    public Product get(String productName, String productCategory) {

        return productRepository.findByNameAndCategoryName(productName, productCategory)
                .orElseThrow(
                        () -> new NotFoundElementException("Error al producto no existe"));
    }
}
