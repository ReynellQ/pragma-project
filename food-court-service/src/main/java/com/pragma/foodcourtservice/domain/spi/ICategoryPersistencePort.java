package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.Category;

/**
 * Interface port that defines the methods to communicate with the Category entity on persistence layer and the Category
 * model in the domain layer.
 */
public interface ICategoryPersistencePort {
    /**
     * Gets a category from the persistence layer given its id.
     * @param id the category's id to be searched.
     * @return The category obtained.
     */
    Category getCategory(Long id);
}
