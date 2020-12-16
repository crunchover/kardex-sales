package com.pipecode.kardexsales.usecase.sales;

import com.pipecode.kardexsales.model.web.QuerySalesRequest;
import com.pipecode.kardexsales.model.web.QuerySalesResponse;

public interface QuerySales {
    QuerySalesResponse findAllByCustomParams(final QuerySalesRequest request);
}

