package com.example.capstone_1.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_1.Model.Merchant;
import com.example.capstone_1.Model.MerchantStock;
import com.example.capstone_1.Model.Product;
import com.example.capstone_1.Model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final MerchantService merchantService; 
    private final ProductService productService; 
    private final UserService userService; 

    private List<MerchantStock> merchantStocks = new ArrayList<>();
    
    public List<MerchantStock> getMerchantStocks(){
        return new ArrayList<>(merchantStocks); 
    }


    public MerchantStock getMerchantStockById(String id){
        for(MerchantStock merchantStockId : merchantStocks){
            if(merchantStockId.getId().equals(id)){
                return merchantStockId; 
            }
        }
        return null; 
    }


    public String addMerchantStock(MerchantStock merchantStock){
        List<Merchant> merchants = merchantService.getMerchants(); 
        List<Product> products = productService.getProducts();


        for(MerchantStock merchantStockId : merchantStocks){
            if(merchantStockId.getId().equals(merchantStock.getId())){
                return "Merchant Stock ID already used"; 
            }
        }

        boolean productExists = false; 
        for(Product product : products){
            if(product.getId().equals(merchantStock.getProductId())){
                productExists = true; 
                break; 
            }
        }

        if(!productExists){
            return "Product ID does not exist"; 
        }

        boolean merchantExists = false; 
        for(Merchant merchant : merchants){
            if(merchant.getId().equals(merchantStock.getMerchantId())){
                merchantExists = true; 
                break; 
            }
        }

        if(!merchantExists){
            return "Merchant ID does not exist"; 
        }


        merchantStocks.add(merchantStock); 
        return "Merchant Stock added successfully"; 
    }



    public boolean updateMerchantStock(String id, MerchantStock merchantStock){
        for(int i = 0; i < merchantStocks.size(); i++){
            if(merchantStocks.get(i).getId().equals(id)){
                merchantStock.setId(id);
                merchantStocks.set(i, merchantStock);
                return true; 
            }
        }
        return false; 
    }


    public boolean deleteMerchantStocks(String id){
        for(int i = 0; i < merchantStocks.size(); i++){
            if(merchantStocks.get(i).getId().equals(id)){
                merchantStocks.remove(i);
                return true; 
            }
        }
        return false; 
    }

    public String addStock(String productId, String merchantId, int additionalStock){
        for(MerchantStock stock : merchantStocks){
            if(stock.getProductId().equals(productId) && stock.getMerchantId().equals(merchantId)){
                stock.setStock(stock.getStock() + additionalStock);
                return "Add " + additionalStock + " to merchant stock"; 
            }
        }
        
        return "Product ID or Merchant ID not found"; 
    }


    public double applyDiscount(double price, double discountPercentage){
        return price - (price * (discountPercentage / 100)); 
    }

    public String buyProduct(String userId, String productId, String merchantId, String discountCode){
        User user = userService.getUserById(userId); 
        if(user == null){
            return "User ID does not exsist"; 
        }

        Product product = productService.getProductById(productId); 
        if(product == null){
            return "Product ID does not exsist"; 
        }

        Merchant merchant = merchantService.getMerchantById(merchantId); 
        if(merchant == null){
            return "Merchant ID does not exsist"; 
        }
        
        MerchantStock stock = getMerchantStockByProductAndMerchant(productId, merchantId);        
        if(stock == null){
            return "Product not available in merchant stock"; 
        }

        double price = product.getPrice();
        
        if(discountCode != null && discountCode.equalsIgnoreCase("get15")){
            price = price - (price * 15.0 / 100);
        }

        if(price <= 0){
            price = 0; 
        }

        if(user.getBalance() < price){
            return "Insufficient balance"; 
        }

        if(stock.getStock() <= 0){
            return "Product out of stock"; 
        }

        stock.setStock(stock.getStock() - 1);
        user.setBalance(user.getBalance() - price);
        
        return "Purchase successful"; 
    }

    public MerchantStock getMerchantStockByProductAndMerchant(String productId, String merchantId){
        for(MerchantStock stock : merchantStocks){
            if(stock.getProductId().equals(productId) && stock.getMerchantId().equals(merchantId)){
                return stock; 
            }
        }

        return null; 
    }
}
