package com.pipecode.kardexsales.usecase.sales;


import com.pipecode.kardexsales.exception.NotFoundElementException;
import com.pipecode.kardexsales.model.web.QuerySalesRequest;
import com.pipecode.kardexsales.model.web.QuerySalesResponse;
import com.pipecode.kardexsales.validator.GenericRequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
@AllArgsConstructor
public class QuerySalesImpl implements QuerySales {

    private final GenericRequestValidator validator;
    private final SearchSalesByPage searchSalesByPage;
    private final SearchSalesByRequestParams searchSalesByRequestParams;

    @Override
    public QuerySalesResponse findAllByCustomParams(final QuerySalesRequest request) {
        validator.accept(request);

        boolean findByCustomRequest = nonNull(request.getIdentificationEmployee());

        final var result = findByCustomRequest ?
                searchSalesByRequestParams.findAllByRequestAndPageable(request) :
                searchSalesByPage.apply(request.getOffset(), request.getLimit());


        if (result.isEmpty()) {
            throw new NotFoundElementException("No existen resultados para el criterio de busqueda");
        }

        final var total = result.size();


        return QuerySalesResponse.builder()
                .total(total)
                .limit(request.getLimit())
                .offset(request.getOffset())
                .results(result)
                .build();
    }

}
