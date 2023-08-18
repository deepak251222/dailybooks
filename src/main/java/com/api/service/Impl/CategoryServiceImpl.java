package com.api.service.Impl;

import com.api.entity.Category;
import com.api.exception.ResourceNotFoundException;
import com.api.dao.CategoryRepo;
import com.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

   // create category
    @Override
    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    // get category by id
    @Override
    public Category getCategory(long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with ID " + categoryId + " not found"));
        return category;
    }
      // delete category by id
    @Override
    public boolean deleteCategories(long categoryId) {
        Optional<Category> category =categoryRepo.findById(categoryId);
        if (category.isPresent()) {
            categoryRepo.delete(category.get());
            return true;
        }
        return false;
    }
    //update category
    @Override
    public Category updateCategory(Category updatedCategory, long categoryId) {
        System.out.println(updatedCategory);
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category with ID " + categoryId + " not found"));
        category.setCategoryName(updatedCategory.getCategoryName());
        category.setExpense(updatedCategory.isExpense());
        category.setIncome(updatedCategory.isIncome());
        return categoryRepo.save(category);
    }
    // get all category
    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("No categories found");
        }
        return categories;
    }
}
