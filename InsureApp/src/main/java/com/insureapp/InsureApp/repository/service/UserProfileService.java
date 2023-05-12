package com.insureapp.InsureApp.repository.service;

import com.insurapp.InsureApp.repository.model.User;
import com.insurapp.InsureApp.repository.model.UserProfile;
import com.insurapp.InsureApp.repository.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    private UserRepository userRepository;

    public UserProfile getUserProfile(HttpSession session) {

        UserProfile userProfile = new UserProfile();

        User user = (User) session.getAttribute("loggedInUser");

        userProfile.setUserId(user.getUserId());
        userProfile.setName(user.getName());
        userProfile.setPhone(user.getPhone());
        userProfile.setEmail(user.getEmail());


        return userProfile;
    }


    public void updateUserProfile(UserProfile userProfile, HttpSession session){
        User user = (User) session.getAttribute("loggedInUser");
        user.setUserId(userProfile.getUserId());
        user.setName(userProfile.getName());
        user.setEmail(userProfile.getEmail());
        user.setPhone(userProfile.getPhone());
        userRepository.save(user);

        session.setAttribute("loggedInUser", user);
    }

}
