package com.shubhra.blog.bloggingappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubhra.blog.bloggingappapi.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
