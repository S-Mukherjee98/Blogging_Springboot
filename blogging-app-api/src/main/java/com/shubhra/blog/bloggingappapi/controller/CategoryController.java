package com.shubhra.blog.bloggingappapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubhra.blog.bloggingappapi.payload.ApiResponse;
import com.shubhra.blog.bloggingappapi.payload.CategoryDto;
import com.shubhra.blog.bloggingappapi.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        CategoryDto categoryCreated = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(categoryCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}")

    ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {
        CategoryDto cat = this.categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(cat);
    }

    @GetMapping("/")
    ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> cat = this.categoryService.getAllCategory();
        return new ResponseEntity<List<CategoryDto>>(cat, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
            @PathVariable Integer categoryId) {

        CategoryDto cat = this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<CategoryDto>(cat, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{categoryId}")
    ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {

        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully", true), HttpStatus.OK);
    }
}
