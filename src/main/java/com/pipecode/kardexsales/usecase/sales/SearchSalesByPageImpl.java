package com.pipecode.kardexsales.usecase.sales;

import com.pipecode.kardexsales.model.entity.Operation;
import com.pipecode.kardexsales.model.entity.Product;
import com.pipecode.kardexsales.repository.OperationRepository;
import com.pipecode.kardexsales.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SearchSalesByPageImpl implements SearchSalesByPage{
    private final OperationRepository operationRepository;

    public static final Sort DEFAULT_SORT = Sort.by("id").ascending();

    @Override
    public List<Operation> apply(int offset, int limit) {
        final Pageable pageable = PageRequest.of(offset, limit, DEFAULT_SORT);

        return operationRepository
                .findAll(pageable).getContent();
    }

}
