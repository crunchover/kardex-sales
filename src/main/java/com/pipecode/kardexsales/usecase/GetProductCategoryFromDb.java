package com.pipecode.kardexsales.usecase;

import com.pipecode.kardexsales.gateway.db.CategoryRepository;
import com.pipecode.kardexsales.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProductCategoryFromDb implements GetProductCategory {

    private final CategoryRepository categoryRepository;

    @Autowired
    public GetProductCategoryFromDb(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category get(String name) {
        final var category= categoryRepository.findByName(name);

         if(category.isPresent()){
            return category.get();
        }else{
            Category newCategory= Category.builder()
                    .name(name).build();
          return categoryRepository.save(newCategory);

        }

    }
}
