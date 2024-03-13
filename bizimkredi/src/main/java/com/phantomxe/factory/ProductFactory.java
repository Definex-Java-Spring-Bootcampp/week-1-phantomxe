package com.phantomxe.factory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.phantomxe.model.Campaign;
import com.phantomxe.model.ConsumerLoan;
import com.phantomxe.model.CreditCard;
import com.phantomxe.model.HouseLoan;
import com.phantomxe.model.Product;
import com.phantomxe.model.VehicleLoan;

public class ProductFactory {
    private List<Product> productList;

    public ProductFactory() {
        productList = new ArrayList<>();
    }

    //Factory design pattern
    public Product generateLoan(String loanType, BigDecimal amount, Integer installment, Double interestRate) throws Exception {
        if(loanType.equalsIgnoreCase("VehicleLoan")) {
            return new VehicleLoan(amount, installment, interestRate);
        } else if(loanType.equalsIgnoreCase("HouseLoan")) {
            return new HouseLoan(amount, installment, interestRate);
        } else if(loanType.equalsIgnoreCase("ConsumerLoan")) {
            return new ConsumerLoan(amount, installment, interestRate);
        } else {
            throw new Exception("Invalid loan type");
        }
    }
    
    public Product generateCard(String creditType, Bank bank, BigDecimal fee, List<Campaign> campaignList) throws Exception {
        if(creditType.equalsIgnoreCase("CreditCard")) {  
            return new CreditCard(bank, fee, campaignList);
        } else {
            throw new Exception("Invalid card type");
        }
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public List<Product> getProductList() {
        return productList;
    }

}
