package com.youcode.servicema.seeder;

import com.youcode.servicema.domain.entities.Authority;
import com.youcode.servicema.domain.entities.Category;
import com.youcode.servicema.domain.enums.AuthorityEnum;
import com.youcode.servicema.repositories.AuthorityRepository;
import com.youcode.servicema.repositories.CategoryRepository;
import com.youcode.servicema.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            seedCategories();
        }
    }

    public void seedCategories() {
        List<String> categories = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();
        categories.add("Cleaning");
        categories.add("Landscaping");
        categories.add("Home Improvement");
        categories.add("Personal Care");
        categories.add("Baby Sitting");
        categories.add("Transportation");
        categories.add("Education");
        categories.add("Technology");
        for (String category : categories) {
            Category authority = Category.builder()
                    .name(category)
                    .build();
            categoryList.add(authority);
        }
        categoryRepository.saveAll(categoryList);
    }
}
