package com.pragma.foodcourtservice.infrastructure.output.jpa.adapter;

import com.pragma.foodcourtservice.domain.model.Category;
import com.pragma.foodcourtservice.domain.spi.ICategoryPersistencePort;
import com.pragma.foodcourtservice.infrastructure.exception.CategoryDoesntExistException;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.CategoryEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.ICategoryRepository;

import java.util.Optional;
/**
 * Class adapter that implements the methods to communicate with the Category entity on persistence layer and the Category
 * model in the domain layer. It uses the JpaRepository and a mapper to map CategoryEntity to Category.
 */
public class CategoryJpaAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    public CategoryJpaAdapter(ICategoryRepository categoryRepository, CategoryEntityMapper categoryEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }
    /**
     * Gets a category from the persistence layer given its id.
     * @param id the category's id to be searched.
     * @return The category obtained.
     * @throws CategoryDoesntExistException if the category with the provided id doesn't exist.
     */
    @Override
    public Category getCategory(Long id) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty()){
            throw new CategoryDoesntExistException();
        }
        return categoryEntityMapper.toCategory(categoryOptional.get());
    }
}
