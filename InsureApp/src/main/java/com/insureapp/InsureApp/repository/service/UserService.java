package com.insureapp.InsureApp.repository.service;

import com.insurapp.InsureApp.repository.model.User;
import com.insurapp.InsureApp.repository.model.enums.EUserRoles;
import com.insurapp.InsureApp.repository.repository.RolesRepository;
import com.insurapp.InsureApp.repository.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    public boolean registerNewUser(User user){
        user.setRoles(
                rolesRepository.getByRoleName(EUserRoles.USER.toString())
        );

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        user = userRepository.save(user);
//
        return user.getUserId() > 0;
//
//
    }



}
