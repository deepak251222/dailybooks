package com.api.service;

import com.api.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category categories);

    Category getCategory(long categoryId);

    boolean deleteCategories(long categoryId);

    Category updateCategory(Category categories, long categoryId);

    List<Category> getAllCategories();
}
