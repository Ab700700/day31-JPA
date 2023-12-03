package com.example.amazonclone.Controller;

import com.example.amazonclone.Model.User;
import com.example.amazonclone.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ac/user")
@RequiredArgsConstructor
public class UserController {

        final private UserService userService;
    @GetMapping("/get")
    public ResponseEntity getUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){

        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        if(userService.updateUser(id,user)){
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND.toString());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        if(userService.deleteUser(id)){
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND.toString());
        }
    }

    @PutMapping("/buy/{userid}/{productid}/{merchantid}")
    public ResponseEntity buy(@PathVariable Integer userid, @PathVariable Integer productid,@PathVariable Integer merchantid){
        int num = userService.buy(userid,productid,merchantid);
        if(num==1) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
        else if(num == 2) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("merchant stock is empty");
        else  if(num == 3) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User balance is not enough");
        else{
            return  ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
        }
    }

    @PutMapping("/giftcard/{id}/{value}")
    public ResponseEntity gift(@PathVariable Integer id , @PathVariable int value){
        String gift = userService.generateCard(id,value);
        if(gift.equals("notfound")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }else if(gift.equals("notenough")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Balance is not enough");
        }else if(gift.equals("negative")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("value must be a positive number");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(gift);
        }

    }

    @PutMapping("/redeemcard/{id}/{cardnumber}")
    public ResponseEntity redeem(@PathVariable Integer id, @PathVariable String cardnumber){
        int num = userService.redeemCard(id,cardnumber);
        if(num == 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }else if(num == 2){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card number is wrong");
        }else {
          return ResponseEntity.status(HttpStatus.OK).body("Done");
        }
    }
}
