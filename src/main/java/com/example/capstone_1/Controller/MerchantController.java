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
import com.example.capstone_1.Model.Merchant;
import com.example.capstone_1.Service.MerchantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService; 
// ===============================================================================================

    @GetMapping
    public ResponseEntity getMerchants(){
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }

// ===============================================================================================

    @GetMapping("/{id}")
    public ResponseEntity getMerchantById(@PathVariable String id){
        Merchant merchant = merchantService.getMerchantById(id); 

        if(merchant != null){
            return ResponseEntity.status(200).body(merchant); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant not found")); 
    }

// ===============================================================================================

    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        boolean idNotUsed = merchantService.addMerchant(merchant);
        if(idNotUsed){
            return ResponseEntity.status(200).body("Merchant added"); 
        }
        return ResponseEntity.status(400).body("This Id already used"); 
    }

// ===============================================================================================


    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id, @Valid @RequestBody Merchant merchant, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }
        boolean isFound = merchantService.updateMerchant(id, merchant); 
        
        if(isFound){
        return ResponseEntity.status(200).body(new ApiResponse("Merchant updated")); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant not found")); 
    }

// ===============================================================================================

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMarchant(@PathVariable String id){
        boolean isFound = merchantService.deleteMerchant(id); 
        
        if(isFound){
        return ResponseEntity.status(200).body(new ApiResponse("Merchant deleted")); 
        }

        return ResponseEntity.status(400).body(new ApiResponse("Merchant not found")); 
    }

// ===============================================================================================

}
