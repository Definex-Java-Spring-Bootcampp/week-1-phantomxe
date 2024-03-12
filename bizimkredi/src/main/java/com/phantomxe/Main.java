package com.phantomxe;

import java.time.LocalDate;

import com.phantomxe.factory.ApplicationFactory;
import com.phantomxe.factory.UserFactory;

public class Main {
    public static void main(String[] args) {
        UserFactory userFactory = new UserFactory();
        ApplicationFactory applicationFactory = new ApplicationFactory(userFactory);

        userFactory.createUser("cem", "dirman", LocalDate.of(1990, 1, 1), "cemdrman@gmail.com", "demopass", "+905425420000");
        //this will fail
        //userFactory.createUser("cem", "dirman", LocalDate.of(1990, 1, 1), "cemdrman@gmail.com", "demopass", "+905425420000");

        
    }
}