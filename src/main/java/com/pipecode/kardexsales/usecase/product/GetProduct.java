package com.pipecode.kardexsales.usecase.product;


import com.pipecode.kardexsales.model.entity.Product;


public interface GetProduct {

    void validate(String productName, String productCategory);

    Product get(String productName, String productCategory);
}
