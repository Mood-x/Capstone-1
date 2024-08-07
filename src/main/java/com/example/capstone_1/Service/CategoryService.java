package com.example.capstone_1.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_1.Model.Category;

@Service
public class CategoryService {

    private List<Category> categories = new ArrayList<>(); 

    public List<Category> getCategories(){
        return new ArrayList<>(categories); 
    }

    public Category getCategoryById(String id){
        for(Category categoryId : categories){
            if(categoryId.getId().equals(id)){
                return categoryId; 
            }
        }
        return null;
    }
    

    public boolean addCategory(Category category){
        for(Category categoryId : categories){
            if(categoryId.getId().equals(category.getId())){
                return false; 
            }
        }
        categories.add(category); 
        return true; 
    }

    public boolean updateCategory(String id, Category category){
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).getId().equals(id)){
                category.setId(id);
                categories.set(i, category);
                return true; 
            }
        }
        return false; 
    }


    public boolean deleteCategory(String id){
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).getId().equals(id)){
                categories.remove(i);
                return true; 
            }
        }
        return false; 
    }
}
