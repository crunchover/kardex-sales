package com.pipecode.kardexsales.usecase.product;


import com.pipecode.kardexsales.model.web.CreateProductRequest;

@FunctionalInterface
public interface CreateProduct {
    void apply(CreateProductRequest request);
}
