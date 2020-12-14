package com.pipecode.kardexsales.repository;


import com.pipecode.kardexsales.model.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
