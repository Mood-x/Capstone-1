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
import com.example.capstone_1.Model.MerchantStock;
import com.example.capstone_1.Service.MerchantStockService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;
    // private final UserService userService; 
    // private final ProductService productService; 

// ===============================================================================================

    @GetMapping
    public ResponseEntity getMerchantStocks(){
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks()); 
    }

// ===============================================================================================

    @GetMapping("/{id}")
    public ResponseEntity getMerchantStockById(@PathVariable String id){
        MerchantStock merchantStock = merchantStockService.getMerchantStockById(id);
        
        if(merchantStock != null){
            return ResponseEntity.status(200).body(merchantStock); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant stock not found")); 
    }

// ===============================================================================================

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors error){
        String result = merchantStockService.addMerchantStock(merchantStock); 

        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        if(result.equals("Merchant Stock added successfully")){
            return ResponseEntity.status(200).body(result); 
        }
        return ResponseEntity.status(400).body(result); 
    }

// ===============================================================================================


    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable String id, @Valid @RequestBody MerchantStock merchantStock, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }
        boolean isFound = merchantStockService.updateMerchantStock(id, merchantStock); 
        
        if(isFound){
        return ResponseEntity.status(200).body(new ApiResponse("Merchant stock updated")); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant stock not found")); 
    }

// ===============================================================================================

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStocks(@PathVariable String id){
        boolean isFound = merchantStockService.deleteMerchantStocks(id); 
        
        if(isFound){
        return ResponseEntity.status(200).body(new ApiResponse("Merchant stock deleted")); 
        }

        return ResponseEntity.status(400).body(new ApiResponse("Merchant stock not found")); 
    }

// ===============================================================================================

    @PutMapping("/addToStock/{productId}/{merchantId}/{additionalStock}")
    public ResponseEntity addStock(@PathVariable String productId, @PathVariable String merchantId, @PathVariable int additionalStock){
        String result = merchantStockService.addStock(productId, merchantId, additionalStock); 
        if(result.equals("Add " + additionalStock + " to merchant stock")){
            return ResponseEntity.status(200).body(result); 
        }else {
            return ResponseEntity.status(405).body(result); 
        }
    }
}
