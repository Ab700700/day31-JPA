package com.example.amazonclone.Service;

import com.example.amazonclone.Model.Category;
import com.example.amazonclone.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categories;

    public List<Category> getCategories(){
        return categories.findAll();
    }

    public void addCategory(Category category){
        categories.save(category);
    }

    public boolean updateCategory(Integer id, Category category){
        Category ca = categories.getById(id);
        if(ca == null) return false;
        category.setId(id);
        categories.save(category);
        return  true;
    }

    public boolean deleteCategory(Integer id){
        Category ca = categories.getById(id);
        if(ca == null) return false;
        categories.delete(ca);
        return true;
    }

    public Category search(String categoryname){
        for(Category c : this.getCategories()){
            if(c.getName().equals(categoryname)) return c;
        }
        return null;
    }
}
