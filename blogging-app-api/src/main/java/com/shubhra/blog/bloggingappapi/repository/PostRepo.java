package com.shubhra.blog.bloggingappapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubhra.blog.bloggingappapi.entity.Category;
import com.shubhra.blog.bloggingappapi.entity.Post;
import com.shubhra.blog.bloggingappapi.entity.User;

public interface PostRepo extends JpaRepository <Post,Integer>{
    
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);


}
