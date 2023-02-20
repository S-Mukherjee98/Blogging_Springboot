package com.shubhra.blog.bloggingappapi.service;

import java.util.List;

import com.shubhra.blog.bloggingappapi.entity.Post;
import com.shubhra.blog.bloggingappapi.payload.PostDto;

public interface PostService {
    
    //create post
    PostDto createPost(PostDto postDto,Integer userId,Integer catId);

    //Update Post
    PostDto updatePost(PostDto postDto,Integer postId);

    //Delete
    void deletePost(Integer postId);

    //get all posts
    List<PostDto> getAllPost();

    //get post by post Id
    PostDto getPostByPostId(Integer postId);

    //get posts by category
    List<PostDto> getPostByCategory(Integer categoryId);

    //get posts by user
    List<PostDto> getPostByUser(Integer userId);

    //search post
    List<Post> searchPost(String keyword);


}
