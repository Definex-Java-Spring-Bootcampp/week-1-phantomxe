package com.phantomxe.factory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.phantomxe.model.User;

public class UserFactory {
    private List<User> userList;
    private byte[] salt = new byte[16];

    public UserFactory() {
        userList = new ArrayList<>();
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
    }

    public String hashPassword(String password) throws Exception { 
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-512"); // Singleton Design Pattern
            mDigest.update(salt);
            byte[] result = mDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< result.length ;i++){
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new Exception("Hashing failed");
        } 
    }

    public void createUser(String name, String surname, LocalDate birthDate, String email, String password, String phoneNumber) {
        Optional<User> userChecker = userList.stream().filter(user -> user.getEmail().equals(email)).findAny();
        if (userChecker.isPresent()) {
            System.out.println("User already exists with this email address.");
            return;
        }

        try {
            String passwordInHash = hashPassword(password); 
            User user = new User(name, surname, email, passwordInHash, phoneNumber, true);
            userList.add(user);
            System.out.println("User created successfully, " + name);
        } catch (Exception e) {
            System.out.println("User creation failed");
            System.out.println(e.getMessage());
        }
    }
}
