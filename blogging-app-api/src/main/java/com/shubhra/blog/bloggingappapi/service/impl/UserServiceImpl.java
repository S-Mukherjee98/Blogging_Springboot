package com.shubhra.blog.bloggingappapi.service.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubhra.blog.bloggingappapi.exception.*;
import com.shubhra.blog.bloggingappapi.entity.User;
import com.shubhra.blog.bloggingappapi.payload.UserDto;
import com.shubhra.blog.bloggingappapi.repository.UserRepo;
import com.shubhra.blog.bloggingappapi.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        // TODO Auto-generated method stub
        User user = this.userDtoToUser(userDto);
        User saveduser= this.userRepo.save(user);

        return this.userToUserDto(saveduser) ;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User","Id",userId));

        user.setId(userId);
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser= this.userRepo.save(user);
        UserDto userDto2=this.userToUserDto(updatedUser);

        return userDto2;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        // TODO Auto-generated method stub

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User", "Id", userId));

        return this.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        // TODO Auto-generated method stub

        List<User> user=this.userRepo.findAll();
       List<UserDto> userDtos= user.stream().map(user1->this.userToUserDto(user1)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        // TODO Auto-generated method stub
        User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("User", "Id", userId));
        
        this.userRepo.delete(user);
    }

    public User userDtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        // user.setId(userDto.getId());
        // user.setName(userDto.getName());
        // user.setEmail(userDto.getEmail());
        // user.setPassword(userDto.getPassword());
        // user.setAbout(userDto.getAbout());

        return user;
    }
    
    public UserDto userToUserDto(User user){
        UserDto userDto=this.modelMapper.map(user, UserDto.class);
        // userDto.setId(user.getId());    
        // userDto.setName(user.getName());
        // userDto.setEmail(user.getEmail());
        // userDto.setPassword(user.getPassword());
        // userDto.setAbout(user.getAbout());

        return userDto;
    }
}
