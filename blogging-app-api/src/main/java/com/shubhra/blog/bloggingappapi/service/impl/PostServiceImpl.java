package com.shubhra.blog.bloggingappapi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubhra.blog.bloggingappapi.entity.Category;
import com.shubhra.blog.bloggingappapi.entity.Post;
import com.shubhra.blog.bloggingappapi.entity.User;
import com.shubhra.blog.bloggingappapi.exception.ResourceNotFound;
import com.shubhra.blog.bloggingappapi.payload.PostDto;
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
    public PostDto createPost(PostDto postDto , Integer userId, Integer catId) {
        
        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User", "User Id: ", userId));

        Category category= this.categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFound("Category", "CategoryId", catId));

        Post post=this.modelMapper.map(postDto, Post.class);

        post.setImageName("default.png");
        post.setCategory(category);
        post.setUser(user);
        post.setAddedDate(new Date());

        Post newpost = this.postRepo.save(post);

        return this.modelMapper.map(newpost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFound("Post", "Post Id: ", postId));
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setImageName(postDto.getImageName());

        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        
        Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFound("Post", "Post Id : ", postId));
        this.postRepo.delete(post);
        
    }

    @Override
    public List<PostDto> getAllPost() {
        
        List<Post> posts= this.postRepo.findAll();
        List<PostDto> pdtos =posts.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return pdtos;
    }

    @Override
    public PostDto getPostByPostId(Integer postId) {
        Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFound("Post", "Post Id: ",postId));

        PostDto postDtos = this.modelMapper.map(post, PostDto.class);
        return postDtos;

        
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category", "Category Id", categoryId));
        List<Post> posts= this.postRepo.findByCategory(cat);
        List<PostDto> postDtos= posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User", "User Id: ", userId));
        List<Post> posts= this.postRepo.findByUser(user);
        List<PostDto> postDtos =    posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<Post> searchPost(String keyword) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
