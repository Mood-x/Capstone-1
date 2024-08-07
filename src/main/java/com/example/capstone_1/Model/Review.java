package com.example.capstone_1.Model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Review {

    @NotEmpty(message = "User ID must not be empty")
    private  String userId; 
    
    @NotEmpty(message = "Product ID must not be empty")
    private String productId;

    @DecimalMin(value = "1.0", message = "Rating must be between 1.0 and 5.0")
    @DecimalMax(value = "5.0", message = "Rating must be between 1.0 and 5.0")
    private double rating;

    private String comment;


}
