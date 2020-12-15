package com.pipecode.kardexsales.usecase.product;

import com.pipecode.kardexsales.model.entity.Product;
import com.pipecode.kardexsales.model.web.QueryProductRequest;

import java.util.List;

public interface SearchProductByRequestParams {

    List<Product> findAllByRequestAndPageable(final QueryProductRequest request);
}
