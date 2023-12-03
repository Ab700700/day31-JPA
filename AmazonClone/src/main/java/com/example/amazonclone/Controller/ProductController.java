package com.example.amazonclone.Controller;


import com.example.amazonclone.Model.Product;
import com.example.amazonclone.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ac/product")
@RequiredArgsConstructor
public class ProductController {

        final ProductService productService;
    @GetMapping("/get")
    public ResponseEntity getProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors){

        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        if(productService.updateProduct(id,product)){
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND.toString());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        if(productService.deleteProduct(id)){
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND.toString());
        }
    }

    @GetMapping("/searchcategory/{category}")
    public ResponseEntity searchcategory(@PathVariable String category){
        return ResponseEntity.status(HttpStatus.OK).body(productService.searchbyCategory(category));
    }
}
