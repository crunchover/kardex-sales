package com.pipecode.kardexsales.usecase.product;

import com.pipecode.kardexsales.exception.NotFoundElementException;
import com.pipecode.kardexsales.model.entity.Product;
import com.pipecode.kardexsales.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetProductImpl implements GetProduct {

    private final ProductRepository productRepository;

    @Override
    public Product get(String productName, String productCategory) {

        return productRepository.findByNameAndCategoryName(productName, productCategory)
                .orElseThrow(
                        () -> new NotFoundElementException("Error al producto no existe"));
    }
}
