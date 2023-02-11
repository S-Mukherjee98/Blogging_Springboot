package com.shubhra.blog.bloggingappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubhra.blog.bloggingappapi.payload.UserDto;
import com.shubhra.blog.bloggingappapi.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    //Post-Create User

    @PostMapping("/")
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
      UserDto userDtoCreated=  this.userService.createUser(userDto);
      return new ResponseEntity<UserDto>(userDtoCreated, HttpStatus.CREATED);
    }

    // Get - show user

    @GetMapping
    ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> userDto = this.userService.getAllUsers();
        return new ResponseEntity<List<UserDto>>(userDto, HttpStatus.FOUND);
    }
    
}
