package com.pipecode.kardexsales.usecase.product;

import com.pipecode.kardexsales.repository.CategoryRepository;
import com.pipecode.kardexsales.model.entity.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetCategoryImpl implements GetCategory {

    private final CategoryRepository categoryRepository;

    @Override
    public Category get(String name) {
        return categoryRepository.findByName(name).orElseGet(()->newCategory(name));
    }

    private Category newCategory(String name) {
        Category newCategory= new Category();
        newCategory.setName(name);
        return categoryRepository.save(newCategory);
    }
}
