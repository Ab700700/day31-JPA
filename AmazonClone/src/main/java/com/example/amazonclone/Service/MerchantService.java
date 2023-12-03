package com.example.amazonclone.Service;

import com.example.amazonclone.Model.Merchant;
import com.example.amazonclone.Model.MerchantStock;
import com.example.amazonclone.Model.Product;
import com.example.amazonclone.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {
    final private MerchantRepository merchants;
    final private MerchantStockService merchantStockService;
    final private ProductService productService;
    public List<Merchant> getMerchants(){
        return merchants.findAll();
    }

    public void addMerchant(Merchant merchant){
        merchants.save(merchant);
    }

    public Boolean updateMerchant(Integer id, Merchant merchant){
        Merchant mer = merchants.getById(id);
        if(mer == null) return false;
        merchant.setId(id);
        merchants.save(merchant);
        return true;
    }


    public Boolean deleteMerchant(Integer id){
        Merchant mer = merchants.getById(id);
        if(mer == null) return false;
        merchants.delete(mer);
        return true;
    }

    public Merchant search(Integer id){
        for(Merchant merchant: this.getMerchants()){
            if(merchant.getId().equals(id)){
                return merchant;
            }
        }
        return null;
    }


    public int addStock(Integer productid,Integer merchantid, int amount){
        Product p = productService.getProduct(productid);
        MerchantStock ms = merchantStockService.searchforMerchant(merchantid);
        if(p == null|| ms == null) return 1;
        else {
            ms.setStock(ms.getStock()+amount);
            merchantStockService.updateMerchantStock(ms.getId(),ms);
            return 3;
        }
    }
}
