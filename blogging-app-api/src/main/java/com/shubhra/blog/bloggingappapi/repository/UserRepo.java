package com.shubhra.blog.bloggingappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubhra.blog.bloggingappapi.entity.User;

public interface UserRepo extends JpaRepository<User,Integer> {
    
}
