package com.shubhra.blog.bloggingappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubhra.blog.bloggingappapi.entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
    
}
