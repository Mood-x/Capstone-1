package com.example.capstone_1.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_1.Model.Product;
import com.example.capstone_1.Model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private List<User> users = new ArrayList<>(); 
    private final ProductService productService; 

    public List<User> getUsers(){
        return new ArrayList<>(users); 
    }

    public User getUserById(String id){
        for(User userId : users){
            if(userId.getId().equals(id)){
                return userId; 
            }
        }
        return null;
    }

    public boolean addUser(User user){
        for(User userId : users){
            if(userId.getId().equals(user.getId())){
                return false; 
            }
        }
        users.add(user); 
        return true; 
    }


    
    public boolean updateUser(String id, User user){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getId().equals(id)){
                users.get(i).setId(id);
                users.set(i, user);
                return true; 
            }
        }
        return false;
    }


    public boolean deleteUser(String id){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getId().equals(id)){
                users.remove(i);
                return true; 
            }
        }
        return false; 
    }



    public List<Product> getWishList(String userId){
        User user = getUserById(userId); 

        if(user != null){
            return user.getWishlist(); 
        }

        return null; 
    }

    public String addProductToWishList(String userId, String productId){
        User user = getUserById(userId); 
        if(user == null){
            return "User not found";
        }

        Product product = productService.getProductById(productId); 
        if(product == null){
            return "Product not found"; 
        }

        user.getWishlist().add(product); 
        return "Product added to wishlist successfully"; 
    }

    public String removeProductFromWishList(String userId, String productId){
        User user = getUserById(userId); 

        if(user == null){
            return "User not found"; 
        }

        Product product = productService.getProductById(productId); 

        if(product == null){
            return "Product not found"; 
        }

        boolean removed = user.getWishlist().remove(product); 
        if(removed){
            return "Product removed from wishList successfully"; 
        }else {
            return "Product was not found in wishList"; 
        }
    }
}
