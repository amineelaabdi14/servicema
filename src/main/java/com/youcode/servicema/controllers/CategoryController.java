package com.youcode.servicema.controllers;

import com.youcode.servicema.domain.entities.Category;
import com.youcode.servicema.dto.responses.CategoryDto;
import com.youcode.servicema.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getCategories() {
       Optional<List<Category>> categories = categoryService.getCategories();
       List<Category> categoryList = categories.get();
       if(categories.isPresent()) {
            return ResponseEntity.ok(CategoryDto.fromCategoryList(categoryList));       }
         return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity addCategory(@RequestBody CategoryDto category) {
        Category newCategory = categoryService.addCategory(Category.builder().name(category.getCategory()).build());
        return new ResponseEntity<>(new HashMap<>() {
            {
                put("message", "Category deleted successfully");
            }
        }, HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        if (!categoryService.getCategories().get().stream().anyMatch(category -> category.getId().equals(id))) {
            return ResponseEntity.notFound().build();
        }
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new HashMap<>(){
            {
                put("message", "Category deleted successfully");
            }
        });
    }
}
