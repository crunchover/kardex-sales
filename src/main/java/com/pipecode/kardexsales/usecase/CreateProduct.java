package com.pipecode.kardexsales.usecase;


import com.pipecode.kardexsales.model.web.CreateProductRequest;

@FunctionalInterface
public interface CreateProduct {
    void apply(CreateProductRequest request);
}
