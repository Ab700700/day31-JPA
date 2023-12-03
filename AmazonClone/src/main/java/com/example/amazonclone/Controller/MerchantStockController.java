package com.example.amazonclone.Controller;


import com.example.amazonclone.Model.MerchantStock;
import com.example.amazonclone.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/ac/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {

    final private MerchantStockService merchantStockService;
    @GetMapping("/get")
    public ResponseEntity getMerchantStocks(){
        return ResponseEntity.status(HttpStatus.OK).body(merchantStockService.getMerchantStocks());
    }
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){

        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        if(merchantStockService.addMerchantStocks(merchantStock)){
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock must be 10 at first");
        }



    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id, @RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        if(merchantStockService.updateMerchantStock(id,merchantStock)){
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND.toString());
        }
    }
    @DeleteMapping("/delete/{id}/{merchantstockid}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id,@PathVariable Integer merchantstockid){
        if(merchantStockService.deleteMerchantStock(merchantstockid,id)){
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND.toString());
        }
    }
}
