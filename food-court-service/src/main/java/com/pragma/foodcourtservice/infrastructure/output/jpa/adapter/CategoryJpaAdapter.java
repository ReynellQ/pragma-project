package com.pragma.foodcourtservice.infrastructure.output.jpa.adapter;

import com.pragma.foodcourtservice.domain.model.Category;
import com.pragma.foodcourtservice.domain.spi.ICategoryPersistencePort;
import com.pragma.foodcourtservice.infrastructure.exception.CategoryNotFoundException;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.CategoryEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
/**
 * Class adapter that implements the methods to communicate with the Category entity on persistence layer and the Category
 * model in the domain layer. It uses the JpaRepository and a mapper to map CategoryEntity to Category.
 */
@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    /**
     * Gets a category from the persistence layer given its id.
     * @param id the category's id to be searched.
     * @return The category obtained.
     * @throws CategoryNotFoundException if the category with the provided id doesn't exist.
     */
    @Override
    public Category getCategory(Long id) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty()){
            throw new CategoryNotFoundException();
        }
        return categoryEntityMapper.toCategory(categoryOptional.get());
    }
}
