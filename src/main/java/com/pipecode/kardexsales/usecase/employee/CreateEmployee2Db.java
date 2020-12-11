package com.pipecode.kardexsales.usecase.employee;

import com.pipecode.kardexsales.gateway.db.EmployeeRepository;
import com.pipecode.kardexsales.model.entity.Employee;
import com.pipecode.kardexsales.model.web.CreateEmployeeRequest;
import com.pipecode.kardexsales.validator.BaseValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateEmployee2Db implements CreateEmployee {

    private final BaseValidator validator;

    private final EmployeeRepository employeeRepository;

    @Override
    public void apply(CreateEmployeeRequest request) {

        validator.accept(request);

        //Todo:// Fuera de scope realizar mejores validaciones
        Employee employee= new Employee();
        employee.setName(request.getName());
        employee.setLastName(request.getLastName());
        employee.setAddress(request.getAddress());
        employeeRepository.save(employee);

    }
}
