package com.pipecode.kardexsales.usecase.inventory;


import com.pipecode.kardexsales.model.web.ChangeProductQtyRequest;

@FunctionalInterface
public interface ChangeProductQty {
    void apply(ChangeProductQtyRequest request);
}
