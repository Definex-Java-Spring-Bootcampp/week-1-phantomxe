package com.phantomxe;

import java.time.LocalDate;
import java.util.List;

public class Main { 
    public static void main(String[] args) { 
        System.out.println("Welcome to Online Shopping System! by Hakan Afat");
        System.out.println();
 
        OrderService orderService = new OrderService(
            List.of(
                new Customer("John", "test@test.com", LocalDate.of(1990, 10, 12)),
                new Customer("Cem", "test2@test.com", LocalDate.of(1997, 3, 3)),
                new Customer("Buse", "test3@test.com", LocalDate.of(1999, 9, 1)),
                new Customer("Ahmet", "test4@test.com", LocalDate.of(2000, 5, 13)),
                new Customer("Cem", "test5@test.com", LocalDate.of(2005, 7, 1))
            ),
            List.of(
                new Product("Laptop", 35000.0, 10, ProductCategory.ELECTRONIC),
                new Product("Mouse", 250.0, 100, ProductCategory.ELECTRONIC),
                new Product("Keyboard", 500.0, 50, ProductCategory.ELECTRONIC),
                new Product("Tshirt", 300.0, 20, ProductCategory.CLOTHING),
                new Product("Cordless Drill", 11650.0, 30, ProductCategory.TOOLS),
                new Product("Screwdriver", 150.0, 100, ProductCategory.TOOLS),
                new Product("Screw", 1.0, 1000, ProductCategory.TOOLS),
                new Product("Shampoo", 50.0, 100, ProductCategory.PERSONAL_CARE)
            )
        );

        // Order 1
        try {
            Product laptop = orderService.getProductByName("Laptop");
            Product mouse = orderService.getProductByName("Mouse");
            Product keyboard = orderService.getProductByName("Keyboard");

            Order order = new Order();
            order.addProduct(laptop, 1);
            order.addProduct(mouse, 1);
            order.addProduct(keyboard, 1);

            Customer customer = orderService.getCustomer("Cem", "test5@test.com");
            Invoice invoice = order.generateInvoice(customer);
            orderService.addInvoice(invoice); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 

        // Order 2
        try { 
            Product tshirt = orderService.getProductByName("Tshirt");
            Product cordlessDrill = orderService.getProductByName("Cordless Drill");

            Order order = new Order();
            order.addProduct(tshirt, 3);
            order.addProduct(cordlessDrill, 1);

            Customer customer = orderService.getCustomer("Cem", "test2@test.com");
            Invoice invoice = order.generateInvoice(customer);
            orderService.addInvoice(invoice); 
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Order 3
        try {
            Product screwdriver = orderService.getProductByName("Screwdriver");
            Product screw = orderService.getProductByName("Screw"); 

            Order order = new Order();
            order.addProduct(screwdriver, 2);
            order.addProduct(screw, 100);

            Customer customer = orderService.getCustomer("John", "test@test.com");
            Invoice invoice = order.generateInvoice(customer);
            orderService.addInvoice(invoice); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Order 4
        try { 
            Product shampoo = orderService.getProductByName("Shampoo"); 
            Product tshirt = orderService.getProductByName("Tshirt");

            Order order = new Order();
            order.addProduct(shampoo, 2);
            order.addProduct(tshirt, 5);

            Customer customer = orderService.getCustomer("Buse", "test3@test.com");
            Invoice invoice = order.generateInvoice(customer);
            orderService.addInvoice(invoice); 
 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

         // Order 5
         try { 
            Product screw = orderService.getProductByName("Screw"); 

            Order order = new Order();
            order.addProduct(screw, 150); 

            Customer customer = orderService.getCustomer("Cem", "test2@test.com");
            Invoice invoice = order.generateInvoice(customer);
            orderService.addInvoice(invoice);  

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("Products stocks status after orders: " + orderService.getProducts().stream().map(it -> it.getCategory() + " | " + it.getName() + " " + it.getQuantity() + "pcs Price: " + it.getPrice() + "TL").reduce("", (a, b) -> a + "\n - " + b)); 
    
        System.out.println();
        System.out.println("JOBS TO DO");
        System.out.println();

        System.out.println("Registered customer count: " + orderService.getCustomerCount()); 
        System.out.println();

        System.out.println("Check the invoices about the customer who named Cem then print the count of the products");
        orderService.getCustomers().stream()
            .filter(customer -> customer.getName().equals("Cem"))
            .forEach(customer -> {
                Integer totalCount = orderService.getInvoices().stream()
                    .filter(invoice -> invoice.getCustomer().equals(customer))
                    .mapToInt(invoice -> invoice.getProductCount())
                    .sum();
                System.out.println("Customer: " + customer.getName() + " Email: " + customer.getEmail() + " Ordered items count: " + totalCount);
            });
        System.out.println(); 

        System.out.println("Check the invoices about the customer who named Cem and age less than 30 but greater than 25 then print the total price of the invoices");
        orderService.getCustomers().stream()
            .filter(customer -> customer.getName().equals("Cem") && customer.getAge() < 30 && customer.getAge() > 25)
            .forEach(customer -> {
                double totalPrice = orderService.getInvoices().stream()
                    .filter(invoice -> invoice.getCustomer().equals(customer))
                    .mapToDouble(invoice -> invoice.getTotalPrice())
                    .sum();
                System.out.println("Customer: " + customer.getName() + " Email: " + customer.getEmail() + " Age: " + customer.getAge() + " Total price of invoices: " + totalPrice);
            });
        System.out.println();

        System.out.println("Check the invoices that total price greater than 1500");
        orderService.getInvoices().stream()
            .filter(invoice -> invoice.getTotalPrice() > 1500)
            .forEach(System.out::println);
    }
}