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
import com.example.capstone_1.Model.Category;
import com.example.capstone_1.Service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService; 

// ===============================================================================================

    @GetMapping
    public ResponseEntity getCategories(){
        return ResponseEntity.status(200).body(categoryService.getCategories()); 
    }
    
// ===============================================================================================


    @GetMapping("/{id}")
    public ResponseEntity getCategoryById(@PathVariable String id){
        Category category = categoryService.getCategoryById(id); 
        if(category != null){
            return ResponseEntity.status(200).body(category); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category not found")); 
    }

// ===============================================================================================


    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        boolean idNotUsed = categoryService.addCategory(category);
        if(idNotUsed){
            return ResponseEntity.status(200).body("Category added"); 
        }
        return ResponseEntity.status(400).body("This Id already used"); 
    }

// ===============================================================================================


    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable String id, @Valid @RequestBody Category category, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        boolean isFound = categoryService.updateCategory(id, category); 
        
        if(isFound){
        return ResponseEntity.status(200).body(new ApiResponse("Category updated")); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category not found")); 
    }

// ===============================================================================================


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id){
        boolean isFound = categoryService.deleteCategory(id); 
        
        if(isFound){
        return ResponseEntity.status(200).body(new ApiResponse("Category deleted")); 
        }

        return ResponseEntity.status(400).body(new ApiResponse("Category not found")); 
    }

// ===============================================================================================

}
