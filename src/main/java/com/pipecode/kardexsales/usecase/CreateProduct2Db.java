package com.pipecode.kardexsales.usecase;


import com.pipecode.kardexsales.gateway.db.ProductRepository;
import com.pipecode.kardexsales.model.entity.Product;
import com.pipecode.kardexsales.model.web.CreateProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateProduct2Db implements CreateProduct {

    private final ProductRepository productRepository;
    private final GetProductCategory getProductCategory;

    @Override
    public void apply(CreateProductRequest request) {

        final var category =
                getProductCategory.get(request.getCategoryName());

        final var product = Product.builder()
                .name(request.getName())
                .brand(request.getBrand())
                .description(request.getDescription())
                .price(request.getPrice())
                .qty(request.getQty())
                .category(category).build();
        productRepository.save(product);
    }
}
