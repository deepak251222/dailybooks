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
        public ResponseEntity<String> createCategory(@RequestBody Category category) {
            Category createdCategory = categoryService.createCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body("Category Created Successful");
        }
        @GetMapping("/{categoryId}")
        public ResponseEntity<Category> getCategory(@PathVariable long categoryId) {
            Category category = categoryService.getCategory(categoryId);
            return ResponseEntity.ok(category);
        }
        @DeleteMapping("/{categoryId}")
        public ResponseEntity<String> deleteCategory(@PathVariable long categoryId) {
                 categoryService.deleteCategories(categoryId);
                return ResponseEntity.status(HttpStatus.OK).body("Category Delete SuccessFul  : " + categoryId);
            }
        @PutMapping("/{categoryId}")
        public ResponseEntity<String> updateCategory(@RequestBody Category updatedCategory, @PathVariable long categoryId) {
            System.out.println(updatedCategory.toString());
            Category category = categoryService.updateCategory(updatedCategory, categoryId);
            return ResponseEntity.status(HttpStatus.OK).body("Category Update SuccessFul");
        }
        @GetMapping
        public ResponseEntity<List<Category>> getAllCategories() {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(categories);
        }
    }

