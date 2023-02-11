package com.shubhra.blog.bloggingappapi.service;

import java.util.List;

import com.shubhra.blog.bloggingappapi.payload.UserDto;


public interface UserService {
    
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
