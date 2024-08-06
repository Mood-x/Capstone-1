package com.example.capstone_1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {



    public static Object getMerchantId;

    @NotEmpty(message = "ID must not be empty")
    @Size(min = 2, message = "ID have to be more than 2 length long") 
    private String id;

    @NotEmpty(message = "Product ID must not be empty")
    @Size(min = 2, message = "Product ID have to be more than 2 length long")
    private String productId; 

    @NotEmpty(message = "Merchant ID must not be empty")
    @Size(min = 2, message = "Merchant ID have to be more than 2 length long")
    private String merchantId; 

    @NotNull(message = "Merchant ID must not be empty")
    @Min(value = 10, message = "Stock have to be more than 10 at start")
    @Positive
    private int stock;

}
