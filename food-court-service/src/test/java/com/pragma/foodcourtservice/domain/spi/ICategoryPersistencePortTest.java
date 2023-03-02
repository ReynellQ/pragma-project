package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.Category;
import com.pragma.foodcourtservice.infrastructure.exception.CategoryNotFoundException;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.CategoryEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ICategoryPersistencePortTest {
    ICategoryRepository categoryRepository;
    CategoryEntityMapper categoryEntityMapper;

    ICategoryPersistencePort categoryPersistencePort;

    @BeforeEach
    void setUp(){
        categoryRepository = mock(ICategoryRepository.class);
        categoryEntityMapper = mock(CategoryEntityMapper.class);
        categoryPersistencePort = new CategoryJpaAdapter(categoryRepository,
                categoryEntityMapper);
    }
    @Test
    void getCategoryCorrectly() {
        Category category = new Category(1L, "Categoria", "Descripcion");
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Categoria", "Descripcion");
        when(categoryRepository.findById(categoryEntity.getId()))
                .thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toCategory(categoryEntity))
                .thenReturn(category);
        assertEquals(category, categoryPersistencePort.getCategory(category.getId()));

    }

    @Test
    void getCategoryButDoesNotExist() {
        Category category = new Category(1L, "Categoria", "Descripcion");
        when(categoryRepository.findById(category.getId()))
                .thenReturn(Optional.empty());

        assertThrows(
                CategoryNotFoundException.class,
                ()-> categoryPersistencePort.getCategory(category.getId()));
    }
}