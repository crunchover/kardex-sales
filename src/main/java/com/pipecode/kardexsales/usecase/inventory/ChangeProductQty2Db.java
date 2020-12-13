package com.pipecode.kardexsales.usecase.inventory;

import com.pipecode.kardexsales.gateway.db.OperationRepository;
import com.pipecode.kardexsales.gateway.db.ProductRepository;
import com.pipecode.kardexsales.gateway.db.UserRepository;
import com.pipecode.kardexsales.model.entity.Operation;
import com.pipecode.kardexsales.model.entity.OperationType;
import com.pipecode.kardexsales.model.web.ChangeProductQtyRequest;
import com.pipecode.kardexsales.usecase.product.GetProduct;
import com.pipecode.kardexsales.validator.BaseValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;


@Component
@AllArgsConstructor
@Transactional
public class ChangeProductQty2Db implements ChangeProductQty {

    private final BaseValidator validator;
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
        product.setQtyInventory(calculateNewQtyInventory(product.getQtyInventory(), request.getProduct().getProductQty()));
        productRepository.save(product);
        operation.setProducts(Set.of(product));
        operationRepository.save(operation);

    }

    private static int calculateNewQtyInventory(int oldQty, int newQty) {

        return newQty + oldQty;
    }
}
