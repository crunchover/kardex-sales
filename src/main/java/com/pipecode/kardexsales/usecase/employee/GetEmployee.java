package com.pipecode.kardexsales.usecase.employee;

import com.pipecode.kardexsales.model.entity.Employee;

@FunctionalInterface
public interface GetEmployee {
    Employee get(String identification,
                           String productName, String categoryName);
}
