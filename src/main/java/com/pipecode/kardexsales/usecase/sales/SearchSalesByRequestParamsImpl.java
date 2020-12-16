package com.pipecode.kardexsales.usecase.sales;

import com.pipecode.kardexsales.model.entity.Operation;
import com.pipecode.kardexsales.model.entity.OperationType;
import com.pipecode.kardexsales.model.web.QuerySalesRequest;
import com.pipecode.kardexsales.repository.EmployeeRepository;
import com.pipecode.kardexsales.repository.OperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SearchSalesByRequestParamsImpl implements SearchSalesByRequestParams {

    private final OperationRepository operationRepository;
    private final EmployeeRepository getEmployee;

    public static final Sort DEFAULT_SORT = Sort.by("id").ascending();

    @Override
    public List<Operation> findAllByRequestAndPageable(QuerySalesRequest request) {
        final Pageable pageable = PageRequest.of(request.getOffset(), request.getLimit(), DEFAULT_SORT);

        final var employee = getEmployee.findByIdentification(request.getIdentificationEmployee());

        final Operation operation = Operation
                .builder().employee(employee)
                .type(OperationType.SELL)
                .build();

        final ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        final Example<Operation> customExample = Example.of(operation, caseInsensitiveExampleMatcher);

        return operationRepository
                .findAll(customExample, pageable).getContent();
    }
}
