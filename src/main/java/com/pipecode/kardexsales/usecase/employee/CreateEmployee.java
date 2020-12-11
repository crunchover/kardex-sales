package com.pipecode.kardexsales.usecase.employee;

import com.pipecode.kardexsales.model.web.CreateEmployeeRequest;

@FunctionalInterface
public interface CreateEmployee {
    void apply(CreateEmployeeRequest request);
}
