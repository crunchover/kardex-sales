package com.pipecode.kardexsales.usecase.product;


import com.pipecode.kardexsales.model.entity.Category;

@FunctionalInterface
public interface GetCategory {
    Category get(String name);
}
