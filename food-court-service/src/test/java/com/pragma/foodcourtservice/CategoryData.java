package com.pragma.foodcourtservice;

import com.pragma.foodcourtservice.domain.model.Category;

public class CategoryData {
    public static Category CATEGORY_001 = new Category(1l, "Categoria 1", "Descripcion de la categoria 1");
    public static Category NON_INSERTED_CATEGORY = new Category(100000l, "Categoria inexistente",
            "Descripcion de la categoria inexistente");
}
