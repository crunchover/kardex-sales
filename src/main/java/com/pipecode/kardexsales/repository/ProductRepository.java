package com.pipecode.kardexsales.repository;

import com.pipecode.kardexsales.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p from Product p join p.category pc where p.name=:productName and pc.name=:categoryName")
    Optional<Product> findByNameAndCategoryName(String productName,String categoryName);

}
