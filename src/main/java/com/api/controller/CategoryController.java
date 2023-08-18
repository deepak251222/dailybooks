package com.api.controller;

import com.api.entity.Category;
import com.api.service.Impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
        private  CategoryServiceImpl categoryService;

        @PostMapping
        public ResponseEntity<Category> createCategory(@RequestBody Category category) {
            Category createdCategory = categoryService.createCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        }
        @GetMapping("/{categoryId}")
        public ResponseEntity<Category> getCategory(@PathVariable long categoryId) {
            Category category = categoryService.getCategory(categoryId);
            return ResponseEntity.ok(category);
        }
        @DeleteMapping("/{categoryId}")
        public ResponseEntity<Void> deleteCategory(@PathVariable long categoryId) {
            boolean deleted = categoryService.deleteCategories(categoryId);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        @PutMapping("/{categoryId}")
        public ResponseEntity<Category> updateCategory(@RequestBody Category updatedCategory, @PathVariable long categoryId) {
            System.out.println(updatedCategory.toString());
            Category category = categoryService.updateCategory(updatedCategory, categoryId);
            return ResponseEntity.ok(category);
        }
        @GetMapping
        public ResponseEntity<List<Category>> getAllCategories() {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(categories);
        }
    }

