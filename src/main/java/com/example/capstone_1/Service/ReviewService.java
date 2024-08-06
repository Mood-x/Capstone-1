package com.example.capstone_1.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_1.Model.Product;
import com.example.capstone_1.Model.Review;
import com.example.capstone_1.Model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ProductService productService;
    private final UserService userService; 

    public String addReview(Review review){
        List<Product> products = productService.getProducts();

        boolean userExists = false; 
        for(User user : userService.getUsers()){
            if(user.getId().equals(review.getUserId())){
                userExists = true; 
                break; 
            }
        }
        if(!userExists){
            return "User not found"; 
        }

        for(Product product : products){
            if(product.getId().equals(review.getProductId())){

                boolean alreadyReviewed = false;

                if(product.getReviews() != null){
                    for(Review reviewed : product.getReviews()){
                        if(reviewed.getUserId().equals(review.getUserId())){
                            alreadyReviewed = true; 
                            break; 
                        }
                    }
                }

                if(alreadyReviewed){
                    return "User has already reviewed this product"; 
                }

                if(product.getReviews() == null){
                    product.setReviews(new ArrayList<>());
                }

                product.getReviews().add(review); 
                updateAvareageRating(product);
                return "Review added successfully"; 
            }
        }

        return "Product not found"; 
    }


    public List<Review> getReviewsByProductId(String productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return new ArrayList<>(product.getReviews());
        }
        return new ArrayList<>();
    }

    public List<Review> getAllReviews() {
        List<Product> products = productService.getProducts();
        List<Review> allReviews = new ArrayList<>();

        for (Product product : products) {
            if (product.getReviews() != null) {
                allReviews.addAll(product.getReviews());
            }
        }
        return allReviews;
    }

    public void updateAvareageRating(Product product){
        List<Review> reviews = product.getReviews(); 
        if(reviews != null && !reviews.isEmpty()){
            double totalRating = 0.0; 
            for(Review review : reviews){
                totalRating += review.getRating(); 
            }

            double averageRating = totalRating / reviews.size(); 
            product.setAverageRating(averageRating);
        }else {
            product.setAverageRating(0.0);
        }
    }

    public String deleteReview(String productId, String userId) {
        Product product = productService.getProductById(productId);
        if (product != null && product.getReviews() != null) {
            Review reviewToRemove = null;
            for (Review review : product.getReviews()) {
                if (review.getUserId().equals(userId)) {
                    reviewToRemove = review;
                    break;
                }
            }
            if (reviewToRemove != null) {
                product.getReviews().remove(reviewToRemove);
                updateAvareageRating(product);
                return "Review deleted successfully";
            } else {
                return "Review not found";
            }
        }
        return "Product not found";
    }

    public String updateReview(String productId, String userId, Review updatedReview) {
        Product product = productService.getProductById(productId);
        if (product != null && product.getReviews() != null) {
            for (Review review : product.getReviews()) {
                if (review.getUserId().equals(userId)) {
                    review.setRating(updatedReview.getRating());
                    review.setComment(updatedReview.getComment());
                    updateAvareageRating(product);
                    return "Review updated successfully";
                }
            }
            return "Review not found";
        }
        return "Product not found";
    }
}
