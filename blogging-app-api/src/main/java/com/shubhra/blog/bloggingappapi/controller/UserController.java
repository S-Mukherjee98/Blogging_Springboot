package com.shubhra.blog.bloggingappapi.controller;

import java.util.List;


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
import com.shubhra.blog.bloggingappapi.payload.UserDto;
import com.shubhra.blog.bloggingappapi.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    //Post-Create User

    @PostMapping("/")
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
      UserDto userDtoCreated=  this.userService.createUser(userDto);
      return new ResponseEntity<UserDto>(userDtoCreated, HttpStatus.CREATED);
    }


    //Put- Update user

    @PutMapping("/{userId}")
    ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid){
        UserDto uDto= this.userService.updateUser(userDto, uid);
        return ResponseEntity.ok(uDto);
    }

    //Delete - To Delete an user 

    @DeleteMapping("/{userId}")

    //We can write like this

    // ResponseEntity<?> deleteUser(@PathVariable Integer userId){
    //     this.userService.deleteUser(userId);
    //     return new ResponseEntity(Map.of("message","User Deleted Successfully"), HttpStatus.OK);

    // }

    // Or we can create a custom api response class

    ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }




    // Get - show  all user

    @GetMapping("/")
    ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> userDto = this.userService.getAllUsers();
        return new ResponseEntity<List<UserDto>>(userDto, HttpStatus.OK);
    }

    //Get - Show a particular user with a specific user id

    @GetMapping("/{userId}")
    ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }


    
}
