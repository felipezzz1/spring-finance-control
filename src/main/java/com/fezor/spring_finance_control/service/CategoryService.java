package com.fezor.spring_finance_control.service;

import com.fezor.spring_finance_control.model.Category;
import com.fezor.spring_finance_control.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not Found"));
    }

    public Category update(Long id, Category categoryUpdated) {
        Category category = findById(id);
        category.setName(categoryUpdated.getName());
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}
