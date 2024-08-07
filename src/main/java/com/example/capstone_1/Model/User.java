package com.example.capstone_1.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {


    @NotEmpty(message = "ID must not be empty")
    @Size(min = 2, message = "ID have to be more than 2 length long") 
    private String id;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 3, message = "Username have to be more than 3 length long")
    private String username;

    @NotEmpty(message = "Password must be not empty!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,20}$", message = "Password size should be between 6 to 20")
    private String password;


    @NotEmpty(message = "Email must not be empty")
    @Email
    private String email; 

    @NotEmpty(message = "Role must be not empty")
    @Pattern(regexp = "^(Admin|Customer|admin|customer)$", message = "you have to be ('Admin', 'Customer')")
    private String role; 


    @NotNull(message = "Balance must not be empty")
    @Positive(message = "Balance have to be positive")
    private double balance;

    private List<Product> wishlist = new ArrayList<>();
    private List<Product> cart = new ArrayList<>();
    private List<Product> purchases = new ArrayList<>();


}
