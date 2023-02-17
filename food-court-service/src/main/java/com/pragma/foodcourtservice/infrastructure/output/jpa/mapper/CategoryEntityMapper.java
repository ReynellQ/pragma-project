package com.pragma.foodcourtservice.infrastructure.output.jpa.mapper;

import com.pragma.foodcourtservice.domain.model.Category;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapping interface to map:
 *
 * - CategoryEntity to Category.
 */
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryEntityMapper {
    Category toCategory(CategoryEntity categoryEntity);
}
