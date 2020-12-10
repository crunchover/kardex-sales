package com.pipecode.kardexsales.usecase;


import com.pipecode.kardexsales.model.entity.Product;

import java.util.Optional;

@FunctionalInterface
public interface GetProduct {
    Optional<Product> get(String productName, String categoryName);
}
