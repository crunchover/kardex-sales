package com.pipecode.kardexsales.model.web;

import com.pipecode.kardexsales.model.entity.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QueryProductResponse {
    private final int offset;
    private final int limit;
    private final long total;
    private final List<Product> results;

}
