package com.pipecode.kardexsales.usecase.employee;

import com.pipecode.kardexsales.repository.EmployeeRepository;
import com.pipecode.kardexsales.model.entity.Employee;
import com.pipecode.kardexsales.model.web.CreateEmployeeRequest;
import com.pipecode.kardexsales.validator.GenericRequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateEmployeeImpl implements CreateEmployee {

    private final GenericRequestValidator validator;

    private final EmployeeRepository employeeRepository;

    @Override
    public void apply(CreateEmployeeRequest request) {

        validator.accept(request);

        //Todo:// Fuera de scope realizar mejores validaciones
        Employee employee= new Employee();
        employee.setIdentification(request.getIdentification());
        employee.setName(request.getName());
        employee.setLastName(request.getLastName());
        employee.setAddress(request.getAddress());
        employeeRepository.save(employee);

    }
}
