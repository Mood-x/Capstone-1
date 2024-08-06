package com.example.capstone_1.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @NotEmpty(message = "ID must not be empty")
    @Size(min = 2, message = "ID have to be more than 2 length long") 
    private String id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, message = "Name have to be more than 3 length long")
    private String name;

    @NotNull(message = "Price must not be null")
    @Positive
    private double price; 

    @NotEmpty(message = "Category ID must not be empty")
    @Size(min = 2, message = "Category ID have to be more than 2 length long")
    private String categoryId;

    private List<Review> reviews = new ArrayList<>(); 
    private double averageRating = 0.0; 
}
