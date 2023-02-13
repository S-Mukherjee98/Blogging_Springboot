package com.shubhra.blog.bloggingappapi.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;
    @NotEmpty(message = "Category title must have to provide")
    private String categoryTitle;
    @NotEmpty(message="Category Description Must have to provide")
    private String categoryDescription;

    
}
