package com.pipecode.kardexsales.gateway.db;


import com.pipecode.kardexsales.model.entity.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Long> {
}
