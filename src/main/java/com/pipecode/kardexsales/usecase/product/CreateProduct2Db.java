package com.pipecode.kardexsales.usecase.product;


import com.pipecode.kardexsales.gateway.db.ProductRepository;
import com.pipecode.kardexsales.gateway.db.UserRepository;
import com.pipecode.kardexsales.model.entity.Operation;
import com.pipecode.kardexsales.model.entity.OperationType;
import com.pipecode.kardexsales.model.entity.Product;
import com.pipecode.kardexsales.model.web.CreateProductRequest;
import com.pipecode.kardexsales.validator.BaseValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CreateProduct2Db implements CreateProduct {

    private final BaseValidator validator;
    private final ProductRepository productRepository;
    private final GetProduct getProduct;
    private final GetProductCategory getProductCategory;

    @Override
    public void apply(CreateProductRequest request) {

        validator.accept(request);

        getProduct.validate(request.getName(), request.getCategoryName());

        final var category =
                getProductCategory.get(request.getCategoryName());

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

