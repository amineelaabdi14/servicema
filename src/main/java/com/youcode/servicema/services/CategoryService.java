package com.youcode.servicema.services;

import com.youcode.servicema.domain.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public Optional<List<Category>> getCategories();
    public Category addCategory(Category category);
    public void deleteCategory(Long id);
    public Optional<Category> getCategoryById(Long id);
}
