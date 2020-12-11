package com.pipecode.kardexsales.usecase.product;

import com.pipecode.kardexsales.gateway.db.CategoryRepository;
import com.pipecode.kardexsales.model.entity.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetProductCategoryFromDb implements GetProductCategory {

    private final CategoryRepository categoryRepository;

    @Override
    public Category get(String name) {
        final var category= categoryRepository.findByName(name);

         if(category.isPresent()){
            return category.get();
        }else{
            Category newCategory= new Category();
            newCategory.setName(name);
          return categoryRepository.save(newCategory);

        }

    }
}
