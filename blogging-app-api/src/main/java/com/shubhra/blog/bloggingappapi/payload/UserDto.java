package com.shubhra.blog.bloggingappapi.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4,message = "User Name Must be of 4 Character Minimum")
    private String name;
    @Email(message = "Email is Not Valid")
    private String email;
    @NotEmpty
    @Size(min = 3, max = 8,message = "Password Must Be of Minimum 3 chars and Maximum 8 chars")
    private String password;
    @NotEmpty
    private String about;
}
