package com.shubhra.blog.bloggingappapi.service;

import java.util.List;

import com.shubhra.blog.bloggingappapi.payload.CategoryDto;



public interface CategoryService {
    //create
    CategoryDto createCategory(CategoryDto categoryDto);
    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    //delete
    void deleteCategory(Integer categoryId);

    //get
    CategoryDto getCategoryById(Integer categoryId);

    //get All
    
    List<CategoryDto> getAllCategory();
}
