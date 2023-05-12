package com.insureapp.InsureApp.service;

import com.insurapp.InsureApp.model.User;
import com.insurapp.InsureApp.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    public User storeUserInfoPerSession(Authentication authentication, HttpSession session){

        User currentUser = userRepository.getByName(authentication.getName());
        session.setAttribute("loggedInUser", currentUser);

        return currentUser;
    }
}
