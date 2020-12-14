package com.pipecode.kardexsales.repository;

import com.pipecode.kardexsales.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByIdentificationAndNameAndLastName(String identification,
                                                              String employeName,
                                                              String employeLastName);
}
