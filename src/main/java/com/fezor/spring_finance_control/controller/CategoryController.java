package com.fezor.spring_finance_control.controller;

import com.fezor.spring_finance_control.dto.CategoryRequest;
import com.fezor.spring_finance_control.dto.CategoryResponse;
import com.fezor.spring_finance_control.mapper.CategoryMapper;
import com.fezor.spring_finance_control.model.Category;
import com.fezor.spring_finance_control.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;
    private final CategoryMapper mapper;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request) {

        Category category = service.create(request);

        URI location = URI.create("/apí/v1/categories/" + category.getId());

        return ResponseEntity.created(location)
                .body(mapper.toResponse(category));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll(){
        return ResponseEntity.ok(
                mapper.toResponseList(service.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {

        return ResponseEntity.ok(
                mapper.toResponse(service.findById(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody CategoryRequest request) {

        Category updatedCategory = service.update(id, request);

        return ResponseEntity.ok(
                mapper.toResponse(updatedCategory)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
