package com.shubhra.blog.bloggingappapi.service.impl;

import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shubhra.blog.bloggingappapi.entity.Category;
import com.shubhra.blog.bloggingappapi.entity.Post;
import com.shubhra.blog.bloggingappapi.entity.User;
import com.shubhra.blog.bloggingappapi.exception.ResourceNotFound;
import com.shubhra.blog.bloggingappapi.payload.PostDto;
import com.shubhra.blog.bloggingappapi.payload.PostResponse;
import com.shubhra.blog.bloggingappapi.repository.PostRepo;
import com.shubhra.blog.bloggingappapi.repository.UserRepo;
import com.shubhra.blog.bloggingappapi.repository.CategoryRepo;
import com.shubhra.blog.bloggingappapi.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer catId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "User Id: ", userId));

        Category category = this.categoryRepo.findById(catId)
                .orElseThrow(() -> new ResourceNotFound("Category", "CategoryId", catId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setCategory(category);
        post.setUser(user);
        post.setAddedDate(new Date());

        Post newpost = this.postRepo.save(post);

        return this.modelMapper.map(newpost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "Post Id: ", postId));
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setImageName(postDto.getImageName());

        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFound("Post", "Post Id : ", postId));
        this.postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {

        Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

        Page<Post> pagePosts = this.postRepo.findAll(p);
        List<Post> allpost = pagePosts.getContent();
        List<PostDto> pdtos = allpost.stream().map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(pdtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalPage(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        return postResponse;
    }

    @Override
    public PostDto getPostByPostId(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "Post Id: ", postId));

        PostDto postDtos = this.modelMapper.map(post, PostDto.class);
        return postDtos;

    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFound("Category", "Category Id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "User Id: ", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        // TODO Auto-generated method stub

        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postdtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postdtos;
    }

}
