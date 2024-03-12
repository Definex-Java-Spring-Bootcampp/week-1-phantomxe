package com.phantomxe.factory;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.text.html.Option;

import com.phantomxe.model.Application;
import com.phantomxe.model.User; 

public class ApplicationFactory {
    private List<Application> applicationList;
    private UserFactory userFactory;

    public ApplicationFactory(UserFactory userFactory) {
        applicationList = new ArrayList<>();
        this.userFactory = userFactory;
    }

    public void addApplication(Application application) {
        applicationList.add(application);
    }
    
    public List<Application> getApplicationList() {
        return applicationList;
    }

    public User getMostApplicantUser() throws Exception {
        if(applicationList.isEmpty()) {
            throw new Exception("Application list is empty");
        }

        List<User> users = userFactory.getUserList();
        
        User mostApplicantUser = users.get(0);
        long maxCount = 0;
        for(User user : users) {
            long userAppCount = applicationList.stream().filter(application -> application.getUser().equals(user)).count();
            if(userAppCount > maxCount) {
                maxCount = userAppCount;
                mostApplicantUser = user;
            }
        }

        return mostApplicantUser;
    }

    public List<Application> getUserApplications(String email) throws Exception {
        if(applicationList.isEmpty()) {
            throw new Exception("Application list is empty");
        }

        List<User> users = userFactory.getUserList();
        Optional<User> user = users.stream().filter(u -> u.getEmail().equals(email)).findFirst();
        if(user.isPresent()) {
            User userFound = user.get();
            return applicationList.stream().filter(application -> application.getUser().equals(userFound)).collect(Collectors.toList());
        } else {
            throw new Exception("User not found");
        } 
    }
}
