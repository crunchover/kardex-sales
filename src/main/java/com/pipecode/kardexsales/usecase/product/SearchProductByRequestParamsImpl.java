package com.pipecode.kardexsales.usecase.product;

import com.pipecode.kardexsales.model.entity.Product;
import com.pipecode.kardexsales.model.web.QueryProductRequest;
import com.pipecode.kardexsales.repository.ProductRepository;
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
public class SearchProductByRequestParamsImpl implements SearchProductByRequestParams {

    private final ProductRepository productRepository;
    private final GetCategory getCategory;

    public static final Sort DEFAULT_SORT = Sort.by("id").ascending();

    @Override
    public List<Product> findAllByRequestAndPageable(QueryProductRequest request) {
        final Pageable pageable = PageRequest.of(request.getOffset(), request.getLimit(), DEFAULT_SORT);

        final var category = getCategory.get(request.getCategory());

        final Product product = Product
                .builder().brand(request.getBrand())
                .name(request.getProductName())
                .description(request.getDescription())
                .category(category)
                .build();

        final ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        final Example<Product> customExample = Example.of(product, caseInsensitiveExampleMatcher);

        return productRepository
                .findAll(customExample, pageable).getContent();
    }
}
