package com.fezor.spring_finance_control.mapper;

import com.fezor.spring_finance_control.dto.CategoryRequest;
import com.fezor.spring_finance_control.dto.CategoryResponse;
import com.fezor.spring_finance_control.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryRequest request);

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponseList(List<Category> categories);
}
