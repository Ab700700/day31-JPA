package com.example.amazonclone.Service;

import com.example.amazonclone.Model.Category;
import com.example.amazonclone.Model.Product;
import com.example.amazonclone.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    final private ProductRepository products;
    final private CategoryService categoryService;
    public List<Product> getProducts(){
        return products.findAll();
    }
    public Product getProduct(Integer id){
        for(Product p : products.findAll()){
            if(p.getId().equals(id)) return p;
        }
        return null;
    }
    public void addProduct(Product product){
        products.save(product);
    }

    public Boolean updateProduct(Integer id, Product product){
        Product pr = products.getById(id);
        if(pr == null) return false;
        product.setId(id);
        products.save(product);
        return true;

    }

    public Boolean deleteProduct(Integer id){
        Product pr  = products.getById(id);
        if(pr == null) return false;
        products.delete(pr);
        return true;
    }

    public Product search(Integer id ){
        for(Product p :products.findAll()){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    public List<Product> searchbyCategory(String category){
        List<Product> newList = new ArrayList<>();
        Category ca =  categoryService.search(category);
        if(ca == null){
            return new ArrayList<>();
        }
        for(Product p : products.findAll()){
            if(p.getCategoryID().equals(ca.getId())) newList.add(p);
        }
        return newList;
    }
}
