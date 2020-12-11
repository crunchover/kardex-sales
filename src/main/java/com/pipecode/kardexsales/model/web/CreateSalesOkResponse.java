package com.pipecode.kardexsales.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CreateSalesOkResponse {

    private String operationId;

    private Set<ProductDto> product;

    private String description;

}
