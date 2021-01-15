package com.arthurdrabazha.openmind.controller;

import com.arthurdrabazha.openmind.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryController {

    private final CategoryRepository categoryService;

    @Autowired
    public CategoryController(CategoryRepository categoryService) {
        this.categoryService = categoryService;
    }

    public String index() {
        categoryService.
    }
}
