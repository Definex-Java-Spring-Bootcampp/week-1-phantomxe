package com.phantomxe;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.phantomxe.enums.SectorType;
import com.phantomxe.factory.ApplicationFactory;
import com.phantomxe.factory.ProductFactory;
import com.phantomxe.factory.UserFactory;
import com.phantomxe.model.Application;
import com.phantomxe.model.Bank;
import com.phantomxe.model.Campaign;
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
        /*  
        User userCemtry2 = userFactory.createUser("cem", "dirman", LocalDate.of(1990, 1, 1), "cemdrman@gmail.com", "demopass", "+905425420000");
        if(userCemtry2 == null) {
            System.out.println("User creation failed");
            return;
        } */

        ProductFactory productFactory = new ProductFactory();
        try {
            Product myLoan = productFactory.generateLoan("VehicleLoan", BigDecimal.valueOf(500000), 36, 3.39);
            applicationFactory.addApplication(myLoan, userCem); 
            Product myLoan2 = productFactory.generateLoan("ConsumerLoan", BigDecimal.valueOf(10000), 12, 2.41);
            applicationFactory.addApplication(myLoan2, userCem); 
            System.out.println("Application added successfully");

            System.out.println("Most Applicant User: " + applicationFactory.getMostApplicantUser().getName());
        
            System.out.println("User Applications: " + applicationFactory.getUserApplications("cemdrman@gmail.com"));
        
            System.out.println("Get application from last month " + applicationFactory.getApplicationsAtLastMonth());

            applicationFactory.printHighestLoanApplication();
        
            Bank akbank = new Bank("Akbank");
            Bank isbank = new Bank("Isbank");

            List<Campaign> campaignList = new ArrayList<>();
            campaignList.add(new Campaign("New Year Shop Campaign", "demo campaign", LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 31), LocalDate.of(2021, 1, 31), SectorType.MARKET));
            campaignList.add(new Campaign("New Year Shop Campaign2", "demo campaign2", LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 31), LocalDate.of(2021, 1, 31), SectorType.MARKET));
            
            List<Campaign> campaignList2 = new ArrayList<>();
            campaignList2.add(new Campaign("New Year Shop Campaign3", "demo campaign", LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 31), LocalDate.of(2021, 1, 31), SectorType.MARKET));

            List<Campaign> campaignList3 = new ArrayList<>();
            campaignList3.add(new Campaign("New Year Shop Campaign3", "demo campaign", LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 31), LocalDate.of(2021, 1, 31), SectorType.MARKET));
            campaignList3.add(new Campaign("New Year Shop Campaign4", "demo campaign", LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 31), LocalDate.of(2021, 1, 31), SectorType.MARKET));
            campaignList3.add(new Campaign("New Year Shop Campaign5", "demo campaign2", LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 31), LocalDate.of(2021, 1, 31), SectorType.MARKET));
            campaignList3.add(new Campaign("New Year Shop Campaign6", "demo campaign3", LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 31), LocalDate.of(2021, 1, 31), SectorType.MARKET));

            Product card1 = productFactory.generateCard("CreditCard", akbank, BigDecimal.valueOf(250), campaignList); 
            productFactory.addProduct(card1);
            Product card2 = productFactory.generateCard("CreditCard", isbank, BigDecimal.valueOf(100), campaignList2); 
            productFactory.addProduct(card2);
            Product card3 = productFactory.generateCard("CreditCard", isbank, BigDecimal.valueOf(300), campaignList3); 
            productFactory.addProduct(card3);

            productFactory.printCreditCardStatusByCampaignCount();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}