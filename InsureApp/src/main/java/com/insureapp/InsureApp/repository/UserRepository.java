package com.insureapp.InsureApp.repository;

import com.insurapp.InsureApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User getByName(String name);
}

