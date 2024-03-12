package com.phantomxe;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> products;
    private Boolean isConfirmed = false;

    public Order() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product, Integer quantity) throws IllegalArgumentException { 
        if(product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock for " + product.getName());
        }

        product.setQuantity(product.getQuantity() - quantity);
        Product newProduct = new Product(product.getName(), product.getPrice(), quantity, product.getCategory());
        products.add(newProduct);
    }

    public List<Product> getProducts() {
        return products;
    } 

    public Invoice generateInvoice(Customer customer) throws Exception {
        if(!isConfirmed) { 
            isConfirmed = true;
            return new Invoice(products, customer);
        } else {
            throw new Exception("Order already confirmed");
        }
    }
}
