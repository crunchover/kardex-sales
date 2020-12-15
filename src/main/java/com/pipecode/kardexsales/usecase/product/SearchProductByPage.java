package com.pipecode.kardexsales.usecase.product;

import com.pipecode.kardexsales.model.entity.Product;

import java.util.List;

public interface SearchProductByPage {
    List<Product> apply(final int offset, int limit);
}
