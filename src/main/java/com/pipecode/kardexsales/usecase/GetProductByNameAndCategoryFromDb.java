package com.pipecode.kardexsales.usecase;

import com.pipecode.kardexsales.gateway.db.ProductRepository;
import com.pipecode.kardexsales.model.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class GetProductByNameAndCategoryFromDb implements GetProduct {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> get(String productName, String productCategory) {

        return productRepository.findByNameAndCategoryName(productName,productCategory);
    }
}
