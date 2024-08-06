package com.example.capstone_1.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone_1.API.ApiResponse;
import com.example.capstone_1.Model.Product;
import com.example.capstone_1.Service.ProductService;
import com.example.capstone_1.Service.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ReviewService reviewService;


// ===============================================================================================

    @GetMapping
    public ResponseEntity getProducts(){
        return ResponseEntity.status(200).body(productService.getProducts());
    }

// ===============================================================================================


    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable String id){
        Product product = productService.getProductById(id); 

        if(product != null){
            return ResponseEntity.status(200).body(product); 
        }
        return ResponseEntity.status(405).body(new ApiResponse("Product not found")); 
    }


// ===============================================================================================


    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors error){
        String result = productService.addProduct(product); 
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        if(result.equals("Product added successfully")){
            return ResponseEntity.status(200).body(result); 
        }
        return ResponseEntity.status(400).body(result); 
    }


// ===============================================================================================


    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable String id, @Valid @RequestBody Product product, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }
        boolean isFound = productService.updateProduct(id, product); 
        
        if(isFound){
        return ResponseEntity.status(200).body(new ApiResponse("Product updated")); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found")); 
    }

// ===============================================================================================


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        boolean isFound = productService.deleteProduct(id); 
        
        if(isFound){
            return ResponseEntity.status(200).body(new ApiResponse("Product deleted")); 
        }

        return ResponseEntity.status(400).body(new ApiResponse("Product not found")); 
    }

// ===============================================================================================
}
