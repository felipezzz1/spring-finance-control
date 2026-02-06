package com.fezor.spring_finance_control.service;

import com.fezor.spring_finance_control.dto.CategoryRequest;
import com.fezor.spring_finance_control.mapper.CategoryMapper;
import com.fezor.spring_finance_control.model.Category;
import com.fezor.spring_finance_control.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    public Category create(CategoryRequest request) {

        Category category = mapper.toEntity(request);

        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not Found"));
    }

    public Category update(Long id, CategoryRequest request) {
        Category category = findById(id);

        category.setName(request.getName());

        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}
