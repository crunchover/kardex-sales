package com.pipecode.kardexsales.usecase.product;


import com.pipecode.kardexsales.exception.NotFoundElementException;
import com.pipecode.kardexsales.model.web.QueryProductRequest;
import com.pipecode.kardexsales.model.web.QueryProductResponse;
import com.pipecode.kardexsales.validator.GenericRequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
@AllArgsConstructor
public class QueryProductImpl implements QueryProduct{
    private final GenericRequestValidator validator;

    private final SearchProductByPage searchProductByPage;
    private final SearchProductByRequestParams searchProductByRequestParams;

    @Override
    public QueryProductResponse findAllByCustomParams(QueryProductRequest request) {

        validator.accept(request);
        boolean findByCustomRequest = nonNull(request.getBrand())
                || nonNull(request.getDescription())
                || nonNull(request.getProductName());

        final var result = findByCustomRequest ?
                searchProductByRequestParams.findAllByRequestAndPageable(request) :
                searchProductByPage.apply(request.getOffset(),request.getLimit());

        if (result.isEmpty()) {
            throw new NotFoundElementException("No existen resultados para el criterio de busqueda");
        }

        final var total = result.size();



        return QueryProductResponse.builder()
                .total(total)
                .limit(request.getLimit())
                .offset(request.getOffset())
                .results(result)
                .build();
    }
}
