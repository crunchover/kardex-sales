package com.pipecode.kardexsales.usecase.employee;

import com.pipecode.kardexsales.exception.NotFoundElementException;
import com.pipecode.kardexsales.repository.EmployeeRepository;
import com.pipecode.kardexsales.model.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetEmployeeImpl implements GetEmployee {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee get(String identification, String name, String lastname) {

        return employeeRepository.findByIdentificationAndNameAndLastName(identification, name, lastname)
                .orElseThrow(() -> new NotFoundElementException("Error Empleado no existe"));
    }
}