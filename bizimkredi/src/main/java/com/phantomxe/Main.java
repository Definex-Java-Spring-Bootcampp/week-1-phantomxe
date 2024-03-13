package com.phantomxe;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.phantomxe.factory.ApplicationFactory;
import com.phantomxe.factory.ProductFactory;
import com.phantomxe.factory.UserFactory;
import com.phantomxe.model.Application;
import com.phantomxe.model.Product;
import com.phantomxe.model.User;
import com.phantomxe.model.VehicleLoan;

public class Main {
    public static void main(String[] args) {
        UserFactory userFactory = new UserFactory();
        ApplicationFactory applicationFactory = new ApplicationFactory(userFactory);

        User userCem = userFactory.createUser("cem", "dirman", LocalDate.of(1990, 1, 1), "cemdrman@gmail.com", "demopass", "+905425420000");
        if(userCem == null) {
            System.out.println("User creation failed");
            return;
        }
        //this will fail
        //userFactory.createUser("cem", "dirman", LocalDate.of(1990, 1, 1), "cemdrman@gmail.com", "demopass", "+905425420000");

        ProductFactory productFactory = new ProductFactory();
        try {
            Product myLoan = productFactory.generateLoan("VehicleLoan", BigDecimal.valueOf(500000), 36, 3.39);
            applicationFactory.addApplication(myLoan, userCem); 
            System.out.println("Application added successfully");

            System.out.println("Most Applicant User: " + applicationFactory.getMostApplicantUser().getName());
        
            System.out.println("User Applications: " + applicationFactory.getUserApplications("cemdrman@gmail.com"));
        
            System.out.println("Get application from last month " + applicationFactory.getApplicationsAtLastMonth());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}