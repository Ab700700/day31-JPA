package com.example.amazonclone.Controller;

import com.example.amazonclone.Model.Merchant;
import com.example.amazonclone.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ac/merchant")
@RequiredArgsConstructor
public class MerchantController {

    final private MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getMerchants(){
        return ResponseEntity.status(HttpStatus.OK).body(merchantService.getMerchants());
    }
    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){

        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable Integer id, @RequestBody @Valid Merchant merchant,Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        if(merchantService.updateMerchant(id,merchant)){
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND.toString());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id){
        if(merchantService.deleteMerchant(id)){
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND.toString());
        }
    }

    @PutMapping("/addtosotck/{productid}/{merchantid}/{amount}")
    public ResponseEntity addStock(@PathVariable Integer productid,@PathVariable Integer merchantid,@PathVariable int amount){
        int num = merchantService.addStock(productid,merchantid,amount);
        if(num == 1)return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
        else if(num == 2) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("amount higher than stock");
        else{
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        }
    }
}
