package com.pipecode.kardexsales.usecase.product;

import com.pipecode.kardexsales.model.web.QueryProductRequest;
import com.pipecode.kardexsales.model.web.QueryProductResponse;

public interface QueryProduct {

    QueryProductResponse findAllByCustomParams(final QueryProductRequest request);
}
