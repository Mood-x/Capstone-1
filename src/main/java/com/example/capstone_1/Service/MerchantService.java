package com.example.capstone_1.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_1.Model.Merchant;

@Service
public class MerchantService {

    private List<Merchant> merchants = new ArrayList<>(); 

    public List<Merchant> getMerchants(){
        return new ArrayList<>(merchants); 
    }

    public Merchant getMerchantById(String id){
        for(Merchant merchantId : merchants){
            if(merchantId.getId().equals(id)){
                return merchantId; 
            }
        }
        
        return null; 
    }


    public boolean addMerchant(Merchant merchant){
        for(Merchant merchantId : merchants){
            if(merchantId.equals(merchant.getId())){
                return false; 
            }
        }
        merchants.add(merchant); 
        return true; 
    }

    public boolean updateMerchant(String id, Merchant merchant){
        for(int i = 0; i < merchants.size(); i++){
            if(merchants.get(i).getId().equals(id)){
                merchants.set(i, merchant);
                return true; 
            }
        }
        return false; 
    }


    public boolean deleteMerchant(String id){
        for(int i = 0; i < merchants.size(); i++){
            if(merchants.get(i).getId().equals(id)){
                merchants.remove(i);
                return true; 
            }
        }
        return false; 
    }
}
