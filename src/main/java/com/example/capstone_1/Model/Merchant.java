package com.example.capstone_1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {


    @NotEmpty(message = "ID must not be empty")
    @Size(min = 2, message = "ID have to be more than 2 length long") 
    private String id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, message = "Name have to be more than 3 length long")
    private String name;
}
