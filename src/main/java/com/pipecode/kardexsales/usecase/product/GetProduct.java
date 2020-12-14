package com.pipecode.kardexsales.usecase.product;


import com.pipecode.kardexsales.model.entity.Product;


public interface GetProduct {



    Product get(String productName, String productCategory);
}
