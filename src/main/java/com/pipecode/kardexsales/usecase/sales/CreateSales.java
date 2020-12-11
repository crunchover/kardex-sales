package com.pipecode.kardexsales.usecase.sales;

import com.pipecode.kardexsales.model.web.CreateSalesOkResponse;
import com.pipecode.kardexsales.model.web.CreateSalesRequest;

@FunctionalInterface
public interface CreateSales {
    CreateSalesOkResponse apply(CreateSalesRequest request);

}
