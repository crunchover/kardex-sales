package com.pipecode.kardexsales.usecase.sales;

import com.pipecode.kardexsales.model.entity.Operation;

import java.util.List;

public interface SearchSalesByPage {
    List<Operation> apply(final int offset, int limit);
}
