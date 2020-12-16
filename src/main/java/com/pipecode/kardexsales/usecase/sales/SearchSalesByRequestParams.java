package com.pipecode.kardexsales.usecase.sales;

import com.pipecode.kardexsales.model.entity.Operation;
import com.pipecode.kardexsales.model.web.QuerySalesRequest;

import java.util.List;

public interface SearchSalesByRequestParams {
    List<Operation> findAllByRequestAndPageable(QuerySalesRequest request);
}
