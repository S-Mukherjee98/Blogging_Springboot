package com.shubhra.blog.bloggingappapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubhra.blog.bloggingappapi.entity.Category;
import com.shubhra.blog.bloggingappapi.exception.ResourceNotFound;
import com.shubhra.blog.bloggingappapi.payload.CategoryDto;
import com.shubhra.blog.bloggingappapi.repository.CategoryRepo;
import com.shubhra.blog.bloggingappapi.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService  {


    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    //create Category
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        // TODO Auto-generated method stub

        Category cat = this.modelMapper.map(categoryDto, Category.class);
       Category added= this.categoryRepo.save(cat);
        return this.modelMapper.map(added, CategoryDto.class);
    }

    //Update Category
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        // TODO Auto-generated method stub
        Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category", "Category Id", categoryId));
        
        cat.setCategoryId(categoryId);
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCat= this.categoryRepo.save(cat);
        return this.modelMapper.map(updatedCat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        // TODO Auto-generated method stub

        Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category","Category Id", categoryId));
        this.categoryRepo.deleteById(categoryId);
        
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        // TODO Auto-generated method stub
        Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category","Category Id",categoryId));

        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        // TODO Auto-generated method stub
        List<Category> cat= this.categoryRepo.findAll();
        List<CategoryDto> catdto= cat.stream().map(cat1->this.modelMapper.map(cat1, CategoryDto.class)).collect(Collectors.toList());
        return catdto;
    }
    
    
}
