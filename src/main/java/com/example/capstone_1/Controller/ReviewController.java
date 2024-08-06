package com.example.capstone_1.Controller;

import java.util.List;

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
import com.example.capstone_1.Model.Review;
import com.example.capstone_1.Service.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping
    public ResponseEntity getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews(); 
        
        if(reviews.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No reviews found for this product")); 
        }

        return ResponseEntity.status(200).body(new ApiResponse("All reviews retrieved successfully", reviews));
    }


    @GetMapping("/product/{productId}")
    public ResponseEntity getReviewsByProductId(@PathVariable String productId) {
        List<Review> reviews = reviewService.getReviewsByProductId(productId); 
        
        if(reviews.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No reviews found for this product")); 
        }
        return ResponseEntity.status(200).body(new ApiResponse("Reviews retrieved successfully", reviews));
    }


    @PostMapping("/add")
    public ResponseEntity addReview(@Valid @RequestBody Review review, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        String result = reviewService.addReview(review);
        if (result.equals("Review added successfully")) {
            return ResponseEntity.status(200).body(new ApiResponse(result));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse(result));
        }
    }

    @DeleteMapping("/delete/{productId}/{userId}")
        public ResponseEntity deleteReview(@PathVariable String productId, @PathVariable String userId) {
        String result = reviewService.deleteReview(productId, userId);
        if (result.equals("Review deleted successfully")) {
            return ResponseEntity.status(200).body(new ApiResponse(result));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse(result));
        }
    }

    @PutMapping("/update/{productId}/{userId}")
    public ResponseEntity updateReview(@PathVariable String productId, @PathVariable String userId, @RequestBody Review updatedReview) {
        String result = reviewService.updateReview(productId, userId, updatedReview);
        if (result.equals("Review updated successfully")) {
            return ResponseEntity.status(200).body(new ApiResponse(result));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse(result));
        }
    }
}
