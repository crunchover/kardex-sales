package com.pipecode.kardexsales.usecase.product;


import com.pipecode.kardexsales.model.entity.Category;

@FunctionalInterface
public interface GetProductCategory {
    Category get(String name);
}
