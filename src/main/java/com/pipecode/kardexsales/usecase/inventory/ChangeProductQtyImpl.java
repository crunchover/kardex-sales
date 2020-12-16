package com.pipecode.kardexsales.usecase.inventory;


import com.pipecode.kardexsales.model.entity.Operation;
import com.pipecode.kardexsales.model.entity.OperationType;
import com.pipecode.kardexsales.model.web.ChangeProductQtyRequest;
import com.pipecode.kardexsales.repository.OperationRepository;
import com.pipecode.kardexsales.repository.ProductRepository;
import com.pipecode.kardexsales.repository.UserRepository;
import com.pipecode.kardexsales.usecase.product.GetProduct;
import com.pipecode.kardexsales.validator.GenericRequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import javax.transaction.Transactional;



@Component
@AllArgsConstructor
@Transactional
public class ChangeProductQtyImpl implements ChangeProductQty {

    private final GenericRequestValidator validator;
    private final GetProduct getProduct;
    private final UserRepository userRepository;
    private final OperationRepository operationRepository;
    private final ProductRepository productRepository;


    @Override
    public void apply(ChangeProductQtyRequest request) {

        validator.accept(request);

        final var user = userRepository.findByUserName(request.getUsername());

        final var operation = Operation.builder()
                .user(user)
                .type(OperationType.INVENTORY)
                .build();

        final var product = getProduct.get(request.getProduct().getProductName(),
                request.getProduct().getProductName());
        product.setQtyInventory(calculateNewQtyInventory(product.getQtyInventory(), request.getProduct()
                .getProductQty()));
        productRepository.save(product);
        operation.setProducts(Set.of(product));
        operationRepository.save(operation);

    }

    private static int calculateNewQtyInventory(int oldQty, int newQty) {

        return newQty + oldQty;
    }
}
