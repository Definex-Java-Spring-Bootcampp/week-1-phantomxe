package com.phantomxe;

import java.time.LocalDate;
import java.util.List;

public class Invoice {
    private static Integer invoiceCounter = 10000;
    private Integer invoiceNumber;
    private List<Product> products;
    private Customer customer;
    private LocalDate date;
    private Double totalPrice;

    public Invoice(List<Product> products, Customer customer) {
        invoiceNumber = invoiceCounter++;
        this.products = products;
        this.customer = customer; 
        date = LocalDate.now();

        totalPrice = products.stream()
            .mapToDouble(product -> {
                return product.getPrice() * product.getQuantity();
            })
            .sum();
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    } 

    public List<Product> getProducts() {
        return products;
    } 

    public Customer getCustomer() {
        return customer;
    } 

    public LocalDate getDate() {
        return date;
    } 

    public Double getTotalPrice() {
        return totalPrice;
    } 

    public Integer getProductCount() {
        return products.stream()
            .mapToInt(product ->  product.getQuantity())
            .sum();
    }

    @Override
    public String toString() {
        String productsOutput = products.stream()
            .map(product -> product.toString())
            .reduce("", (a, b) -> a + "\n - " + b);
        return "Invoice #" + invoiceNumber + " Date: " + date + "\nCustomer Name: " + customer.getName() +"\n Order Details" + productsOutput + "\nTotal Price: " + totalPrice + "TL\n";
    } 
}