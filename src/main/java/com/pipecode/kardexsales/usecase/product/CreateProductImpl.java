package com.pipecode.kardexsales.usecase.product;


import com.pipecode.kardexsales.repository.ProductRepository;
import com.pipecode.kardexsales.model.entity.Product;
import com.pipecode.kardexsales.model.web.CreateProductRequest;
import com.pipecode.kardexsales.validator.GenericRequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CreateProductImpl implements CreateProduct {

    private final GenericRequestValidator validator;
    private final ValidateProduct validateProduct;
    private final ProductRepository productRepository;
    private final GetCategory getCategory;

    @Override
    public void apply(CreateProductRequest request) {

        validator.accept(request);

        validateProduct.verify(request.getName(), request.getCategoryName());

        final var category =
                getCategory.get(request.getCategoryName());

        final var newProduct = new Product();
        newProduct.setName(request.getName());
        newProduct.setBrand(request.getBrand());
        newProduct.setDescription(request.getDescription());
        newProduct.setPrice(request.getPrice());
        newProduct.setQtyInventory(request.getQty());
        newProduct.setCategory(category);
        productRepository.save(newProduct);
    }

}

