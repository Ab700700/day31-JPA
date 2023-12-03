package com.example.amazonclone.Service;

import com.example.amazonclone.Model.Merchant;
import com.example.amazonclone.Model.MerchantStock;
import com.example.amazonclone.Model.Product;
import com.example.amazonclone.Repository.MerchantStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

final private MerchantStockRepository merchantStocks;
    public List<MerchantStock> getMerchantStocks(){
        return merchantStocks.findAll();
    }


    public Boolean addMerchantStocks(MerchantStock merchantStock){
        if(merchantStock.getStock()<10){
            return false;
        }
        merchantStocks.save(merchantStock);
        return true;
    }


    public Boolean updateMerchantStock(Integer id, MerchantStock merchantStock){
        MerchantStock mrs = merchantStocks.getById(id);
        if(mrs == null) return false;
        merchantStock.setId(id);
        merchantStocks.save(merchantStock);
        return  true;
    }


    public  boolean deleteMerchantStock(Integer id ,Integer merchantId){
        MerchantStock mrs = merchantStocks.getById(id);
        if(mrs == null) {
            return false;
        }else if(merchantId != mrs.getMerchantid()){
            return false;
        }else{
             merchantStocks.delete(mrs);
             return true;
        }

    }


    public MerchantStock searchforMerchant(Integer id){
        for (MerchantStock merchantStock: merchantStocks.findAll()){
            if(merchantStock.getMerchantid().equals(id))return merchantStock;
        }
        return  null;
    }

    public MerchantStock searchByids(Integer merchantid,Integer productid){
        for(MerchantStock merchantStock: merchantStocks.findAll()){
            if(merchantStock.getMerchantid().equals(merchantid)&&merchantStock.getProductid().equals(productid)){
                return merchantStock;
            }
        }
        return null;
    }
}
