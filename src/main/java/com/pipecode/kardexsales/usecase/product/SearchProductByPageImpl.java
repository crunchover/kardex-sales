package com.pipecode.kardexsales.usecase.product;


import com.pipecode.kardexsales.model.entity.Product;
import com.pipecode.kardexsales.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SearchProductByPageImpl implements SearchProductByPage {

    private final ProductRepository productRepository;

    public static final Sort DEFAULT_SORT = Sort.by("id").ascending();

    @Override
    public List<Product> apply(int offset, int limit) {
        final Pageable pageable = PageRequest.of(offset, limit, DEFAULT_SORT);
        return productRepository
                .findAll(pageable).getContent();
    }
}
