package com.example.capstone_1.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_1.Model.Category;
import com.example.capstone_1.Model.Product;
import com.example.capstone_1.Model.Review;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryService categoryService; 

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts(){
        return products; 
    }
    
    public Product getProductById(String id){
        for(Product product : products){
            if(product.getId().equals(id)){
                return product; 
            }
        }
        return null; 
    }

    public String addProduct(Product product){
        List<Category> categories = categoryService.getCategories();

        for(Product productId : products){
            if(productId.getId().equals(product.getId())){
                return "Product ID already used"; 
            }
        }
        
        boolean categoryExists = false; 
        for(Category category : categories){
            if(category.getId().equals(product.getCategoryId())){
                categoryExists = true; 
                break; 
            }
        }
        
        if(!categoryExists){
            return "Category ID does not exist"; 
        }

        products.add(product); 
        return "Product added successfully"; 
    }


    public boolean updateProduct(String id, Product product){
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getId().equals(id)){
                product.setId(product.getId());
                products.set(i, product);
                return true; 
            }
        }
        return false; 
    }

    public boolean deleteProduct(String id){
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getId().equals(id)){
                products.remove(i);
                return true; 
            }
        }
        return false; 
    }
    
    // ===============================================
    public String addReview(String productId, Review review) {
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                product.getReviews().add(review);
                return "Review added successfully";
            }
        }
        return "Product not found";
    }

    public List<Review> getReviewsByProductId(String productId) {
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                return new ArrayList<>(product.getReviews());
            }
        }
        return new ArrayList<>();
    }
}
